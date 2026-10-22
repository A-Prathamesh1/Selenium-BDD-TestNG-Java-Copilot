package com.example.listeners;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.example.utils.ExtentReportManager;

public class ExtentListener implements ITestListener {
    private static final ThreadLocal<ExtentTest> CURRENT_TEST = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String name = testName(result);
        ExtentTest test = ExtentReportManager.createTest(name);
        CURRENT_TEST.set(test);
        test.log(Status.INFO, "Test started: " + name);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        currentTest(result).pass(testName(result) + " Test passed");
        CURRENT_TEST.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failWithThrowable(result, testName(result) + " Test failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = currentTest(result);
        if (result.getThrowable() == null) {
            test.skip(testName(result) + " Test skipped");
        } else {
            test.skip(result.getThrowable());
        }
        CURRENT_TEST.remove();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        failWithThrowable(result, testName(result) + " Test failed within the success percentage allowance");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        failWithThrowable(result, testName(result) + " Test failed due to a timeout");
    }

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.getInstance();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flush();
    }

    private ExtentTest currentTest(ITestResult result) {
        ExtentTest test = CURRENT_TEST.get();
        if (test == null) {
            test = ExtentReportManager.createTest(testName(result));
            CURRENT_TEST.set(test);
        }
        return test;
    }

    private String testName(ITestResult result) {
        if (result == null) {
            return "Unknown Test";
        }

        String name = result.getTestName();
        if (name != null && !name.isBlank()) {
            return name;
        }

        ITestNGMethod method = result.getMethod();
        if (method != null) {
            name = method.getMethodName();
            if (name != null && !name.isBlank()) {
                return name;
            }
        }

        return "Unnamed Test";
    }

    public static void attachFailureScreenshot(String base64Screenshot, String scenarioName) {
        ExtentTest test = CURRENT_TEST.get();
        if (test != null) {
            test.info("Screenshot captured for failed scenario: " + scenarioName,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot, scenarioName).build());
        }
    }

    private void failWithThrowable(ITestResult result, String message) {
        ExtentTest test = currentTest(result);
        Throwable throwable = result.getThrowable();
        if (throwable == null) {
            test.fail(message);
        } else {
            test.fail(throwable).log(Status.FAIL, message);
        }
        CURRENT_TEST.remove();
    }
}