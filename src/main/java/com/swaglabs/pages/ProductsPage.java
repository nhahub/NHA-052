package com.swaglabs.pages;

import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.WaitClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    // Locators for Test Cases Sorting & Detail View
    private final By sortDropdown = By.className("product_sort_container");
    private final By itemNames = By.className("inventory_item_name");
    private final By itemPrices = By.className("inventory_item_price");
    private final By firstItemNameLink = By.id("item_4_title_link"); // Specific link for Backpack
    public final By backToProductsButton = By.id("back-to-products"); // For product detail page



    // Actions
    public void clickAddToCartForFirstItem() {
        ElementActions.clickElement(driver, firstItemAddToCartButton);
    }

    public void clickShoppingCart() {
        ElementActions.clickElement(driver, shoppingCartLink);
    }

    public void selectSortOption(String optionText) {
        ElementActions.selectByVisibleText(driver, sortDropdown, optionText);
    }

    public void clickFirstProductName() {
        ElementActions.clickElement(driver, firstItemNameLink);
    }

    // Validations
    public boolean isProductsPageDisplayed() {
        try {
            WaitClass.waitForElementVisible(driver, productsHeader);
            return true;
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isShoppingCartBadgeCountCorrect(String expectedCount) {
        try {
            WebElement badge = driver.findElement(shoppingCartBadge);
            return badge.isDisplayed() && badge.getText().equals(expectedCount);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public List<String> getItemNames() {
        return driver.findElements(itemNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getItemPrices() {
        return driver.findElements(itemPrices).stream()
                .map(e -> e.getText().replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    /**
     * Asserts if the current list of items is sorted alphabetically (A to Z).
     */
    public boolean isSortedAlphabetically(List<String> actualNames) {
        List<String> sortedNames = new ArrayList<>(actualNames);
        sortedNames.sort(Comparator.naturalOrder()); // A to Z sort
        return actualNames.equals(sortedNames);
    }

    /**
     * Asserts if the current list of items is sorted by price from high to low.
     */
    public boolean isSortedByPriceHighToLow(List<Double> actualPrices) {
        List<Double> sortedPrices = new ArrayList<>(actualPrices);
        sortedPrices.sort(Comparator.reverseOrder()); // Desc
        return actualPrices.equals(sortedPrices);
    }
}
