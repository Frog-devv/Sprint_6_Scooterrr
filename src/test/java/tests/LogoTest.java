package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.MainPage;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoTest extends tests.CommonBaseTest {

    @Test
    public void clickScooterLogoRedirectsToMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite().clickStatusButton();

        mainPage.clickScooterLogo();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe("https://qa-scooter.praktikum-services.ru/"));

        assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl(), "Переход на главную не произошел!");
    }

    @Test
    public void clickYandexLogoOpensYandexPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();

        String mainWindow = driver.getWindowHandle();
        mainPage.clickYandexLogo();


        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.numberOfWindowsToBe(2));


        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }



        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(driver -> driver.getCurrentUrl().contains("yandex"));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Мы перешли на страницу: " + currentUrl); // Посмотри в консоль, что там написано


        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(driver -> driver.getCurrentUrl().contains("yandex") || driver.getCurrentUrl().contains("dzen"));

        assertTrue(currentUrl.contains("yandex") || currentUrl.contains("dzen"),
                "Страница Яндекса или Дзен не открылась!");
    }
}