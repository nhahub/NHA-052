package com.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scrolling {

    private Scrolling(){};

    //use selenium actions to scroll to elements by executing js code
    public static void scrollToElement(WebDriver driver, By locator) {

        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        String jsCode = "arguments[0].scrollIntoView();";
        jsx.executeScript(jsCode, driver.findElement(locator));

    }
}
