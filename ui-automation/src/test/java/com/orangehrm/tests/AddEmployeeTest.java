package com.orangehrm.tests;

import com.orangehrm.core.BaseTest;
import com.orangehrm.pages.DirectoryPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PimPage;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Paths;

public class AddEmployeeTest extends BaseTest {

    @Test(description = "Agregar y validar empleado en OrangeHRM")
    @Description("Login -> PIM add employee -> Directory search -> Validate")
    public void testAddEmployee() throws IOException {
        String first = "Carlos";
        String last = "Ramirez";
        String photo = Paths.get("src/test/resources/images/photo-sample.jpg").toAbsolutePath().toString();

        LoginPage login = new LoginPage(driver);
        PimPage pim = new PimPage(driver);
        DirectoryPage directory = new DirectoryPage(driver);

        login.loginAsAdmin();
        pim.addEmployee(first, last, photo);
        directory.searchEmployee(first, last);

    }

}

