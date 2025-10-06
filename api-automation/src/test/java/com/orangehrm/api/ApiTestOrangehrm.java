package com.orangehrm.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.filter.cookie.CookieFilter;
import com.orangehrm.api.utils.ReportUtils;

public class ApiTestOrangehrm {

    @Test(description = "API Login exitoso orangehrm")
    public void login() {

        CookieFilter cookieFilter = new CookieFilter();

        Response loginPage = RestAssured.given()
                .filter(cookieFilter)
                .get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        Document doc = Jsoup.parse(loginPage.asString());
        Element authLogin = doc.selectFirst("auth-login");
        String token = authLogin.attr(":token").replace("&quot;", "").replace("\"", "");

        ReportUtils.commentReport("Token obtenido", "Token actual: " + token, "INFO");

        Response loginResponse = RestAssured.given()
                .filter(cookieFilter)
                .contentType("application/x-www-form-urlencoded")
                .formParam("_token", token)
                .formParam("username", "Admin")
                .formParam("password", "admin123")
                .post("https://opensource-demo.orangehrmlive.com/web/index.php/auth/validate");

        int statusCode = loginResponse.statusCode();
        String redirectUrl = loginResponse.getHeader("Location");
        ReportUtils.commentReport("Login Response",
                "Status Code: " + statusCode + "\nRedirect URL: " + redirectUrl,
                statusCode == 302 ? "SUCCESS" : "FAIL");

        Assert.assertEquals(statusCode, 302, " El login no devolvió redirect esperado (302)");

        Response dashboardResponse = RestAssured.given()
                .filter(cookieFilter)
                .get(redirectUrl);

        int dashboardStatus = dashboardResponse.statusCode();
        boolean isDashboard = dashboardResponse.asString().contains("Dashboard");

        ReportUtils.commentReport("Dashboard Response",
                "Status: " + dashboardStatus + "\nEs Dashboard: " + isDashboard,
                isDashboard ? "SUCCESS" : "FAIL");

        Assert.assertEquals(dashboardStatus, 200, " El dashboard no se cargó correctamente");
        Assert.assertTrue(isDashboard, " No se detectó el Dashboard después del login");
    }
}
