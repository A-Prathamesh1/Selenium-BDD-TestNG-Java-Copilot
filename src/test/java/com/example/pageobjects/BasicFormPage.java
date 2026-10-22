package com.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasicFormPage extends BasePage {

    private final By form = By.id("basic-form");

    private final By nameInput = By.id("text-input");

    private final By passwordInput = By.id("password-input");

    private final By emailInput = By.id("email-input");

    private final By phoneInput = By.id("phone-input");

    private final By bioInput = By.id("textarea-input");

    private final By submitButton = By.id("form-submit");

    private final By resetButton = By.id("form-reset");

    private final By resultMessage = By.cssSelector("[data-testid='form-result']");
    //private DriverManager driverManager;
    //private WebDriver driver = super.driver;//Manager.getDriver();
    public BasicFormPage(WebDriver driver) {
        super(driver);
        //this.driver = driver;        
    }

    public void open(String url) {
        driver.get(url);
        waitForVisible(form);
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

    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getURL(){
        return driver.getCurrentUrl();
    }
}
