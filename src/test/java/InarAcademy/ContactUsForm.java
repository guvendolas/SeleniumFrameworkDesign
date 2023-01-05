package InarAcademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
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

public class ContactUsForm {
    WebDriver driver;
    WebElement name;
    WebElement email;
    WebElement subject;
    WebElement message;


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
        driver.findElement(By.xpath("//a[@href='/contact_us']")).click();
        name = driver.findElement(By.xpath("//input[@data-qa='name']"));
        email = driver.findElement(By.xpath("//input[@data-qa='email']"));
        subject = driver.findElement(By.xpath("//input[@data-qa='subject']"));
        message = driver.findElement(By.id("message"));

    }

    @Test
    public void fillTheBox() {
        name.sendKeys("guven");
        email.sendKeys("guvenn@gmail.com");
        subject.sendKeys("Thanks!");
        message.sendKeys("Thanks for this great page");
    }

    @Test
    public void uploadFile() {
        driver.findElement(By.xpath("//input[@name='upload_file']")).sendKeys("C:\\Users\\guven\\Desktop\\Selenium\\SeleniumFrameworkDesign\\src\\test\\file\\ThanksText.txt");
        driver.findElement(By.xpath("//input[@data-qa='submit-button']")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='status alert alert-success']")).getText(), "Success! Your details have been submitted successfully.");

        ////a[@class='btn btn-success']
    }

    @Test
    public void confirmTheReturnHomePage() {
        driver.findElement(By.xpath("(//div//a)[10]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("(//ul//li//a)[1]")).getText(), "Home");
    }
    @AfterSuite
    public void tearUp() {
        driver.quit();
    }
}
