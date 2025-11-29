package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }
    //locators : those who locate elements in website's html
    private final By username = By.id("user-name");//items' id's in website
    private final By password = By.id("password");
    private final By login = By.id("login-button");
    private final By errorMessageContainer = By.className("error-message-container");
    private final By productsHeader = By.className("title");

    //action: click login button
    public void login(String userName,String pass){
        driver.findElement(username).sendKeys(userName);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(login).click();
    }

    /**
     * Checks if a specific error message is displayed on the login page.
     * @return true if the error message is visible and matches the expected text, false otherwise.
     */
    public boolean isErrorMessageDisplayed(String expectedErrorText) {
        try {
            WebElement errorElement = driver.findElement(errorMessageContainer);
            return errorElement.isDisplayed() && errorElement.getText().contains(expectedErrorText);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false; // Error element not present
        }
    }

    /**
     * Checks if the user is on the Products page (successful login).
     * NOTE: This assumes successful login navigates to a page with a "Products" header.
     * @return true if the Products header is visible, false otherwise.
     */
    public boolean isProductsPageDisplayed() {
        try {
            // Wait for the element to be present/visible , like implicit wait
            return driver.findElement(productsHeader).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


}
