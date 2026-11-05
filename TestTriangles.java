package triangles;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestInstance(Lifecycle.PER_CLASS)
public class TestTriangles {

	 private WebDriver driver;
	 private WebDriverWait wait;
	 
	  @BeforeAll
	  public  void setUp() {
		  
		  driver = new ChromeDriver();
		  wait=new WebDriverWait (driver, Duration.ofSeconds(30));
	  	    
	  }
	  
	  @Test
	  public void trianglesTest1() {
	    driver.get("https://do.tusur.ru/qa-test2/");
	    driver.findElement(By.name("a")).click();
	    driver.findElement(By.name("a")).sendKeys("14");
	    driver.findElement(By.name("b")).click();
	    driver.findElement(By.name("b")).sendKeys("14");
	    driver.findElement(By.name("c")).click();
	    driver.findElement(By.name("c")).sendKeys("14");
	    driver.findElement(By.name("calc")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".result > div:nth-child(2)")));
	   assertEquals(driver.findElement(By.cssSelector(".result > div:nth-child(2)")).getText(),"Треугольник равносторонний");
	  }
	  
	  @Test
	  public void trianglesTest2() {
		    driver.get("https://do.tusur.ru/qa-test2/");
		    driver.findElement(By.name("a")).click();
		    driver.findElement(By.name("a")).sendKeys("30");
		    driver.findElement(By.name("b")).click();
		    driver.findElement(By.name("b")).sendKeys("30");
		    driver.findElement(By.name("c")).click();
		    driver.findElement(By.name("c")).sendKeys("50");
		    driver.findElement(By.name("calc")).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".result > div:nth-child(2)")));
		   assertEquals(driver.findElement(By.cssSelector(".result > div:nth-child(2)")).getText(),"Треугольник равнобедренный");
		  }
	  @Test
	  public void trianglesTest3() {
		    driver.get("https://do.tusur.ru/qa-test2/");
		    driver.findElement(By.name("a")).click();
		    driver.findElement(By.name("a")).sendKeys("3");
		    driver.findElement(By.name("b")).click();
		    driver.findElement(By.name("b")).sendKeys("4");
		    driver.findElement(By.name("c")).click();
		    driver.findElement(By.name("c")).sendKeys("5");
		    driver.findElement(By.name("calc")).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".result > div:nth-child(2)")));
		   assertEquals(driver.findElement(By.cssSelector(".result > div:nth-child(2)")).getText(),"Треугольник прямоугольный");
		  }

	@AfterAll
	  public void tearDown() {
	    driver.quit();
	  }
}


