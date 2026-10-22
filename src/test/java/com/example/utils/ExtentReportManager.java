package com.example.utils;

import java.nio.file.Path;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;

public final class ExtentReportManager {
    private static final Path REPORT_PATH = Path.of("target", "extent-report.html");
    private static ExtentReports extentReports;

    private ExtentReportManager() {
    }

    public static synchronized ExtentReports getInstance() {
        if (extentReports == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH.toString());
            sparkReporter.config().setDocumentTitle("Test Execution Report");
            sparkReporter.config().setReportName("Automated Test Results");
            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            addSystemInfo("Java Runtime", System.getProperty("java.runtime.version"));
            addSystemInfo("Java Vendor", System.getProperty("java.vendor"));
            addSystemInfo("Java Target", System.getProperty("java.target.version"));
            addSystemInfo("Operating System", System.getProperty("os.name"));
            addSystemInfo("Operating System Version", System.getProperty("os.version"));
            addSystemInfo("Operating System Architecture", System.getProperty("os.arch"));
            addSystemInfo("Maven", System.getProperty("maven.version"));
            addSystemInfo("Cucumber PicoContainer", System.getProperty("cucumber.version"));
            addSystemInfo("Cucumber Java", System.getProperty("cucumber.version"));
            addSystemInfo("Cucumber TestNG", System.getProperty("cucumber.version"));
            addSystemInfo("TestNG", System.getProperty("testng.version"));
            addSystemInfo("Selenium Java", System.getProperty("selenium.version"));
            addSystemInfo("Apache POI OOXML", System.getProperty("poi.version"));
            addSystemInfo("ExtentReports", System.getProperty("extentreports.version"));
            addSystemInfo("Maven Compiler Plugin", System.getProperty("maven.compiler.plugin.version"));
            addSystemInfo("Maven Surefire Plugin", System.getProperty("maven.surefire.plugin.version"));
        }
        return extentReports;
    }

    public static synchronized void setBrowserInfo(WebDriver driver) {
        if (driver == null) {
            return;
        }

        Capabilities capabilities = ((HasCapabilities) driver).getCapabilities();
        addSystemInfo("Browser", capabilities.getBrowserName());
        addSystemInfo("Browser Version", capabilities.getBrowserVersion());
    }

    private static void addSystemInfo(String name, String value) {
        if (value != null && !value.isBlank()) {
            extentReports.setSystemInfo(name, value);
        }
    }

    public static ExtentTest createTest(String testName) {
        return getInstance().createTest(testName);
    }

    public static synchronized void flush() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}