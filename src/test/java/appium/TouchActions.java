package appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;


public class TouchActions {

    public void swipeByCoordinates(AppiumDriver driver, int x1, int y1, int x2, int y2){
        PointOption p1= new PointOption();
        new TouchAction(driver).
                press(p1.withCoordinates(x1, y1)).
                moveTo(p1.withCoordinates(x2, y2)).
                release().perform();
    }
}
