package api;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APITest {
    protected static String baseURL = "http://localhost:3001/booking/";
    protected String pathToRunningFile = System.getProperty("user.dir");
    protected String pathToTripJson = pathToRunningFile + "/createBookingJson.json";
    protected String pathToTripEditedJson = pathToRunningFile + "/createBookingEditedJson.json";
    private int idOfCreatedBooking;

    @Test
    public void getFirstBookingAndCheckFirstName() {
        String url = baseURL + "1";
        given().get(url).then().body("firstname", equalTo("Eric"));
    }

    @Test
    public void createBookingAndCheckIfWasCreated() throws IOException, ParseException {
        JSONObject jsonObject = (JSONObject) new JSONParser()
                .parse(new FileReader(pathToTripJson));
        Response response = given().
                with().contentType("application/json").body(jsonObject.toString()).post(baseURL);
        response.then().statusCode(200);
        idOfCreatedBooking = response.then().extract().path("bookingid");
        given().get(baseURL + idOfCreatedBooking).then().body("firstname", equalTo("jose"));
        System.out.println("idOfCreatedBooking= " + idOfCreatedBooking);
    }

    @Test
    public void createBookingAndDeleteIt() throws IOException, ParseException {
        createBookingAndCheckIfWasCreated();
        given().with().header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .delete(baseURL + idOfCreatedBooking).then().statusCode(201);
        given().get(baseURL + idOfCreatedBooking).then().statusCode(404);
    }

    @Test
    public void createBookingAndEditItAndDeleteIt() throws IOException, ParseException {
        //create new booking and get id
        createBookingAndCheckIfWasCreated();
        //get the edited json
        JSONObject jsonObject = (JSONObject) new JSONParser()
                .parse(new FileReader(pathToTripEditedJson));
//        call put method with edited json
        Response response = given().
                with().header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType("application/json").body(jsonObject.toString())
                .put(baseURL + idOfCreatedBooking);
//        check for 200 ok response
        response.then().statusCode(200);
//        delete the edited booking and check for 201
        given().with().header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .delete(baseURL + idOfCreatedBooking).then().statusCode(201);
//        check that the booking is not found
        given().get(baseURL + idOfCreatedBooking).then().statusCode(404);
    }
}
