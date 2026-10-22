package com.example.runners;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import com.example.listeners.ExtentListener;
import com.example.listeners.LogListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@Listeners({LogListener.class, ExtentListener.class})
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.example",
    plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber-report.json"},
    monochrome = true
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    // can add the testNG annotated methods here, since while initializing this class
    // first overridden method will be executed & then TestNG related methods will be executed 
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
     
    @BeforeTest
    public void setUpBeforeTest(){
        System.out.println("Before test executed");
    } 
    /*
    @BeforeMethod 
    public void beforeMethod(){
        System.out.println("Before method executed");
    }
    */
    @AfterTest 
    public void afterTest(){
        System.out.println("After test executed");
    }
    /*
    @AfterMethod 
    public void afterMethod(){
        System.out.println("After method executed");
    }
     */
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before suite executed"); 
    }
    
    @AfterSuite 
    public void afterSuite(){
        System.out.println("after suite executed");
    }
}
