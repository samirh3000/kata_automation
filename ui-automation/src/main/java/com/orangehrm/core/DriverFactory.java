package com.orangehrm.core;

//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.MutableCapabilities;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.remote.RemoteWebDriver;
//
//import java.net.URL;
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
//public class DriverFactory {
//    private static final ThreadLocal<WebDriver> TL_DRIVER = new ThreadLocal<>();
//
//    public static void initDriver() {
//        String browser = System.getProperty("browser", System.getProperty("browser", "chrome"));
//        boolean useBS = Boolean.parseBoolean(System.getProperty("useBrowserStack", "false"));
//
//        try {
//            if (useBS) {
//                String user = System.getProperty("BROWSERSTACK_USERNAME");
//                String key = System.getProperty("BROWSERSTACK_ACCESS_KEY");
//                String url = "https://" + user + ":" + key + "@hub-cloud.browserstack.com/wd/hub";
//
//                MutableCapabilities caps = new MutableCapabilities();
//                caps.setCapability("browserName", "chrome");
//                caps.setCapability("browserVersion", "latest");
//                Map<String, Object> bstackOptions = new HashMap<>();
//                bstackOptions.put("os", "Windows");
//                bstackOptions.put("osVersion", "11");
//                bstackOptions.put("sessionName", "OrangeHRM E2E");
//                caps.setCapability("bstack:options", bstackOptions);
//
//                TL_DRIVER.set(new RemoteWebDriver(new URL(url), caps));
//            } else {
//                if ("chrome".equalsIgnoreCase(browser)) {
//                    WebDriverManager.chromedriver().setup();
//                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments("--start-maximized");
//                    TL_DRIVER.set(new ChromeDriver(options));
//                } else {
//                    throw new IllegalArgumentException("Browser no soportado: " + browser);
//                }
//            }
//            TL_DRIVER.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        } catch (Exception e) {
//            throw new RuntimeException("No se pudo iniciar el driver", e);
//        }
//    }
//
//    public static WebDriver getDriver() { return TL_DRIVER.get(); }
//
//    public static void quitDriver() {
//        if (TL_DRIVER.get() != null) {
//            TL_DRIVER.get().quit();
//            TL_DRIVER.remove();
//        }
//    }
//}

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

    public static WebDriver createInstance(String browser, boolean useBrowserStack) throws Exception {
        WebDriver driver;

        if (useBrowserStack) {
            ChromeOptions options = new ChromeOptions();
            options.setPlatformName("Windows 11");
            options.setBrowserVersion("latest");

            HashMap<String, Object> bstackOptions = new HashMap<>();
            bstackOptions.put("projectName", "OrangeHRM Automation");
            bstackOptions.put("buildName", "Build #1");
            bstackOptions.put("sessionName", "Test ejecutado en BrowserStack");
            bstackOptions.put("os", "Windows");
            bstackOptions.put("osVersion", "11");
            bstackOptions.put("local", "false");
            bstackOptions.put("seleniumVersion", "4.21.0");

            options.setCapability("bstack:options", bstackOptions);

            driver = new RemoteWebDriver(new URL(HUB_URL), options);
            System.out.println("🌐 Ejecutando en BrowserStack con usuario: " + USERNAME);

        } else {
            System.out.println("💻 Ejecutando localmente en: " + browser);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        return driver;
    }
}
