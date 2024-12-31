package com.nixonex.pages;


import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
    WebDriver driver;


    @FindBy(id = "login2")  // Login link on the homepage
    WebElement loginLink;


    @FindBy(id = "loginusername")  // Username input field in the login modal
    WebElement usernameField;


    @FindBy(id = "loginpassword")  // Password input field in the login modal
    WebElement passwordField;


    @FindBy(xpath = "//button[contains(text(),'Log in')]")  // Login button in the login modal
    WebElement loginButton;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void dismissBlockingModal() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement modalCloseButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Close')]"))); // Adjust locator for modal close button
            modalCloseButton.click();
        } catch (Exception e) {
            System.out.println("No modal or blocking element found.");
        }
    }


    public void clickLoginLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        
        // Scroll to the element if necessary
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginLink);


        // Click the element using JavaScript to avoid interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginLink);
    }


    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }


    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }
    
    public void clearFields() {
      usernameField.clear();
      passwordField.clear();
    }


    public void clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }
    
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
}
