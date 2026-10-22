package com.example.step_definitions;

import org.openqa.selenium.WebDriver;

public class BaseTest {
    public WebDriver driver;

    public BaseTest(WebDriver driver){
        this.driver = driver;
    }    
}
