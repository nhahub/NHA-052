package com.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitClass {
    //wait for elements to be
    //1 .present 2.visible 3.clickable

    //prevent constructor
    private WaitClass() {
    }


    public static WebElement waitForElementPresent(WebDriver driver, By locator) {   //explicit wait on elem to become present
        //max wait 5 secs , if not found through a Timeout exception

        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(
                        //generic driver , avoided naming it driver
                        genericDriver -> genericDriver.findElement(locator)
                );
    }

    //wait for element to be visible
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


}
