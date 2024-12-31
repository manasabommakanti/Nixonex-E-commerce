package com.nixonex.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.nixonex.pages.*;
import com.nixonex.utils.ExtentManager;
import com.nixonex.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class CompleteTest {
    private WebDriver driver;
    private static ExtentReports extent;
    private ExtentTest test;

    private RegisterPage registerPage;
    private LoginPage loginPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");

        extent = ExtentManager.getExtentReports();
        
        // Initialize page objects
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
    }


    @Test(priority = 1)
    public void testRegistration() {
        test = extent.createTest("Registration Test");
        try {
            test.info("Starting Registration Process");
            registerPage.clickSignUpLink();
            Thread.sleep(2000);
            registerPage.enterUsername("manasatest");
            registerPage.enterPassword("password123");
            registerPage.clickSignUpButton();
            Thread.sleep(2000);


            String regScreenshot = ScreenshotUtil.takeScreenshot(driver, "Registration");
            test.pass("Registration completed",
                MediaEntityBuilder.createScreenCaptureFromPath(regScreenshot).build());
        } catch (Exception e) {
            handleFailure(e, "Registration Failure");
        }
    }


    @Test(priority = 2, dependsOnMethods = "testRegistration")
    public void testLogin() {
        test = extent.createTest("Login Test");
        try {
            test.info("Starting Login Process");
            loginPage.clickLoginLink();
            Thread.sleep(2000);
            loginPage.enterUsername("manasatest");
            loginPage.enterPassword("password123");
            loginPage.clickLoginButton();
            Thread.sleep(5000);


            String loginScreenshot = ScreenshotUtil.takeScreenshot(driver, "Login");
            test.pass("Login successful",
                MediaEntityBuilder.createScreenCaptureFromPath(loginScreenshot).build());
        } catch (Exception e) {
            handleFailure(e, "Login Failure");
        }
    }


    @Test(priority = 3, dependsOnMethods = "testLogin")
    public void testAddToCart() {
        test = extent.createTest("Add to Cart Test");
        try {
            test.info("Adding product to cart");
            productPage.selectLaptopsCategory();
            Thread.sleep(2000);
            productPage.selectProduct();
            Thread.sleep(2000);
            productPage.addToCart();
            Thread.sleep(500);


            String addToCartScreenshot = ScreenshotUtil.takeScreenshot(driver, "AddToCart");
            test.pass("Product added to cart",
                MediaEntityBuilder.createScreenCaptureFromPath(addToCartScreenshot).build());
        } catch (Exception e) {
            handleFailure(e, "Add to Cart Failure");
        }
    }


    @Test(priority = 4, dependsOnMethods = "testAddToCart")
    public void testCheckout() {
        test = extent.createTest("Checkout Test");
        try {
            test.info("Starting Checkout Process");
            checkoutPage.openCart();
            Thread.sleep(2000);
            checkoutPage.clickPlaceOrder();
            Thread.sleep(2000);
            checkoutPage.fillDetails("John Doe", "USA", "New York", "1234567812345678", "12", "2025");
            checkoutPage.clickPurchase();
            Thread.sleep(2000);


            String checkoutScreenshot = ScreenshotUtil.takeScreenshot(driver, "Checkout");
            test.pass("Checkout completed",
                MediaEntityBuilder.createScreenCaptureFromPath(checkoutScreenshot).build());
        } catch (Exception e) {
            handleFailure(e, "Checkout Failure");
        }
    }


    private void handleFailure(Exception e, String testName) {
        try {
            String failureScreenshot = ScreenshotUtil.takeScreenshot(driver, testName);
            test.fail("Test failed: " + e.getMessage(),
                MediaEntityBuilder.createScreenCaptureFromPath(failureScreenshot).build());
        } catch (Exception screenshotException) {
            test.fail("Failed to capture screenshot: " + screenshotException.getMessage());
        }
    }
}


