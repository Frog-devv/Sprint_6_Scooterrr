package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage {
    private WebDriver driver;

    // Локаторы для заказа
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By headerOrderButton = By.className("Button_Button__ra12g");
    private final By middleOrderButton = By.className("Button_Middle__1CSJM");

    private final By scooterLogo = By.xpath(".//a[@href='/']");

    // Локаторы для FAQ
    private final String[] dropDownQuestionsArray = {
            "accordion__heading-0", "accordion__heading-1", "accordion__heading-2", "accordion__heading-3",
            "accordion__heading-4", "accordion__heading-5", "accordion__heading-6", "accordion__heading-7"};
    private final String[] dropDownAnswersArray = {
            "accordion__panel-0", "accordion__panel-1", "accordion__panel-2", "accordion__panel-3",
            "accordion__panel-4", "accordion__panel-5", "accordion__panel-6", "accordion__panel-7"};

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public MainPage openSite() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        return this;
    }

    public MainPage clickCookieButton() {
        try { driver.findElement(cookieButton).click(); } catch (Exception ignored) {}
        return this;
    }

    public void clickOrderButton(String position) {
        if (position.equals("header")) {
            driver.findElement(headerOrderButton).click();
        } else {
            WebElement element = driver.findElement(middleOrderButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            element.click();
        }
    }


    public void scrollPageToEndOfList() {
        WebElement element = driver.findElement(By.className("Home_FAQ__3uVm4"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickQuestionArrow(int index) {
        WebElement element = driver.findElement(By.id(dropDownQuestionsArray[index]));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public void checkTextInOpenPanel(String expectedText, int index) {
        WebElement panel = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(dropDownAnswersArray[index])));

        String actualText = panel.getText().trim();
        assertEquals(expectedText, actualText, "Текст ответа в блоке " + index + " не совпадает!");
    }

    private final By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    public void clickScooterLogo() {
        WebElement element = driver.findElement(scooterLogo);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    private final By statusButton = By.className("Header_Link__1TAG7"); // Класс кнопки "Статус заказа"

    public void clickStatusButton() {
        driver.findElement(statusButton).click();
    }



}