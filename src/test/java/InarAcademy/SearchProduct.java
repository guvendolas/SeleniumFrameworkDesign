package InarAcademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchProduct {
    WebDriver driver;


    @BeforeSuite
    public void setUpSuite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
    }

    @BeforeTest()
    public void setUpTest() {
        driver.get("https://automationexercise.com/");
    }

    @Test(priority = 1)
    public void clickProductsPageAndConfirm() {
        driver.findElement(By.xpath("(//ul//li//a)[2]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h2[contains(text(),'All Products')]")).getText(), "ALL PRODUCTS");
    }
    @Test(priority = 2)
    public void searchProduct(){
        driver.findElement(By.id("search_product")).sendKeys("Men Tshirt");
        driver.findElement(By.id("submit_search")).click();
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,200)");
    }
    @Test(priority = 3)
    public void confirmSearchProduct() throws InterruptedException {

        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//div[@class='productinfo text-center']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("(//p[contains(text(),'Men Tshirt')])[2]")).getText(),"Men Tshirt");
    }


    @AfterSuite
    public void tearUp() {
        driver.quit();
    }
}
