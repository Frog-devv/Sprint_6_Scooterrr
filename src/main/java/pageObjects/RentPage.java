package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RentPage {
    private WebDriver driver;
    private final By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalTimeField = By.className("Dropdown-placeholder");
    private final By rentalTime = By.xpath(".//div[text()='сутки']");
    private final By colorBlack = By.id("black");
    private final By orderButton = By.xpath(".//button[contains(@class, 'Button_Middle') and text()='Заказать']");
    private final By confirmButton = By.xpath(".//button[text()='Да']");
    private final By modal = By.xpath(".//div[text()='Заказ оформлен']");

    public RentPage(WebDriver driver) { this.driver = driver; }

    public RentPage sendRentalDate(String d) { driver.findElement(date).sendKeys(d, Keys.ENTER); return this; }
    public RentPage setRentalTime() { driver.findElement(rentalTimeField).click(); driver.findElement(rentalTime).click(); return this; }
    public RentPage clickColorBlack() { driver.findElement(colorBlack).click(); return this; }
    public void clickOrderButton() { driver.findElement(orderButton).click(); }
    public void clickConfirmButton() { driver.findElement(confirmButton).click(); }
    public boolean isModalDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(modal)).isDisplayed();
    }
    // Внутри класса RentPage.java

    // Локатор для красной кнопки "Нет" (ищем по тексту и специфичному классу, если есть)
    private final By rejectButton = By.xpath(".//button[text()='Нет']");

    public void clickRejectButton() {
        // 1. Ждем, пока кнопка станет кликабельной
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(rejectButton));

        // 2. Скроллим до неё, чтобы она попала в область видимости
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);

        // 3. Кликаем
        button.click();
    }
    // Локатор комментария
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    public RentPage sendComment(String comment) {
        // 1. Ждем появления поля на странице
        WebElement field = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(commentField));

        // 2. Прокручиваем, чтобы поле было точно в зоне видимости
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", field);

        // 3. Очищаем поле (на случай, если там что-то было) и вводим текст
        field.clear();
        field.sendKeys(comment);

        return this;
    }
}