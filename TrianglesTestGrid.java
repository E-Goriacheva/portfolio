package triangles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.parallel.Execution;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.jupiter.api.parallel.*;

@Execution(ExecutionMode.CONCURRENT)
@TestInstance(Lifecycle.PER_CLASS)
public class TrianglesTestGrid {
	
	private WebDriver driver1, driver2;
	String nodeURL;
		 
	 @BeforeAll
	  public  void setUp() throws MalformedURLException, URISyntaxException{
		  ChromeOptions opt1=new ChromeOptions();
		  FirefoxOptions opt2= new FirefoxOptions(); 
		  String nodeURL="http://localhost:4444/";
		 
		 driver1 = new RemoteWebDriver(URI.create(nodeURL).toURL(), opt1);
		 driver2 = new RemoteWebDriver(URI.create(nodeURL).toURL(), opt2);
		  		 	    
	  }
	  
	  
	 @Test
	  public void trianglesTest1() {
	    driver1.get("https://do.tusur.ru/qa-test2/");
	    driver1.findElement(By.name("a")).click();
	    driver1.findElement(By.name("a")).sendKeys("14");
	    driver1.findElement(By.name("b")).click();
	    driver1.findElement(By.name("b")).sendKeys("14");
	    driver1.findElement(By.name("c")).click();
	    driver1.findElement(By.name("c")).sendKeys("14");
	    driver1.findElement(By.name("calc")).click();
	 
	   String expected=driver1.findElement(By.cssSelector(".result > div:nth-child(2)")).getText();
       Long id=Thread.currentThread().threadId();
	   System.out.println("Chrome: id=" + id);
	   assertEquals("Треугольник равносторонний", expected);
	   
	  }
	  
	  @Test
	  public void trianglesTest2() {
		    driver2.get("https://do.tusur.ru/qa-test2/");
		    driver2.findElement(By.name("a")).click();
		    driver2.findElement(By.name("a")).sendKeys("30");
		    driver2.findElement(By.name("b")).click();
		    driver2.findElement(By.name("b")).sendKeys("30");
		    driver2.findElement(By.name("c")).click();
		    driver2.findElement(By.name("c")).sendKeys("50");
		    driver2.findElement(By.name("calc")).click();
		    String expected=driver2.findElement(By.cssSelector(".result > div:nth-child(2)")).getText();
		    Long id=Thread.currentThread().threadId();
			System.out.println("Firefox: id=" + id);
			assertEquals("Треугольник равнобедренный", expected);
		   
		  }
	 @Test
	 public void trianglesTest3() {
		    driver1.get("https://do.tusur.ru/qa-test2/");
		    driver1.findElement(By.name("a")).click();
		    driver1.findElement(By.name("a")).sendKeys("3");
		    driver1.findElement(By.name("b")).click();
		    driver1.findElement(By.name("b")).sendKeys("4");
		    driver1.findElement(By.name("c")).click();
		    driver1.findElement(By.name("c")).sendKeys("5");
		    driver1.findElement(By.name("calc")).click();
		    String expected=driver1.findElement(By.cssSelector(".result > div:nth-child(2)")).getText();
		    Long id=Thread.currentThread().threadId();
		    System.out.println("Chrome: id=" + id);
		    assertEquals("Треугольник прямоугольный", expected);
		   
		 }

	@AfterAll
	  public void tearDown() {
	    driver1.quit();
	    driver2.quit();
	  }
}


