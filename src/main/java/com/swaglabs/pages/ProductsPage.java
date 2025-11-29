package com.swaglabs.pages;

import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.WaitClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage {
    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By productsHeader = By.className("title");
    // Locator for the "Add to cart" button for a specific item (e.g., the first item)
    private final By firstItemAddToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By shoppingCartLink = By.className("shopping_cart_link");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");

    // Actions
    public void clickAddToCartForFirstItem() {
        ElementActions.clickElement(driver, firstItemAddToCartButton);
    }

    public void clickShoppingCart() {
        ElementActions.clickElement(driver, shoppingCartLink);
    }

    // Validations
    public boolean isProductsPageDisplayed() {
        try {
            WaitClass.waitForElementVisible(driver, productsHeader);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Checks if the shopping cart badge displays the expected item count.
     * @param expectedCount The number expected on the cart badge (e.g., "1").
     * @return true if the badge is displayed and has the expected text.
     */
    public boolean isShoppingCartBadgeCountCorrect(String expectedCount) {
        try {
            WebElement badge = driver.findElement(shoppingCartBadge);
            return badge.isDisplayed() && badge.getText().equals(expectedCount);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
