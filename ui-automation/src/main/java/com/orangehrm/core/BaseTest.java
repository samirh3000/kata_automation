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

import io.github.cdimascio.dotenv.Dotenv;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.util.HashMap;

public class BaseTest {

    protected WebDriver driver;
    private static final String BROWSERSTACK_URL = "https://%s:%s@hub-cloud.browserstack.com/wd/hub";

    @BeforeMethod
    public void setUp() throws Exception {
        // Verificar si usar BrowserStack o ejecución local
        String useBrowserStack = System.getProperty("useBrowserStack", "true");

        if ("true".equalsIgnoreCase(useBrowserStack)) {
            // Configuración para BrowserStack
            setupBrowserStack();
        } else {
            // Configuración local
            setupLocalBrowser();
        }
    }

    private void setupBrowserStack() throws Exception {
        // Cargar variables de entorno desde .env
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        String username = dotenv.get("BROWSERSTACK_USERNAME");
        String accessKey = dotenv.get("BROWSERSTACK_ACCESS_KEY");

        // Validar que las credenciales existan
        if (username == null || username.isEmpty()) {
            throw new IllegalStateException("BROWSERSTACK_USERNAME no está configurado en el archivo .env");
        }
        if (accessKey == null || accessKey.isEmpty()) {
            throw new IllegalStateException("BROWSERSTACK_ACCESS_KEY no está configurado en el archivo .env");
        }

        // Imprimir para debug (comentar en producción)
        System.out.println("=== BROWSERSTACK DEBUG ===");
        System.out.println("Username cargado: " + username);
        System.out.println("AccessKey cargado: " + (accessKey != null ? "****" + accessKey.substring(accessKey.length() - 4) : "null"));
        System.out.println("Verifica tus credenciales en: https://www.browserstack.com/accounts/settings");
        System.out.println("========================");

        // Configurar capabilities con formato actualizado W3C
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "latest");

        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "11");
        browserstackOptions.put("projectName", "Mi Proyecto Selenium");
        browserstackOptions.put("buildName", "Build 1.0");
        browserstackOptions.put("sessionName", "Test Session");
        browserstackOptions.put("userName", username);
        browserstackOptions.put("accessKey", accessKey);

        capabilities.setCapability("bstack:options", browserstackOptions);

        // Usar URL sin credenciales en la URL (ya están en bstack:options)
        String hubUrl = "https://hub-cloud.browserstack.com/wd/hub";

        // Inicializar RemoteWebDriver
        driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
        driver.manage().window().maximize();
    }

    private void setupLocalBrowser() {
        // Configuración para ejecución local con ChromeDriver
        String browser = System.getProperty("browser", "chrome");

        System.out.println("=== EJECUCIÓN LOCAL ===");
        System.out.println("Navegador: " + browser);
        System.out.println("=====================");

        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        // Puedes agregar más navegadores aquí

        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}