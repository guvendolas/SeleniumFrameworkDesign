package InarAcademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginUserWithCorrectEmailAndPassword {
    WebDriver driver;
    WebElement email;
    WebElement password;
    WebElement loginButton;

    @BeforeSuite
    public void setUpSuite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
    }

    @BeforeTest
    public void setUpTest() {

        driver.get("https://automationexercise.com/");
        driver.findElement(By.cssSelector("a[href='/login']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("(//div//h2)[1]")).getText(), "Login to your account");
        email = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
        password = driver.findElement(By.xpath("//input[@data-qa='login-password']"));
        loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
    }

    @Test
    public void SignWithCorrectEmailAndPassword() {
       email.sendKeys("guvenn@gmail.com");
       password.sendKeys("123.Gg");
       loginButton.click();
    }

    @Test
    public void checkIsLogin() {
        Assert.assertEquals(driver.findElement(By.xpath("(//ul//li//a)[10]")).getText(),"Logged in as guven");
    }
    @AfterSuite
    public void tearUp(){
        driver.quit();
    }
}
