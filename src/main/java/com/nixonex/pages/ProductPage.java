package com.nixonex.pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    WebDriver driver;

    // Locators
    @FindBy(linkText = "Laptops")
    WebElement laptopsCategory;

    @FindBy(xpath = "//a[contains(text(),'Sony vaio i5')]")
    WebElement productLink;

    @FindBy(xpath = "//a[contains(text(),'Add to cart')]")
    WebElement addToCartButton;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectLaptopsCategory() {
        laptopsCategory.click();
    }

    public void selectProduct() {
        productLink.click();
    }
    
    public void addToCart() {
        // Click the Add to Cart button
        addToCartButton.click();

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
