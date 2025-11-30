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

    // Locators for NEW Test Cases (Removal & Navigation)
    private final By firstItemRemoveButton = By.id("remove-sauce-labs-backpack");
    private final By continueShoppingButton = By.id("continue-shopping");
    private final By itemImage = By.className("inventory_item_img");

    // Actions
    public void clickCheckout() {
        ElementActions.clickElement(driver, checkoutButton);
    }

    public void clickContinueShopping() {
        ElementActions.clickElement(driver, continueShoppingButton);
    }

    public void removeItem() {
        ElementActions.clickElement(driver, firstItemRemoveButton);
    }

    // Validations
    public boolean isCartPageDisplayed() {
        try {
            WaitClass.waitForElementVisible(driver, cartHeader);
            return driver.findElement(cartHeader).getText().equals("Your Cart");
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isBackpackItemPresent() {
        try {
            return driver.findElement(backpackItemTitle).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public int getCartItemCount() {
        return driver.findElements(cartItem).size();
    }

    public boolean areItemImagesDisplayed() {
        // Checks that at least one image is present and visible
        try {
            return driver.findElement(itemImage).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}