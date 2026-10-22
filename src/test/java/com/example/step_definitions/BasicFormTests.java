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
        //driver = drive.getDriver();
        basicFormPage = new BasicFormPage(driver);
        basicFormPage.open(url);
        Assert.assertTrue(basicFormPage.getURL().contains("sreenidhirajakrishnan"));
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
        Assert.assertEquals(actualMessage, expectedMessage, "Form result message did not match the expected value.");
    }
}
