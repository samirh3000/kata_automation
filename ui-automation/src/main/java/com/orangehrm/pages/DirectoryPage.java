package com.orangehrm.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import java.io.*;
import java.io.IOException;

public class DirectoryPage extends BasePage {
    public DirectoryPage(WebDriver driver) {
        super(driver);
    }

    private final By menuDirectory = By.xpath("//span[text()='Directory']");
    private final By employeeNameInput = By.xpath("//label[text()='Employee Name']/../following-sibling::div//input");
    private final By searchBtn = By.xpath("//button[contains(.,'Search')]");
    private final By titleCard = By.xpath("//div[@class=\"orangehrm-corporate-directory-sidebar\"]//p[contains(@class, 'orangehrm-directory-card-header')]");



    @Step("search Employee")
    public void searchEmployee(String fName,String lName) throws IOException {
        SoftAssert softAssert = new SoftAssert();

        final By selectName = By.xpath("//div[contains(@class, 'oxd-autocomplete-dropdown')]//*[contains(text(), '"+fName+"')]");
        final By resultsCards = By.xpath("//p[contains(., '"+fName+"')]");

        click(menuDirectory);
        type(employeeNameInput, fName);
        click(selectName);
        click(searchBtn);
        waitForVisibility(resultsCards);
        click(resultsCards);
        waitForVisibility(titleCard);
        attachScreenshot(driver,"Visualizacion de card empleado ");
        softAssert.assertTrue(driver.findElement(titleCard).getText().contains(fName), "No se visualiza el nombre del empleado"+ fName);
        softAssert.assertTrue(driver.findElement(titleCard).getText().contains(lName), "No se visualiza el apellido del empleado"+ lName);
        softAssert.assertAll();
    }

}
