package com.example.pageobjects;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class BasePage {

    public WebDriver driver;
    protected FluentWait<WebDriver> wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
    }
}
