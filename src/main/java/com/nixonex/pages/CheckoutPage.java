package com.nixonex.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;

    @FindBy(id = "cartur")
    WebElement cartLink;

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    WebElement placeOrderButton;

    @FindBy(id = "name")
    WebElement nameField;

    @FindBy(id = "country")
    WebElement countryField;

    @FindBy(id = "city")
    WebElement cityField;

    @FindBy(id = "card")
    WebElement cardField;

    @FindBy(id = "month")
    WebElement monthField;

    @FindBy(id = "year")
    WebElement yearField;

    @FindBy(xpath = "//button[contains(text(),'Purchase')]")
    WebElement purchaseButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openCart() {
        cartLink.click();
    }

    public void clickPlaceOrder() {
        placeOrderButton.click();
    }

    public void fillDetails(String name, String country, String city, String card, String month, String year) {
        nameField.sendKeys(name);
        countryField.sendKeys(country);
        cityField.sendKeys(city);
        cardField.sendKeys(card);
        monthField.sendKeys(month);
        yearField.sendKeys(year);
    }

    public void clickPurchase() {
        purchaseButton.click();
    }
}
