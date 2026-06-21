package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.MainPage;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoTest extends tests.CommonBaseTest {

    @Test
    public void clickScooterLogoRedirectsToMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite().clickStatusButton();

        mainPage.clickScooterLogo();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe("https://qa-scooter.praktikum-services.ru/"));

        assertTrue(driver.getCurrentUrl().equals("https://qa-scooter.praktikum-services.ru/"), "Переход на главную не произошел!");
    }

    @Test
    public void clickYandexLogoOpensYandexPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        String mainWindow = driver.getWindowHandle();
        mainPage.clickYandexLogo();

        // 1. Ждем, пока откроется новая вкладка
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        // 2. Переключаемся на неё
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // 3. Вместо urlContains, проверяем заголовок или URL
        // Это более гибкий подход
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(driver -> driver.getCurrentUrl().contains("yandex"));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Мы перешли на страницу: " + currentUrl); // Посмотри в консоль, что там написано

        // Обновленный блок в LogoTest.java:
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(driver -> driver.getCurrentUrl().contains("yandex") || driver.getCurrentUrl().contains("dzen"));

        assertTrue(currentUrl.contains("yandex") || currentUrl.contains("dzen"),
                "Страница Яндекса или Дзен не открылась!");
    }
}