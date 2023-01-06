package InarAcademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class deneme {
    public static void main(String[] args){


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));


        driver.get("https://automationexercise.com/");

        driver.findElement(By.xpath("(//ul//li//a)[2]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h2[contains(text(),'All Products')]")).getText(), "ALL PRODUCTS");
        //you have to scroll your page. If you don't do this your code cant see the products
        org.openqa.selenium.JavascriptExecutor scroll = (org.openqa.selenium.JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0,200)");


        //find products name get first product and click.
        List<WebElement> itemsNameElement = driver.findElements(By.xpath("//div[@class='productinfo text-center']//p"));
        WebElement firstElement = itemsNameElement.get(0);
        firstElement.findElement(By.xpath("//div//a[@class='btn btn-default add-to-cart']")).click();

        //view the cart
        driver.findElement(By.xpath("//div[@class='modal-body']//u")).click();


        //confirm in the cart product
        String productName = driver.findElement(By.xpath("//div[@class='table-responsive cart_info']//h4")).getText();
        Assert.assertEquals(productName,"Blue Top");


        driver.quit();
    }
}
