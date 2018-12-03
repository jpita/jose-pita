package appium.pageobjects.cocoin

import appium.pageobjects.BasePage
import io.appium.java_client.AppiumDriver

class BookingPage(driver: AppiumDriver<*>) : BasePage(driver)  {

    fun isExpenseInBooking(amount: String): Boolean {
        return driver.findElementById("com.nightonke.cocoin:id/expanse").text.trim() == "$$amount"
    }

}