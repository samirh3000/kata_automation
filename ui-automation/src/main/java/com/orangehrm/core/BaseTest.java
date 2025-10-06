package com.orangehrm.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        String browser = System.getProperty("browser", "chrome");
        boolean useBrowserStack = Boolean.parseBoolean(System.getProperty("useBrowserStack", "false"));

        DriverFactory.setDriver(browser, useBrowserStack);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}

