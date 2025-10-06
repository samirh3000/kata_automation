package com.orangehrm.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By submit = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open login page")
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
