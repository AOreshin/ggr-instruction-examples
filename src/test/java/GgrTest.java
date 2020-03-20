import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

class GgrTest {
    @Test()
    void androidEmulator() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "nexus_5_10.0");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "androidEmulator");
        capabilities.setCapability("app", "/root/tmp/app-debug.apk");

        AndroidDriver<WebElement> driver = new AndroidDriver<>(getGgr(), capabilities);
        AndroidElement pager = (AndroidElement) driver.findElements(By.xpath("//*")).get(23);
        pager.click();
        driver.quit();
    }

    @Test()
    void androidRealDevice() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "androidRealDevice");
        capabilities.setCapability("remoteAdbHost", "172.17.0.1");
        capabilities.setCapability("adbPort", 5038);
        capabilities.setCapability("app", "/root/tmp/app-debug.apk");

        AndroidDriver<WebElement> driver = new AndroidDriver<>(getGgr(), capabilities);
        AndroidElement pager = (AndroidElement) driver.findElements(By.xpath("//*")).get(23);
        pager.click();
        driver.quit();
    }

    @Test
    void chrome() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        WebDriver webDriver = new RemoteWebDriver(getGgr(), capabilities);
        webDriver.get("https://google.ru");
        webDriver.quit();
    }

    @Test
    void firefox() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("73.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        WebDriver webDriver = new RemoteWebDriver(getGgr(), capabilities);
        webDriver.get("https://google.ru");
        webDriver.quit();
    }

    private static URL getGgr() {
        try {
            return URI.create("http://test:test-password@localhost:4444/wd/hub").toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();

            return null;
        }
    }
}
