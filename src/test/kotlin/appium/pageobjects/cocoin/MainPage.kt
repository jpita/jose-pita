package appium.pageobjects.cocoin

import appium.pageobjects.BasePage
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.By

class MainPage(driver: AppiumDriver<MobileElement>?) : BasePage(driver!!)  {

    @AndroidFindBy(id="com.nightonke.cocoin:id/content_hamburger")
    private lateinit var hamburgerMenuButton: MobileElement

    fun isBurgerMenuShowing():Boolean{
        return hamburgerMenuButton.isDisplayed
    }

    fun selectCategory(category: String): String {
        for(el in driver.findElements(By.id("com.nightonke.cocoin:id/tag_name"))){
            if(el.text==category) {
                el.click()
                break
            }
        }
        return driver.findElements(By.id("com.nightonke.cocoin:id/tag_name"))[0].text
    }

    fun submitExpense() {
        driver.findElements(By.id("com.nightonke.cocoin:id/icon"))[1].click()
    }

    fun enterBookingView(): BookingPage {
        hamburgerMenuButton.click()
        insertNumberOnTheOnScreenKeyboard("1111")
        return BookingPage(driver)
    }

}
