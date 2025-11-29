package com.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementActions {
    private ElementActions(){};
    //send keys and clicking class

    public static void sendData(WebDriver driver, By locator , String data){
        WaitClass.waitForElementVisible(driver, locator);

        Scrolling.scrollToElement(driver, locator);

        driver.findElement(locator).sendKeys(data);

    }

    public static void clickElement(WebDriver driver, By locator){

        WaitClass.waitForElementClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        driver.findElement(locator).click();

    }

}
