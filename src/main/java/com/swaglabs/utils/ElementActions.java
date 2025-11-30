package com.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ElementActions {
    private ElementActions(){};
    //send keys and clicking class

    public static void sendData(WebDriver driver, By locator , String data){
        WaitClass.waitForElementVisible(driver, locator);

        Scrolling.scrollToElement(driver, locator);

        driver.findElement(locator).sendKeys(data);

    }
    public static void selectByVisibleText(WebDriver driver, By locator, String visibleText) {
        // 1. Wait for the dropdown element to be visible
        WebElement dropdownElement = WaitClass.waitForElementVisible(driver, locator);

        // 2. Instantiate the Selenium Select class
        Select select = new Select(dropdownElement);

        // 3. Select the option by its visible text
        select.selectByVisibleText(visibleText);
    }
    public static void clickElement(WebDriver driver, By locator){

        WaitClass.waitForElementClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        driver.findElement(locator).click();

    }

}
