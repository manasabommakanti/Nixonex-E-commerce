package com.nixonex.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
            spark.config().setDocumentTitle("E-commerce Test Report");
            spark.config().setReportName("E-commerce Automation Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Your Name");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}
