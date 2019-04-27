package com.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseTest {

    File reportDir = new File(System.getProperty("user.dir") + "//Reports");
    File screenShotDir = new File(reportDir, "ScreenShots");
    File logsDir = new File(reportDir, "Logs");
    WebDriver driver = null;

    @BeforeSuite
    public void beforeSuite(ITestContext context) throws Exception {

        Environment.loadProperties(context);

        if (reportDir.exists()) {
            for (File reportFile : reportDir.listFiles()) {
                if (reportFile.isFile()) {
                    if (!reportFile.delete()) {
                        System.out.println(String.format("Unable to remove %s, please close if opened.", reportFile.getName()));
                        System.exit(1);
                    }
                }
            }
        } else {
            reportDir.mkdir();
        }

        if (logsDir.exists()) {
            for (File logFile : logsDir.listFiles()) {
                if (!logFile.delete()) {
                    System.out.println(String.format("Unable to remove %s, please close if opened.", logFile.getName()));
                    System.exit(1);
                }
            }
        } else {
            logsDir.mkdir();
        }


        if (screenShotDir.exists()) {
            for (File scrnshtFile : screenShotDir.listFiles()) {
                if (!scrnshtFile.delete()) {

                    System.out.println(String.format("Unable to remove %s, please close if opened.", scrnshtFile.getName()));
                    System.exit(1);
                }
            }
        } else {
            screenShotDir.mkdir();
        }

    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass(ITestContext context) {

    }

    @AfterClass(alwaysRun = true)
    public void afterClass(ITestContext context) {

    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method m, ITestContext context) {
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
            }

        } catch (Exception wde) {
        }
    }


}