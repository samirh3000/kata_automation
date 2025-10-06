package com.orangehrm.api.utils;

import io.qameta.allure.Allure;
import java.time.LocalDateTime;


public class ReportUtils {


    public static void commentReport(String title, String comment, String status) {
        String jsonReport = String.format("""
        {
          "title": "%s",
          "comment": "%s",
          "status": "%s",
          "timestamp": "%s"
        }
        """, title, comment, status, LocalDateTime.now());

        Allure.addAttachment(" Evidencia JSON - " + title, "application/json", jsonReport);
    }


    public static void plainReport(String title, String message) {
        Allure.addAttachment("📝 " + title, "text/plain", message);
    }
}
