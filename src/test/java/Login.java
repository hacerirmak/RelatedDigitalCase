import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class Login {

    WebDriver driver;
    WebDriverWait wait;


    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 15, 15);
        driver.get("https://console.euromsg.com/login");
    }

    @Test
    public void basariliLogintest() throws IOException {
        driver.findElement(By.id("exampleInputEmail1")).sendKeys("hacerirmak@outlook.com");
        driver.findElement(By.id("exampleInputPassword1")).sendKeys("Test12345");
        new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[name^='a-'][src^='https://www.google.com/recaptcha/api2/anchor?']")));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable((By.xpath("//span[@id='recaptcha-anchor']")))).click();

        WebElement root = driver.findElement(By.tagName("app-root"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement shadowDom1 = (WebElement) js.executeAsyncScript("return arguments[0].shadowRoot", root);
        WebElement app = shadowDom1.findElement(By.className("btn btn-block btn-primary mt-3"));
        app.click();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));
        System.out.println("Başarılı login olunmuştur.");

        driver.findElement(By.xpath("/html/body/app-root/app-layout/div/section/div/app-home/div/div/div/div[2]/div[1]/div/b"));
    }

    @Test
    public void basarisizLogintest() {
        driver.findElement(By.id("exampleInputEmail1")).sendKeys("yanlıis@outlook.com");
        driver.findElement(By.id("exampleInputPassword1")).sendKeys("Test12345");
        new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[name^='a-'][src^='https://www.google.com/recaptcha/api2/anchor?']")));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable((By.xpath("//span[@id='recaptcha-anchor']")))).click();
    }
}
