package triangles;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class TestDnsSelen1 {

    
    // Настройка Selenide
   
    @BeforeAll
    public static void setUp() {
     //  Глобальные настройки Selenide 
        Configuration.timeout = 10000;           // Ожидание элементов по умолчанию — 10 секунд
        Configuration.pageLoadTimeout = 60000;   // Ожидание загрузки страницы — 60 секунд 
        Configuration.screenshots = true;        // Автоматические скриншоты при падении теста
        Configuration.savePageSource = false;   // HTML при падении не сохраняем — экономия мест
        Configuration.headless = false;         // Браузер открывается обычным окном на экране
    
        // Настройка подключения к уже запущенному Chrome 
       
        ChromeOptions options = new ChromeOptions();
        // Chrome должен быть запущен командой:
        // "C:\Program Files\Google\Chrome\Application\chrome.exe"
        //     --remote-debugging-port=9222 --user-data-dir="C:\chrome-debug-profile"
        // и на нём вручную открыт https://www.dns-shop.ru/ (Qrator пройден).
        // Подключаемся к уже запущенному вручную Chrome на порту 9222
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Обязательно для Chrome 111+, иначе будет ошибка 
        options.addArguments("--remote-allow-origins=*");

        // Создаём ChromeDriver напрямую
        ChromeDriver driver = new ChromeDriver(options);

        // Регистрируем драйвер в Selenide 
        WebDriverRunner.setWebDriver(driver);
    }

   
    // Открытие формы входа перед каждым тестом
    
    @BeforeEach
    public void openLoginForm() {
        // 1. Открыть главную (браузер уже запущен вручную, Qrator пройден)
        open("https://www.dns-shop.ru/");

        // 2. Клик по иконке профиля
        $(".user-profile__login").shouldBe(clickable).click();

        // 3. Клик по кнопке «Войти» в попапе
        $("button.user-profile__guest-button").shouldBe(clickable).click();

        // 4. Вкладка «Код или пароль»
        $("button[data-base-tab-id='entryOrRegistry']").shouldBe(clickable).click();

        // 5. Кнопка «Войти с паролем»
        $x("//button[.//span[text()='Войти с паролем']]").shouldBe(clickable).click();

        // 6. Ждём появления поля Email
        $("input[aria-label='Телефон или e mail']").shouldBe(visible);
    }

    
    // Скриншот для Allure
   
    @Attachment(value = "image", type = "image/png")
    public byte[] takeScreenshot() {
        return Selenide.screenshot(OutputType.BYTES);
    }

    
    // Выход после теста
        
    @AfterEach
    public void tearDown() {
        SelenideElement logout = $x("//a[text()='Выйти']");
        if (logout.is(visible, Duration.ofSeconds(3))) {
            logout.click();
            }
        
     }

    
    // Вспомогательные методы - локаторы формы
    /** Поле ввода email/телефона в форме входа. */
    private SelenideElement emailField() {
        return $("input[aria-label='Телефон или e mail']");
    }
    /** Поле ввода пароля. */
    private SelenideElement passwordField() {
        return $("input[aria-label='Введите пароль']");
    }
    /** Кнопка отправки формы входа. */
    private SelenideElement submitButton() {
        return $("button[class*='form-entry-with-password__button_main']");
    }
    /** Блок с сообщением об ошибке (E-mail/ телефон или пароль указаны неверно). */
    private SelenideElement errorMessage() {
        return $("p[class*='form-entry-with-password__error']");
    }

   
    // Тесты
    /**
     * Позитивный сценарий: корректные email и пароль.
     * Ожидаем успешный вход и появление имени пользователя в профиле.
     */
    @Test
    public void PositiveTest() {
    	// Вводим корректные учётные данные
        emailField().shouldBe(visible).setValue("modestg81@mail.ru");
        passwordField().shouldBe(visible).setValue("RNF_2956");
        // Отправляем форму
        submitButton().shouldBe(clickable).click();
        // Ждём появления аватара — признак успешного входа (до 15 сек)
        $(".user-profile__avatar").shouldBe(visible, Duration.ofSeconds(15));
        $(".user-profile__avatar").click();
        // Проверяем, что в профиле отображается ожидаемое имя пользователя
        $(".user-profile__username").shouldBe(visible).shouldHave(text("Пришелец-IN61998"));
        // Прикрепляем скриншот к отчёту
        takeScreenshot();
    }
    /**
     * Негативный сценарий 1: несуществующий email + неверный пароль.
     * Ожидаем сообщение об ошибке.
     */
    @Test
    public void NegativeTest1() {
        emailField().setValue("kastg81@mail.ru");
        passwordField().setValue("RNF_2956154");
        submitButton().click();
     // Ждём появления сообщения об ошибке и проверяем его текст
        errorMessage().shouldBe(visible)
                      .shouldHave(text("E-mail/ телефон или пароль указаны неверно."));

        takeScreenshot();
     }
    /**
     * Негативный сценарий 2: корректный email + неверный пароль.
     * Ожидаем сообщение об ошибке.
     */
    @Test
    public void NegativeTest2() {
        emailField().setValue("modestg81@mail.ru");
        passwordField().setValue("RNF_2956154");
        submitButton().click();

        errorMessage().shouldBe(visible)
                      .shouldHave(text("E-mail/ телефон или пароль указаны неверно."));

        takeScreenshot();
    }
    /**
     * Негативный сценарий 3: несуществующий email + корректный пароль.
     * Ожидаем сообщение об ошибке.
     */
    @Test
    public void NegativeTest3() {
        emailField().setValue("kastg81@mail.ru");
        passwordField().setValue("RNF_2956");
        submitButton().click();

        errorMessage().shouldBe(visible)
                      .shouldHave(text("E-mail/ телефон или пароль указаны неверно."));

        takeScreenshot();
    }
}