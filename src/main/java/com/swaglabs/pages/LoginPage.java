package com.swaglabs.pages;

import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.WaitClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.file.Watchable;


public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }
    //locators : those who locate elements in website's html
    private final By username = By.id("user-name");//items' id's in website
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By errorMessageContainer = By.className("error-message-container");
    private final By productsHeader = By.className("title");

    public void navigateToLoginPage(){
        BrowserActions.openURL(driver, "https://saucedemo.com/");
    }

    //actions
    public void enterUserName(String userName){
        ElementActions.sendData(driver, username, userName);
    }
    public void enterPassword(String passwordStr){
            ElementActions.sendData(driver,password,passwordStr);
    }

    public void clickLogin(){
        ElementActions.clickElement(driver, loginBtn);
    }
    public void login(String username, String password){
        enterUserName(username);
        enterPassword(password);
        clickLogin();
    }

    //validations
    public boolean isErrorMessageDisplayed(String expectedErrorText) {
        try {
            WebElement errorElement = driver.findElement(errorMessageContainer);
            return errorElement.isDisplayed() && errorElement.getText().contains(expectedErrorText);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false; // Error element not even present
        }
    }
    public boolean isProductsPageDisplayed() {
        try {
            WaitClass.waitForElementVisible(driver, productsHeader);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
