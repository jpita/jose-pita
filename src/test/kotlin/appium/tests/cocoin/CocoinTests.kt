package appium.tests.cocoin

import appium.pageobjects.cocoin.MainPage
import appium.pageobjects.cocoin.OnboardingPage
import appium.pageobjects.cocoin.PinPage
import appium.tests.BaseTest
import io.qameta.allure.Description
import org.testng.Assert
import org.testng.annotations.Test

class CocoinTests : BaseTest(){

    var pinCode = "1111"

    @Test
    @Description("Goes through the onboarding process and creates a pin")
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
    @Description("checks that the categories change when we select different ones")
    fun categoriesAreChangingTest(){
        onBoardingAndPinCreationTest()
        var mainPage = MainPage(driver)
        var firstCategorySelected = mainPage.selectCategory("Lunch")
        var secondCategorySelected = mainPage.selectCategory("Breakfast")
        Assert.assertTrue(firstCategorySelected!=secondCategorySelected, "Categories are not changing")
    }

    @Test
    @Description("Creates and entry on the app and checks that it appears")
    fun createAnEntryOnTheBooking(){
        onBoardingAndPinCreationTest()
        var mainPage = MainPage(driver)
        mainPage.selectCategory("Lunch")
        val amount = "10"
        mainPage.insertNumberOnTheOnScreenKeyboard(amount)
        mainPage.submitExpense()
//      @text="Save successfully!"
//      this is a good place to put an assert on the popup but
//      I couldn't find it with Appium-desktop nor with uiautomatorviewer
        var bookingPage = mainPage.enterBookingView()
        Assert.assertTrue(bookingPage.isExpenseInBooking(amount), "Booking is not being saved")



    }
}