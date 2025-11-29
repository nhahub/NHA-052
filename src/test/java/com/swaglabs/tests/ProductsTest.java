package com.swaglabs.tests;

import com.swaglabs.drivers.DriverSetup;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProductsTest {
    public WebDriver driver;

    // A simple test to add an item to the cart and verify the cart badge updates
    @Test
    public void addItemToCartTest() {

        // Assume successful login is done in setUp
        ProductsPage productsPage = new ProductsPage(driver);

        // 1. Verify we are on the Products Page
        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                "The user is not on the Products page after login.");

        // 2. Add an item to the cart
        productsPage.clickAddToCartForFirstItem();

        // 3. Verify the cart badge shows '1'
        Assert.assertTrue(productsPage.isShoppingCartBadgeCountCorrect("1"),
                "Shopping cart badge count is incorrect after adding an item.");

        // 4. Click the shopping cart icon to go to the Cart page
        productsPage.clickShoppingCart();
        // A further test in CartTest would verify the item is actually in the cart
    }

    @BeforeMethod
    public void setUp(){
        driver = DriverSetup.driverSetup();
        LoginPage loginPage=new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
