package com.swaglabs.tests;

import com.swaglabs.drivers.DriverSetup;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductsPage;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductsTest {
    public WebDriver driver;

    //add an item to the cart and verify the cart badge updates
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

    // NEW Test Case: Sorting A to Z (TC_020, BUG_001)
    @Test(description = "Verify sorting products by Name A to Z")
    public void testSortingAToZ() {
        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.selectSortOption("Name (A to Z)");
        List<String> actualNames = productsPage.getItemNames();

        Assert.assertTrue(productsPage.isSortedAlphabetically(actualNames),
                "Products are not sorted alphabetically (A to Z).");
    }

    // Test sorting price Desc
    @Test
    public void testSortingPriceHighToLow() {
        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.selectSortOption("Price (high to low)");
        List<Double> actualPrices = productsPage.getItemPrices();

        Assert.assertTrue(productsPage.isSortedByPriceHighToLow(actualPrices),
                "Products are not sorted by Price (High to Low).");
    }

    //Test Navigate to Product Detail (Req_019)
    @Test
    public void testNavigateToProductDetail() {
        ProductsPage productsPage = new ProductsPage(driver);

        // 1. Click on a product name link
        productsPage.clickFirstProductName();

        // 2. Assert navigation away from the products page (Check for the Back button presence)
        try {
            // We use findElement directly to assert existence on the new page
            Assert.assertTrue(driver.findElement(productsPage.backToProductsButton).isDisplayed(),
                    "Did not navigate to the Product Detail Page.");
        } catch (Exception e) {
            Assert.fail("Back to products button not found on detail page.");
        }

        // 3. Click the Back button
        driver.findElement(productsPage.backToProductsButton).click();

        // 4. Assert return to the Products page
        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                "Failed to return to the Products page after clicking 'Back'.");
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
