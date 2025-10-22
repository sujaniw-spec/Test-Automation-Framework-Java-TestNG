package com.ui.listerners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.ui.tests.TestBase;
import com.utilitiy.BrowserUtility;
import com.utilitiy.ExtentReporterUtility;
import com.utilitiy.LoggerUtility;

public class TestListener implements ITestListener {

    private static final Logger logger = LoggerUtility.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test suite started: " + context.getName());
        ExtentReporterUtility.setupSparkReporter("report.html");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test suite finished: " + context.getName());
        ExtentReporterUtility.flushReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting test: " + result.getMethod().getMethodName());
        logger.info("Description: " + result.getMethod().getDescription());
        logger.info("Groups: " + Arrays.toString(result.getMethod().getGroups()));

        ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " PASSED");
        ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error(result.getMethod().getMethodName() + " FAILED");
        logger.error(result.getThrowable().getMessage());

        ExtentReporterUtility.getTest().log(Status.FAIL, result.getThrowable());

        try {
            BrowserUtility browserUtility = ((TestBase) result.getInstance()).getInstance();
            if (browserUtility != null && browserUtility.getDriver() != null) {
                String screenshotPath = browserUtility.takeScreenshot(result.getMethod().getMethodName());
                ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
                logger.info("Screenshot captured: " + screenshotPath);
            } else {
                logger.warn("WebDriver is null — skipping screenshot for test: " + result.getName());
            }
        } catch (Exception e) {
            logger.error("Error capturing screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn(result.getMethod().getMethodName() + " SKIPPED");
        ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not commonly used, can leave empty
    }
}
