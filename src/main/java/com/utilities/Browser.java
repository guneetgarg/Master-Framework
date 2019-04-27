package com.utilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class Browser {
    /**
     * This method will invoke the browser instance.
     * Willn't load any URL
     *
     * @return driver object
     * @throws Exception
     */
    public static BrowserDriverImpl getBrowserInstance() {
        String browserName = Environment.getBrowserType();
        final BrowserDriverImpl driver = startDriver(browserName);
        //delete cookies
        driver.manage().deleteAllCookies();
        // set the implicit timeout
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    public static void setSystemProperty(final String propKey, final String value) {
        if (null == value) {
            return;
        }

        System.setProperty(propKey, value);
    }


    public static BrowserDriverImpl startDriver(String browserName) {
        WebDriver driver = null;
        //CustomLogger.log("Starting " + browserName.toUpperCase() + " browser ...");
        DesiredCapabilities capabilities = null;
        capabilities = DesiredCapabilities.chrome();

        // Set platform, browser type and version in capabilities
        capabilities.setPlatform(Platform.ANY);
        capabilities.setBrowserName(browserName);

//        CustomLogger.log("OS Information: " + System.getProperty("os.name") + "("
//                + System.getProperty("os.version") + ") - "
//                + System.getProperty("os.arch"));

        // Set drivers location
        String chromeDriverFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverFilePath);
        driver = new ChromeDriver();

        return new BrowserDriverImpl(driver);
    }


}