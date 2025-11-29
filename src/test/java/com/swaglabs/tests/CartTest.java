package com.swaglabs.tests;

import com.swaglabs.drivers.DriverSetup;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest {
    public WebDriver driver;

    // Test to ensure the added item is present in the cart and start checkout
    @Test
    public void verifyItemAndCheckoutTest() {
        // Setup ensures successful login
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Pre-requisite: Add item and navigate to cart
        productsPage.clickAddToCartForFirstItem();
        productsPage.clickShoppingCart();

        // 1. Verify we are on the Cart Page
        Assert.assertTrue(cartPage.isCartPageDisplayed(),
                "The user is not on the Cart page.");

        // 2. Verify the specific item is present and the count is 1
        Assert.assertEquals(cartPage.getCartItemCount(), 1,
                "The cart should contain exactly 1 item.");
        Assert.assertTrue(cartPage.isBackpackItemPresent(),
                "The 'Sauce Labs Backpack' is not present in the cart.");

        // 3. Click the Checkout button
        cartPage.clickCheckout();
        // A CheckoutPage class and CheckoutTest would follow from here
    }

    @BeforeMethod
    public void setUp() {
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
