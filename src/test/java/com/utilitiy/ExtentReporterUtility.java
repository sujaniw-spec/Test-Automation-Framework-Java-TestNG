package com.utilitiy;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {

    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static void setupSparkReporter(String reportName) {
        if (extentReports == null) {
            synchronized (ExtentReporterUtility.class) {
                if (extentReports == null) {
                    ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/" + reportName);
                    sparkReporter.config().setReportName("Automation Test Results");
                    sparkReporter.config().setDocumentTitle("Automation Report");

                    extentReports = new ExtentReports();
                    extentReports.attachReporter(sparkReporter);
                }
            }
        }
    }

    public static void createExtentTest(String testName) {
        ExtentTest test = extentReports.createTest(testName);
        extentTest.set(test);
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void flushReport() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}