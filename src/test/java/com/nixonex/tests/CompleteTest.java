package com.nixonex.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.nixonex.pages.*;
import com.nixonex.utils.ExtentManager;
import com.nixonex.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CompleteTest {
    private WebDriver driver;
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");

        extent = ExtentManager.getExtentReports();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
    }

    @Test
    public void testCompleteWorkflow() {
        test = extent.createTest("Complete Workflow Test");
        try {
            // **Registration**
            test.info("Starting Registration Process");
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.clickSignUpLink();
            Thread.sleep(2000);
            registerPage.enterUsername("manasatest");
            registerPage.enterPassword("password123");
            registerPage.clickSignUpButton();
            Thread.sleep(2000);
            String regScreenshot = ScreenshotUtil.takeScreenshot(driver, "Registration");
            test.pass("Registration completed",
                MediaEntityBuilder.createScreenCaptureFromPath(regScreenshot).build());

            // **Login**
            test.info("Starting Login Process");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickLoginLink();
            Thread.sleep(2000);
            loginPage.enterUsername("manasatest");
            loginPage.enterPassword("password123");
            loginPage.clickLoginButton();
            Thread.sleep(2000);
            String loginScreenshot = ScreenshotUtil.takeScreenshot(driver, "Login");
            test.pass("Login successful",
                MediaEntityBuilder.createScreenCaptureFromPath(loginScreenshot).build());

            // **Add to Cart**
            test.info("Adding product to cart");
            ProductPage productPage = new ProductPage(driver);
            productPage.selectLaptopsCategory();
            Thread.sleep(2000);
            productPage.selectProduct();
            Thread.sleep(2000);
            productPage.addToCart();
            Thread.sleep(2000);
            String addToCartScreenshot = ScreenshotUtil.takeScreenshot(driver, "AddToCart");
            test.pass("Product added to cart",
                MediaEntityBuilder.createScreenCaptureFromPath(addToCartScreenshot).build());

            // **Checkout**
            test.info("Starting Checkout Process");
            CheckoutPage checkoutPage = new CheckoutPage(driver);
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
            // Capture failure screenshot
            String failureScreenshot = ScreenshotUtil.takeScreenshot(driver, "Failure");
            test.fail("Test failed: " + e.getMessage(),
                MediaEntityBuilder.createScreenCaptureFromPath(failureScreenshot).build());
        }
    }
}
