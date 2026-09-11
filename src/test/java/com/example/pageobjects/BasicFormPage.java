package com.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasicFormPage extends BasePage {

    @FindBy(id = "basic-form")
    private WebElement form;

    @FindBy(id = "text-input")
    private WebElement nameInput;

    @FindBy(id = "password-input")
    private WebElement passwordInput;

    @FindBy(id = "email-input")
    private WebElement emailInput;

    @FindBy(id = "phone-input")
    private WebElement phoneInput;

    @FindBy(id = "textarea-input")
    private WebElement bioInput;

    @FindBy(id = "form-submit")
    private WebElement submitButton;

    @FindBy(id = "form-reset")
    private WebElement resetButton;

    @FindBy(css = "[data-testid='form-result']")
    private WebElement resultMessage;

    public BasicFormPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(form));
    }

    public void enterName(String name) {
        WebElement input = waitForVisible(nameInput);
        input.clear();
        input.sendKeys(name);
    }

    public void enterPassword(String password) {
        WebElement input = waitForVisible(passwordInput);
        input.clear();
        input.sendKeys(password);
    }

    public void enterEmail(String email) {
        WebElement input = waitForVisible(emailInput);
        input.clear();
        input.sendKeys(email);
    }

    public void enterPhone(String phone) {
        WebElement input = waitForVisible(phoneInput);
        input.clear();
        input.sendKeys(phone);
    }

    public void enterBio(String bio) {
        WebElement input = waitForVisible(bioInput);
        input.clear();
        input.sendKeys(bio);
    }

    public void fillForm(String name, String password, String email, String phone, String bio) {
        enterName(name);
        enterPassword(password);
        enterEmail(email);
        enterPhone(phone);
        enterBio(bio);
    }

    public void submit() {
        waitForVisible(submitButton).click();
    }

    public void reset() {
        waitForVisible(resetButton).click();
    }

    public String getResultMessage() {
        return waitForVisible(resultMessage).getText().trim();
    }

    public WebElement waitForVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
