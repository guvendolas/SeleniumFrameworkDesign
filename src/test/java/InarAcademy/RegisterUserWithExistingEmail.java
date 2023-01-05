package InarAcademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterUserWithExistingEmail {
    WebDriver driver;
    WebElement name;
    WebElement email;

    WebElement signup;

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
        Assert.assertEquals(driver.findElement(By.xpath("(//div//h2)[3]")).getText(), "New User Signup!");
        name = driver.findElement(By.xpath("//input[@type='text']"));
        email = driver.findElement(By.xpath("//input[@data-qa='signup-email']"));
        signup = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
    }

    @Test
    public void fillTheNameAndEmailBox() {

        name.sendKeys("guven");
        email.sendKeys("guvenn@gmail.com");
        signup.click();
    }

    @Test
    public void checkTheExistEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean exist = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//p[normalize-space()='Email Address already exist!']")));
        Assert.assertTrue(exist);
    }

    @AfterSuite
    public void tearUp() {
        driver.quit();
    }
}
