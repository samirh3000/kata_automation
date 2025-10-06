package com.orangehrm.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PimPage extends BasePage {
    public PimPage(WebDriver driver) {
        super(driver);
    }

    private final By menuPIM = By.xpath("//span[text()='PIM']");
    private final By addButton = By.xpath("//button[contains(.,'Add')]");
    private final By firstName = By.name("firstName");
    private final By lastName = By.name("lastName");
    private final By fileInput = By.xpath("//input[@type='file']");
    private final By saveBtn = By.xpath("//button[contains(.,'Save')]");
    private final By toast = By.cssSelector(".oxd-toast--success");
    private final By titleName = By.xpath("//div[@class=\"orangehrm-edit-employee-name\"]");

    @Step("Fill add Employee")
    public void addEmployee(String fName, String lName, String photoPath) {
        waitForPageToLoad(60);
        click(menuPIM);
        click(addButton);
        type(firstName, fName);
        type(lastName, lName);
        driver.findElement(fileInput).sendKeys(photoPath);
        click(saveBtn);
        waitVisible(toast);
        attachScreenshot(driver,"Busqueda de empleado");
        waitVisible(titleName);


    }
}
