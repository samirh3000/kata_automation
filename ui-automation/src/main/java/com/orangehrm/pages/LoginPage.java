package com.orangehrm.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final String baseUrl = System.getProperty("baseUrl", "https://opensource-demo.orangehrmlive.com");

    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By submit = By.cssSelector("button[type='submit']");

    @Step("Open login page")
    public void open() {
        driver.get(baseUrl + "/web/index.php/auth/login");
    }

    @Step("Fill login page")
    public void loginAsAdmin() {
        open();
        waitForPageToLoad(60);
        type(username, "Admin");
        type(password, "admin123");
        click(submit);

    }
}
