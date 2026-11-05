package triangles;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import com.codeborne.selenide.SelenideElement;


public class SelenideTrianglesTest {

    @Test
	public void trianglesTest1() throws Exception {
		open("https://do.tusur.ru/qa-test2/");
		$(By.name("a")).sendKeys("4");
		$(By.name("b")).sendKeys("5");
		$(By.name("c")).sendKeys("6");
		$(By.name("calc")).click();
		SelenideElement expected=$(By.cssSelector(".result > div:nth-child(2)"));
		expected.shouldHave(text("Треугольник остроугольный"));
    }
    @Test
	public void trianglesTest2() throws Exception {
		open("https://do.tusur.ru/qa-test2/");
		$(By.name("a")).sendKeys("3");
		$(By.name("b")).sendKeys("4");
		$(By.name("c")).sendKeys("6");
		$(By.name("calc")).click();
		SelenideElement expected=$(By.cssSelector(".result > div:nth-child(2)"));
		expected.shouldHave(text("Треугольник тупоугольный"));
    }
    @Test
	public void trianglesTest3() throws Exception {
		open("https://do.tusur.ru/qa-test2/");
		$(By.name("a")).sendKeys("3");
		$(By.name("b")).sendKeys("5");
		$(By.name("c")).sendKeys("9");
		$(By.name("calc")).click();
		SelenideElement expected=$(By.cssSelector(".text-danger"));
		expected.shouldHave(text("Треугольник не возможно построить"));
    }
}
