package com.swaglabs.pages;

import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.WaitClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By cartHeader = By.className("title");
    private final By checkoutButton = By.id("checkout");
    // A generic locator for an item in the cart, used for verification
    private final By cartItem = By.className("cart_item");
    // Specific locator for the backpack item title
    private final By backpackItemTitle = By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Backpack']");

    // Actions
    public void clickCheckout() {
        ElementActions.clickElement(driver, checkoutButton);
    }

    // Validations
    public boolean isCartPageDisplayed() {
        try {
            WaitClass.waitForElementVisible(driver, cartHeader);
            return driver.findElement(cartHeader).getText().equals("Your Cart");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Checks if a specific item (Sauce Labs Backpack) is present in the cart.
     * @return true if the item title is visible in the cart list.
     */
    public boolean isBackpackItemPresent() {
        try {
            return driver.findElement(backpackItemTitle).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Counts the total number of unique items currently in the cart.
     * @return The count of cart items.
     */
    public int getCartItemCount() {
        return driver.findElements(cartItem).size();
    }
}