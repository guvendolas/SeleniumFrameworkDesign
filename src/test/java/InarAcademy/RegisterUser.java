package InarAcademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterUser {
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
    public void clickSignupAndLoginButton() {

        driver.get("https://automationexercise.com/");
        String pageSignupText = driver.findElement(By.xpath("(//div//h2)[4]")).getText();
        Assert.assertEquals(pageSignupText, "CATEGORY");
        driver.findElement(By.cssSelector("a[href='/login']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("(//div//h2)[3]")).getText(), "New User Signup!");


    }

    @Test
    public void fillTheNameAndEmailBox() {
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Guven");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("Guven@gmail.com");
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h2[@class='title text-center'])[1]")));

    }

    @Test
    public void fillTheInformationForm() {
        driver.findElement(By.id("uniform-id_gender1")).click();
        // driver.findElement(By.id("name")).sendKeys("Guven");
        //driver.findElement(By.xpath("//input[@id='email']")).sendKeys("guven@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123.Gg");
       // List<WebElement> dateOdBirth = driver.findElements(By.xpath("//div[@class='col-xs-4']"));
       /// dateOdBirth.stream().filter(s->s.getText().contains("Day")).collect(Collectors.toList());
       // Select select = new Select((WebElement) dateOdBirth);
        //dateOdBirth.get(1).click();
       // dateOdBirth.stream().anyMatch(s -> s.getText().contains("20"));
    }

    @AfterSuite
    public void afterTest() {

        driver.quit();
    }

}
