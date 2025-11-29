package com.swaglabs.utils;

import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private BrowserActions(){};
    public static void openURL(WebDriver driver,String url){
        driver.get(url);
    }
}
