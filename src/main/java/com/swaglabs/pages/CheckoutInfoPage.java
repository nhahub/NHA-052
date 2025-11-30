package com.swaglabs.pages;

import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.WaitClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutInfoPage {
    WebDriver driver;

    public CheckoutInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By checkoutHeader = By.className("title");
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By zipCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");
    private final By errorMessageContainer = By.cssSelector("[data-test='error']"); // Specific error locator

    // Actions
    public void enterInformation(String firstName, String lastName, String zipCode) {
        ElementActions.sendData(driver, firstNameField, firstName);
        ElementActions.sendData(driver, lastNameField, lastName);
        ElementActions.sendData(driver, zipCodeField, zipCode);
    }

    public void clickContinue() {
        ElementActions.clickElement(driver, continueButton);
    }

    public void clickCancel() {
        ElementActions.clickElement(driver, cancelButton);
    }

    // Validations
    public boolean isCheckoutInfoPageDisplayed() {
        try {
            WaitClass.waitForElementVisible(driver, checkoutHeader);
            return driver.findElement(checkoutHeader).getText().equals("Checkout: Your Information");
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Checks if the required field error message is displayed and contains the expected text.
     */
    public boolean isErrorMessageDisplayed(String expectedErrorText) {
        try {
            WebElement errorElement = driver.findElement(errorMessageContainer);
            return errorElement.isDisplayed() && errorElement.getText().contains(expectedErrorText);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}