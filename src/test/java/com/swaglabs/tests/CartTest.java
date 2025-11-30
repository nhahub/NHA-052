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

    //ensure we start on the Cart Page with >=1 item
    private void navigateToCartWithItem() {
        ProductsPage productsPage = new ProductsPage(driver);

        // Add one item
        productsPage.clickAddToCartForFirstItem();

        // Navigate to cart
        productsPage.clickShoppingCart();
    }

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
    //Remove Item from Cart
    @Test
    public void testRemoveItemFromCart() {
        navigateToCartWithItem();
        CartPage cartPage = new CartPage(driver);

        // 1. Remove the item
        cartPage.removeItem();

        // 2. Assert cart count is 0
        Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart item count did not decrease after removal.");

        // 3. Assert cart badge is hidden/not present
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertFalse(productsPage.isShoppingCartBadgeCountCorrect("1"), "Cart badge should be hidden or empty.");
    }

    //Continue Shopping Button
    @Test
    public void testContinueShopping() {
        navigateToCartWithItem();
        CartPage cartPage = new CartPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        // 1. Click Continue Shopping
        cartPage.clickContinueShopping();

        // 2. Assert navigation to the Products page
        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                "Failed to navigate back to Products page after 'Continue Shopping'.");
    }
    //Test Case: Cart Item Integrity (BUG_2)
    @Test(description = "Verify product image and name are displayed correctly (TC_004, BUG_2)")
    public void testCartItemIntegrity() {
        navigateToCartWithItem();
        CartPage cartPage = new CartPage(driver);

        // 1. Verify item name presence
        Assert.assertTrue(cartPage.isBackpackItemPresent(),
                "Product name is not displayed or is incorrect (BUG_2 potential recurrence).");

        // 2. Verify image presence
        Assert.assertTrue(cartPage.areItemImagesDisplayed(),
                "Product image is missing from the cart (BUG_2).");
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
