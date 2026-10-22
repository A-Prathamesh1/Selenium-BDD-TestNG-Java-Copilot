package com.example.hooks;

import com.example.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;

public class Hooks {
    private final DriverManager driverManager;

    
    public Hooks(DriverManager driverManager){
        this.driverManager = driverManager;
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before all hook Cucumber test run started");
    }

    @Before
    public void beforeScenario() {
        System.out.println("Before scenario hook from BasicFormTest started");
        driverManager.getDriver(); // driver initialized 
    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("Before Step hook started");
        
    }

    @AfterStep
    public void afterStep() {
        System.out.println("after Step Hook finished");
    }

    @After
    public void afterScenario() {
        driverManager.quitDriver();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After all hook Cucumber test run finished");
    }
}