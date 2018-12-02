package appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;
import org.openqa.selenium.net.UrlChecker;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumServerChecker {
    private static AppiumDriverLocalService service = null;


    public static void startAppiumServerWithEmulator(String emulatorName) {
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .withArgument(new ServerArgument(){
                    public String getArgument() {
                        return "--avd";
                    }
                }, emulatorName));

        service.start();
    }

    public static void startAppiumServer(URL appiumServerURL) throws Exception {
        if(!isAppiumRunning(appiumServerURL)){
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder());
            service.start();
        }

    }

    public static void stopAppiumServer(){
        try{
            service.stop();
        }catch (NullPointerException ex)
        {
            System.out.println("Service wasn't running");
        }
    }


    private static Boolean isAppiumRunning(URL server_url) throws Exception {
        final URL status = new URL(server_url + "/sessions");
        try {
            new UrlChecker().waitUntilAvailable(1, TimeUnit.MILLISECONDS, status);
            return true;
        } catch (UrlChecker.TimeoutException e) {
            return false;
        }
    }
}
