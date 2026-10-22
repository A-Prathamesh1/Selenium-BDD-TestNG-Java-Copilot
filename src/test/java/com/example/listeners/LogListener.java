package com.example.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.example.utils.LogUtility.*;

public class LogListener implements ITestListener {

    public static String testName(ITestResult result) {
		return result.getTestName() != null ? result.getTestName()
				: result.getMethod().getConstructorOrMethod().getName();
	}

    @Override
    public void onTestStart(ITestResult result) {
        logInfo("-------------TEST STARTED-------------: " + testName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logInfo("TEST PASSED: " + testName(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logError("TEST FAILED: " + testName(result));
        logThrowable(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logWarning("TEST SKIPPED: " + testName(result));
        logThrowable(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logWarning("TEST FAILED WITHIN SUCCESS PERCENTAGE: " + testName(result));
        logThrowable(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        logError("TEST FAILED WITH TIMEOUT: " + testName(result));
        logThrowable(result);
    }

    @Override
    public void onStart(ITestContext context) {
        startFileLogging();
        logInfo("TEST STARTED: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logInfo("TEST FINISHED: " + context.getName());
        closeFileLogging();
    }

    private void logThrowable(ITestResult result) {
        if (result.getThrowable() != null) {
            logError("Test exception details: " + testName(result), result.getThrowable());
        }
    }
}