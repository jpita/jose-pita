package appium.pageobjects.cocoin

import appium.pageobjects.BasePage
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy

class PinPage(driver: AppiumDriver<MobileElement>?) : BasePage(driver!!) {

    @AndroidFindBy(id="com.nightonke.cocoin:id/password_tip")
    private lateinit var messageLabel: MobileElement

    fun isWelcomeMessageShowing(): Boolean {
        return messageLabel.isDisplayed &&
                messageLabel.text == "Hey!\n" +
                "Welcome to use CoCoin!\n" +
                "To protect your account book,\n" +
                " you need a password to do so."
    }

    fun isRepeatPinMessageShowing(): Boolean {
        return messageLabel.isDisplayed &&
                messageLabel.text == "You gonna do it!\n Just input your password again!"
    }
}