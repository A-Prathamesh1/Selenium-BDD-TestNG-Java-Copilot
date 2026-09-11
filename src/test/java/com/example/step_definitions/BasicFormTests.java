package com.example.step_definitions;

import static org.testng.Assert.assertEquals;

import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.example.pageobjects.BasicFormPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BasicFormTests {

    private WebDriver driver;
    private BasicFormPage basicFormPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new");
        //options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        //options.addArguments("--no-sandbox");
        //options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        basicFormPage = new BasicFormPage(driver);
    }

    // @After
    // public void tearDown() {
    //     if (driver != null) {
    //         driver.quit();
    //     }
    // }

    @Given("user opens the basic form page")
    public void user_opens_the_basic_form_page() {
        //String filePath = Paths.get("src/test/resources/basic-form-page.html").toAbsolutePath().toUri().toString();
        basicFormPage.open("https://www.sreenidhirajakrishnan.com/practice");
    }

    @When("user enters {string}, {string}, {string}, {string}, and {string}")
    public void user_enters(String name, String password, String email, String phone, String bio) {
        basicFormPage.fillForm(name, password, email, phone, bio);
    }

    @And("submits the form")
    public void submits_the_form() {
        basicFormPage.submit();
    }

    @Then("the form submission message should show {string}")
    public void the_form_submission_message_should_show(String expectedMessage) {
        String actualMessage = basicFormPage.getResultMessage();
        assertEquals(actualMessage, expectedMessage, "Form result message did not match the expected value.");
    }
}
