package com.example.step_definitions;

import org.testng.Assert;
import com.example.pageobjects.BasicFormPage;
import com.example.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BasicFormTests extends BaseTest{
    private BasicFormPage basicFormPage;

    public BasicFormTests(DriverManager driverManager) {
        super(driverManager.getDriver());
    }

    @Given("user is on the {string} site")
    public void user_is_on_the_site(String url) {
        log.info("Starting basic form test; opening application URL: " + url);
        basicFormPage = new BasicFormPage(driver);
        basicFormPage.open(url);
        String currentUrl = basicFormPage.getURL();
        Assert.assertTrue(currentUrl.contains("sreenidhirajakrishnan"));
        log.info("Application URL verification passed");
    }

    @When("user enters {string}, {string}, {string}, {string}, and {string}")
    public void user_enters(String name, String password, String email, String phone, String bio) {
        basicFormPage.enterName(name);
        log.info("Name field entry completed");

        basicFormPage.enterPassword(password);
        log.info("Password field entry completed");

        basicFormPage.enterEmail(email);
        log.info("Email field entry completed");

        basicFormPage.enterPhone(phone);
        log.info("Phone field entry completed");

        basicFormPage.enterBio(bio);
        log.info("Bio field entry completed");
    }

    @And("submits the form")
    public void submits_the_form() {
        basicFormPage.submit();
        log.info("Form submit button clicked");
    }

    @Then("the form submission message should show {string}")
    public void the_form_submission_message_should_show(String expectedMessage) {
        log.info("Reading the form submission result message");
        String actualMessage = basicFormPage.getResultMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Form result message did not match the expected value.");
        log.info("Form submission result verification passed");
    }
}
