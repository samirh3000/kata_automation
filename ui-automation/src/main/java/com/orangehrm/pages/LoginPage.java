package com.orangehrm.pages;
//
//import io.qameta.allure.Step;
//import org.openqa.selenium.By;
//
//public class LoginPage extends BasePage {
//    private final String baseUrl = System.getProperty("baseUrl", "https://opensource-demo.orangehrmlive.com");
//
//    private final By username = By.name("username");
//    private final By password = By.name("password");
//    private final By submit = By.cssSelector("button[type='submit']");
//
//    @Step("Open login page")
//    public void open() {
//        driver.get(baseUrl + "/web/index.php/auth/login");
//    }
//
//    @Step("Fill login page")
//    public void loginAsAdmin() {
//        open();
//        waitForPageToLoad(60);
//        type(username, "Admin");
//        type(password, "admin123");
//        click(submit);
//
//    }
//}

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("txtUsername");
    private final By passwordField = By.id("txtPassword");
    private final By loginButton = By.id("btnLogin");
    private final By dashboardTitle = By.id("welcome");
    private final String baseUrl = System.getProperty("baseUrl", "https://opensource-demo.orangehrmlive.com");

    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By submit = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        openUrl("https://opensource-demo.orangehrmlive.com/");
    }
    @Step("Fill login page")
    public void loginAsAdmin() {
        open();
        waitForPageToLoad(60);
        type(username, "Admin");
        type(password, "admin123");
        attachScreenshot(driver,"Campos de login diligenciados ");
        click(submit);

    }

}
