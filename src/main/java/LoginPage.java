import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By login = By.id("login-button");

//    public void userName(String userName)
//    {
//      driver.findElement(username).sendKeys(userName);
//    }
//    public void enterPassword(String passwrod)
//    {
//        driver.findElement(password).sendKeys(passwrod);
//    }
//    public void logIn()
//    {
//        driver.findElement(login).click();
//    }
    public void login(String userName,String pass){
        driver.findElement(username).sendKeys(userName);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(login).click();
    }


}
