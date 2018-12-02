package appium.tests

import appium.AppiumServerChecker.startAppiumServer
import appium.AppiumServerChecker.stopAppiumServer
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import java.net.URL
import java.util.concurrent.TimeUnit

open class BaseTest {
    var driver: AppiumDriver<MobileElement>? = null

    private val appiumServerURL = URL("http://127.0.0.1:4723/wd/hub")

    private val capabilities = DesiredCapabilities()
    private var runningPath = System.getProperty("user.dir")
    private var appPath = "$runningPath/CoCoin.apk"

    @BeforeMethod
    fun setup() {
        startAppiumServer(appiumServerURL)
        createDriver()
    }

    private fun createDriver() {
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android")
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8")
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2")
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true)
        //setting timeout for 120 seconds so that the driver doesn't shutdown during debug
        capabilities.setCapability("newCommandTimeout", 120)
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "phone")
        capabilities.setCapability(MobileCapabilityType.APP, appPath)
        driver = AndroidDriver(appiumServerURL, capabilities)
        driver?.let {
            it.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        }
    }

    @AfterMethod
    fun tearDown() {
        driver!!.quit()
        stopAppiumServer()
    }
}
