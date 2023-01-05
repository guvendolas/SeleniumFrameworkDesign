package InarAcademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class VerifyAllProductsAndProductDetailPage {
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

    @Test(priority = 1)
    public void clickProductsPageAndConfirm() {
        driver.findElement(By.xpath("(//ul//li//a)[2]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h2[contains(text(),'All Products')]")).getText(), "ALL PRODUCTS");
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,200)");

    }

    @Test(priority = 2)
    public void productDetailsAndConfirm() {
        WebElement productDetailsBar = driver.findElement(By.cssSelector("a[href='/product_details/1']"));
        productDetailsBar.click();
        Assert.assertEquals(driver.findElement(By.xpath("//h2[contains(text(),'Blue Top')]")).getText(),"Blue Top");
    }

    @AfterSuite
    public void tearUp() {
        driver.quit();
    }
}

