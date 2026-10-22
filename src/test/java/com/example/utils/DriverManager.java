package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    // get the driver
    public WebDriver getDriver(){
        if(threadLocalDriver.get() == null){
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            threadLocalDriver.set(driver);
        }
        return threadLocalDriver.get();
    }
    // quit driver
    public void quitDriver(){
        if(threadLocalDriver.get() != null){
            threadLocalDriver.get().quit();
            threadLocalDriver.remove();
        }
    }
}
