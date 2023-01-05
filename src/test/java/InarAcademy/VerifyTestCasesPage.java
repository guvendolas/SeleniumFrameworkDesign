package InarAcademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;

public class VerifyTestCasesPage {
    WebDriver driver;


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
    }

    @Test
    public void confirmTheTestCasePage() throws InterruptedException {
        driver.findElement(By.xpath("(//ul//li//a)[5]")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//b[contains(text(),'Test Cases')]")).getText(), "TEST CASES");

    }
    // dismiss-button id
    // /html[1]/body[1] xpath

    @AfterSuite
    public void tearUp() {
        driver.quit();
   }
}
