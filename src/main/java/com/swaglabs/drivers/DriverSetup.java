package com.swaglabs.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverSetup {
    private static WebDriver driver;
    public static WebDriver driverSetup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");

        // Setup the WebDriver instance
        WebDriver driver = new ChromeDriver(chromeOptions);

        // Implicit wait (used in your original code)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return driver;
    }
}
