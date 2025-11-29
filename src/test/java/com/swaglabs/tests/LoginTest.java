package com.swaglabs.tests;

import com.swaglabs.drivers.DriverSetup;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.BrowserActions;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class LoginTest {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    public WebDriver driver;

//DATA DRIVEN TEST FROM EXCEL

    //data provider returns a 2d arr of rows and cols of data
    @DataProvider(name = "loginDataProviderMethod")
    public Object[][] getLoginData() throws IOException {
        String filePath = "src/loginCredentials.xlsx";
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = 3;

        // Initialize a 2D array to hold the test data. We skip the header row.
        Object[][] data = new Object[rowCount - 1][colCount];

        // Loop through all rows, starting from the second row (index 1) to skip the header
        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            // Get data from columns for each row
            data[i - 1][0] = row.getCell(0).getStringCellValue(); // Username
            data[i - 1][1] = row.getCell(1).getStringCellValue(); // Password
            data[i - 1][2] = row.getCell(2).getStringCellValue(); // ExpectedResult

        }

        workbook.close();
        fis.close();
        return data;
    }

    //feed on data from data provider
    @Test(dataProvider = "loginDataProviderMethod")
    public void dataDrivenLoginTest(String username, String password, String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);

        // 1. Perform the Login Action
        loginPage.login(username, password);

        // 2. Perform Validation based on the 'ExpectedResult' column from Excel
        System.out.println("Testing with User: " + username + ", Expected: " + expectedResult);

        if (expectedResult.startsWith("SUCCESS")) {
            Assert.assertTrue(loginPage.isProductsPageDisplayed(),
                    "Login should have been successful for user: " + username);

        } else if (expectedResult.startsWith("FAILURE")) {
            // Check for a generic error message display
            Assert.assertTrue(loginPage.isErrorMessageDisplayed("Epic sadface"),
                    "Login should have failed for user: " + username + " but error message not found.");

        }
    }


//    @Test
//    public void loginTest1(){
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("standard_user","secret_sauce");
//    }
//    @Test
//    public void loginTest2(){
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("locked_out_user","secret_sauce");
//    }
//    @Test
//    public void loginTest3(){
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("problem_user","secret_sauce");
//    }
//    @Test
//    public void loginTest4(){
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("performance_glitch_user","secret_sauce");
//    }    @Test
//    public void loginTest5(){
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("error_user","secret_sauce");
//    }
//    @Test
//    public void loginTest6(){
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("visual_user","secret_sauce");
//    }
    // Test for successful login
//    @Test
//    public void testSuccessfulLogin() {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("standard_user", "secret_sauce");
//
//        // Assert that the successful page element is displayed
//        Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Verification failed: User did not navigate to the Products page.");
//    }
//
//    // Test for locked-out user
//    @Test
//    public void testLockedOutUser() {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("locked_out_user", "secret_sauce");
//        String expectedError = "Epic sadface: Sorry, this user has been locked out.";
//
//        // Assert that the correct error message is displayed
//        Assert.assertTrue(loginPage.isErrorMessageDisplayed(expectedError), "Verification failed: Expected error message was not displayed.");
//    }

    @BeforeMethod
    public void setUp() {
        driver = DriverSetup.driverSetup();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
//        loginPage.login();
//        loginPage.login("standard_user", "secret_sauce");
    }

    //close chrome tabs after test cases
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
