package appium.pageobjects

import appium.TouchActions
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait

open class BasePage(val driver: AppiumDriver<*>) {

    var width = driver.manage().window().size.width
    var height = driver.manage().window().size.height
    var rightEdgeOfTheScreen = width-200
    var leftEdgeOfTheScreen = 100

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }

    fun waitForElementToDisappear(by: By){
        val wait = FluentWait<WebDriver>(driver)
            .withTimeout(java.time.Duration.ofSeconds(10))
            .pollingEvery(java.time.Duration.ofSeconds(1))
            .ignoring(NoSuchElementException::class.java)

        wait.until(ExpectedConditions.invisibilityOfElementLocated(by))
    }

    fun waitForElementToAppear(element: MobileElement){
        val wait = FluentWait<WebDriver>(driver)
            .withTimeout(java.time.Duration.ofSeconds(10))
            .pollingEvery(java.time.Duration.ofSeconds(1))
            .ignoring(NoSuchElementException::class.java)

        wait.until(ExpectedConditions.visibilityOf(element))
    }


    fun swipeLeft(){
        TouchActions().swipeByCoordinates(driver,rightEdgeOfTheScreen, height/3, leftEdgeOfTheScreen, height/3)
    }

    fun swipeRight(){
        TouchActions().swipeByCoordinates(driver,leftEdgeOfTheScreen, height/2, rightEdgeOfTheScreen, height/2)
    }

    fun insertNumberOnTheOnScreenKeyboard(number: String) {
        for (n in number) {
            for (el in driver.findElementsByClassName("android.widget.TextView"))
            {
                if (el.text == n.toString()) {
                    el.click()
                    break
                }
            }

        }

    }
}