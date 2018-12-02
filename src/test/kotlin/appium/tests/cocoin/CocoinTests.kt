package appium.tests.cocoin

import appium.pageobjects.cocoin.MainPage
import appium.pageobjects.cocoin.OnboardingPage
import appium.pageobjects.cocoin.PinPage
import appium.tests.BaseTest
import org.testng.Assert
import org.testng.annotations.Test

class CocoinTests : BaseTest(){

    var pinCode = "1111"

    @Test
    fun onBoardingAndPinCreationTest(){
        var onboardingPage = OnboardingPage(driver)
        Assert.assertTrue(onboardingPage.isFirstScreenLoading(), "FIRST SCREEN IS NOT SHOWING")
        onboardingPage.swipeLeft()
        Assert.assertTrue(onboardingPage.isSecondScreenLoading(), "Second SCREEN IS NOT SHOWING")
        onboardingPage.swipeLeft()
        Assert.assertTrue(onboardingPage.isThirdScreenLoading(), "Third SCREEN IS NOT SHOWING")
        onboardingPage.swipeLeft()
        Assert.assertTrue(onboardingPage.isFourthScreenLoading(), "Fourth SCREEN IS NOT SHOWING")
        onboardingPage.swipeLeft()
        var pinPage = PinPage(driver)
        Assert.assertTrue(pinPage.isWelcomeMessageShowing(), "Welcome Message IS NOT SHOWING")
        pinPage.insertNumberOnTheOnScreenKeyboard(pinCode)
        Assert.assertTrue(pinPage.isRepeatPinMessageShowing(), "Repeat Pin Message IS NOT SHOWING")
        pinPage.insertNumberOnTheOnScreenKeyboard(pinCode)
        var mainPage = MainPage(driver)
        Assert.assertTrue(mainPage.isBurgerMenuShowing(), "Burger menu IS NOT SHOWING")
    }

    @Test
    fun categoriesAreChangingTest(){
        onBoardingAndPinCreationTest()
        var mainPage = MainPage(driver)
        var firstCategorySelected = mainPage.selectCategory("Lunch")
        var secondCategorySelected = mainPage.selectCategory("Breakfast")
        Assert.assertTrue(firstCategorySelected!=secondCategorySelected, "Categories are not changing")
    }

    @Test
    fun createAnEntryOnTheBooking(){
        onBoardingAndPinCreationTest()
        var mainPage = MainPage(driver)
        mainPage.selectCategory("Lunch")
        mainPage.insertNumberOnTheOnScreenKeyboard("10")
        mainPage.submitExpense()
//        text()="Save successfully!"
//        couldn't find the success popup
        try {
            print(driver!!.findElementByXPath("//*[contains(text(), 'Save')]").text)
        }catch (e: Exception){
            print("couldn't find the element")
        }
        var bookingPage = mainPage.enterBookingView()
        Assert.assertTrue(bookingPage.isExpenseInBooking(), "Booking is not being saved")



    }
}