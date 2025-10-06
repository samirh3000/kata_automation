package com.orangehrm.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.URL;
import java.util.HashMap;

public class BrowserStackTest {

    private WebDriver driver;

    public static final String USERNAME = "samircamargo_tmcmaf";
    public static final String ACCESS_KEY = "f6quxx5cirbo2ZN7TBLp";
    public static final String HUB_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @BeforeClass
    public void setUp() throws Exception {
        // Configuración del navegador
        ChromeOptions options = new ChromeOptions();
        options.setPlatformName("Windows 11");
        options.setBrowserVersion("latest");

        // Opciones específicas de BrowserStack (obligatorio en Selenium 4+)
        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("projectName", "Demo Maven Java TestNG");
        bstackOptions.put("buildName", "Build #1");
        bstackOptions.put("sessionName", "Abrir y cerrar Chrome");
        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "11");
        bstackOptions.put("local", "false"); // Solo si NO usas BrowserStack Local
        bstackOptions.put("seleniumVersion", "4.21.0");

        // Añadir las opciones
        options.setCapability("bstack:options", bstackOptions);

        // Conectar al hub remoto de BrowserStack
        driver = new RemoteWebDriver(new URL(HUB_URL), options);
    }

    @Test
    public void openGooglePage() {
        driver.get("https://www.google.com");
        System.out.println("Título de la página: " + driver.getTitle());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Navegador cerrado correctamente.");
        }
    }
}
