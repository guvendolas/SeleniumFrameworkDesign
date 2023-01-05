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

public class SubscriptionInHomePage {
    WebDriver driver;
    WebElement email;


    @BeforeSuite
    public void setUpSuite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
    }

    @BeforeTest()
    public void setUpTest() throws InterruptedException {
        driver.get("https://automationexercise.com/");
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,8500)");
        Thread.sleep(3000);
        email = driver.findElement(By.id("susbscribe_email"));
    }

    @Test(priority = 1)
    public void enterEmailAndClick() throws InterruptedException {
        email.sendKeys("guvenn@gmail.com");
        driver.findElement(By.id("subscribe")).click();
    }
    @Test(priority = 2)
    public void confirmSubscribe(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5000));
       boolean s =  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='alert-success alert']")));
        Assert.assertTrue(s);
    }
    @AfterSuite
    public void tearUp() {
        driver.quit();
    }
}
