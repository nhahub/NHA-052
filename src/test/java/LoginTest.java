import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    public WebDriver driver;

    @Test
    public void loginTest1(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user","secret_sauce");
    }
    @Test
    public void loginTest2(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user","secret_sauce");
    }
    @Test
    public void loginTest3(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("problem_user","secret_sauce");
    }
    @Test
    public void loginTest4(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("performance_glitch_user","secret_sauce");
    }    @Test
    public void loginTest5(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("error_user","secret_sauce");
    }
    @Test
    public void loginTest6(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("visual_user","secret_sauce");
    }


    @BeforeMethod
    public void setUp(){

        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver =new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.saucedemo.com/");
    }
//    @AfterMethod
}
