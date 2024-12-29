package com.nixonex.pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    WebDriver driver;

    @FindBy(id = "signin2")
    WebElement signUpLink;

    @FindBy(id = "sign-username")
    WebElement usernameField;

    @FindBy(id = "sign-password")
    WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(),'Sign up')]")
    WebElement signUpButton;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSignUpLink() {
        signUpLink.click();
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSignUpButton() {
        signUpButton.click();
        
     // Wait for the alert to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            // Print the alert message for debugging
            System.out.println("Alert message: " + alert.getText());
            // Accept the alert to dismiss it
            alert.accept();
        } catch (Exception e) {
            System.err.println("No alert appeared or it was already handled.");
        }
    }
}
