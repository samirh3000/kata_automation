package com.orangehrm.api;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DemoApiTest {

    @Test(description = "Ejemplo estable: API pública (reqres.in)")
    @Description("Se incluye para tener una prueba estable y repetible en CI/CD.")
    public void testPublicApi() {
        given()
            .baseUri("https://reqres.in")
            .when()
            .get("/api/users/2")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("data.id", equalTo(2))
            .body("data.email", containsString("@"));
    }

    @Test(enabled = false, description = "OrangeHRM - placeholder si capturas la llamada desde la UI")
    public void testOrangeHrmPlaceholder() {
        given()
            .baseUri("https://opensource-demo.orangehrmlive.com")
        .when()
            .get("/api/placeholder")
        .then()
            .statusCode(200);
    }
}
