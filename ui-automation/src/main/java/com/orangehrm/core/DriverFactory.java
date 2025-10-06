package com.orangehrm.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;

public class DriverFactory {

    private static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME") != null
            ? System.getenv("BROWSERSTACK_USERNAME") : "samircamargo_tmcmaf";
    private static final String ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY") != null
            ? System.getenv("BROWSERSTACK_ACCESS_KEY") : "f6quxx5cirbo2ZN7TBLp";

    private static final String HUB_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static synchronized void setDriver(String browser, boolean useBrowserStack) throws Exception {
        if (useBrowserStack) {
            ChromeOptions options = new ChromeOptions();
            options.setPlatformName("Windows 11");
            options.setBrowserVersion("latest");

            HashMap<String, Object> bstackOptions = new HashMap<>();
            bstackOptions.put("projectName", "OrangeHRM Automation");
            bstackOptions.put("buildName", "Build #1");
            bstackOptions.put("sessionName", Thread.currentThread().getName());
            bstackOptions.put("os", "Windows");
            bstackOptions.put("osVersion", "11");
            bstackOptions.put("local", "false");
            bstackOptions.put("seleniumVersion", "4.21.0");
            options.addArguments("--start-maximized");


            options.setCapability("bstack:options", bstackOptions);
            System.out.println("🌐 Conectando a BrowserStack HUB: " + HUB_URL);
            System.out.println("🌐 Conectando a BrowserStack HUB: " + USERNAME);

            System.out.println("🌐 Conectando a BrowserStack HUB: " + ACCESS_KEY);
            driver.set(new RemoteWebDriver(new URL(HUB_URL), options));
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver.set(new ChromeDriver(options));
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
