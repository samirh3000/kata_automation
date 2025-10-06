//package com.orangehrm.core;
//
//import io.qameta.allure.testng.AllureTestNg;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Listeners;
//
//@Listeners({AllureTestNg.class})
//public class BaseTest {
//
//    @BeforeClass
//    public void setUp() {
//        DriverFactory.initDriver();
//    }
//
//    @AfterClass(alwaysRun = true)
//    public void tearDown() {
//        DriverFactory.quitDriver();
//    }
//}

package com.orangehrm.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        String browser = System.getProperty("browser", "chrome");
        boolean useBrowserStack = Boolean.parseBoolean(System.getProperty("useBrowserStack", "True"));

        driver = DriverFactory.createInstance(browser, false);
    }

    public WebDriver getDriver() {
        return driver;
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
