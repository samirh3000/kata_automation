package com.orangehrm.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class DirectoryPage extends BasePage {

    private final By menuDirectory = By.xpath("//span[text()='Directory']");
    private final By employeeNameInput = By.xpath("//label[text()='Employee Name']/../following-sibling::div//input");
    private final By searchBtn = By.xpath("//button[contains(.,'Search')]");
    private final By resultsCards = By.cssSelector(".oxd-grid-item .oxd-sheet.oxd-sheet--rounded");

    @Step("search Employee")
    public void searchEmployee(String fName,String lName) {
        click(menuDirectory);
        type(employeeNameInput, fName);
        click(searchBtn);
        List<WebElement> cards = driver.findElements(resultsCards);
        boolean found =cards.stream().anyMatch(c -> c.getText().contains(fName));
        Assert.assertTrue(found, "El empleado "+ fName +" no se encontró en el directorio");
    }
}
