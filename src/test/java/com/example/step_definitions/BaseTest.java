package com.example.step_definitions;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import com.example.utils.LogUtility;

public class BaseTest {
    public WebDriver driver;
    public Logger log = LogUtility.getLogger();
    
    public BaseTest(WebDriver driver){
        this.driver = driver;
    }    
}
