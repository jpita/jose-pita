package appium.pageobjects.cocoin

import appium.pageobjects.BasePage
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy


class OnboardingPage(driver: AppiumDriver<MobileElement>?) : BasePage(driver!!) {

    @AndroidFindBy(id="com.nightonke.cocoin:id/icon_11")
    private lateinit var imageOnTheFirstScreen: MobileElement

    @AndroidFindBy(id="com.nightonke.cocoin:id/histogram")
    private lateinit var imageOnTheSecondScreen: MobileElement

    @AndroidFindBy(id="com.nightonke.cocoin:id/mobile")
    private lateinit var imageOnTheThirdScreen: MobileElement

    @AndroidFindBy(id="com.nightonke.cocoin:id/remind_2")
    private lateinit var imageOnTheFourthScreen: MobileElement


    fun isFirstScreenLoading(): Boolean {
        return imageOnTheFirstScreen.isDisplayed
    }

    fun isSecondScreenLoading(): Boolean {
        return imageOnTheSecondScreen.isDisplayed
    }

    fun isThirdScreenLoading(): Boolean {
        return imageOnTheThirdScreen.isDisplayed
    }

    fun isFourthScreenLoading(): Boolean {
        return imageOnTheFourthScreen.isDisplayed
    }
}

