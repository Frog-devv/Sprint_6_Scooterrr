package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RentPage {
    private final WebDriver driver;
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(rejectButton));


        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);


        button.click();
    }

    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    public RentPage sendComment(String comment) {

        WebElement field = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(commentField));


        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", field);

        field.clear();
        field.sendKeys(comment);

        return this;
    }
}