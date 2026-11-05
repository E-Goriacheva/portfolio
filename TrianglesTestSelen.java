package triangles;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Attachment;

@TestInstance(Lifecycle.PER_CLASS)
 public class trianglesTestSelen {

	
	 private WebDriver driver;
	 private WebDriverWait wait;
	 
	 
	@BeforeAll
	  public  void setUp() {
		  System.setProperty("webdriver.chrome.driver",
					"D:\\Users\\Admin\\Downloads\\driver\\chromedriver.exe");
		  ChromeOptions options = new ChromeOptions();
		  options.addArguments("--start-maximized");
		  driver = new ChromeDriver(options);
		  wait=new WebDriverWait (driver, Duration.ofSeconds(10));
	   	  }
	  @Attachment(value="image", type="image/png")
	  public static byte [] takeScreenshot (WebDriver driver) {
		  return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	  }
	  @Test
	  public void trianglesTest1() {
	    driver.get("https://do.tusur.ru/qa-test2/");
	    driver.findElement(By.name("a")).sendKeys("14");
	    driver.findElement(By.name("b")).sendKeys("14");
	    driver.findElement(By.name("c")).sendKeys("14");
	    driver.findElement(By.name("calc")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".result > div:nth-child(2)")));
	    takeScreenshot (driver);
	   assertEquals(driver.findElement(By.cssSelector(".result > div:nth-child(2)")).getText(),"Треугольник равносторонний");
	  }
	  
	

	@AfterAll
	  public void tearDown() {
	    driver.quit();
	  }
}
