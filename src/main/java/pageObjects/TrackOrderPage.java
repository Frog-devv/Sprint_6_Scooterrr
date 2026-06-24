package pageObjects;
/////
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TrackOrderPage {
    private WebDriver driver;


    private final By orderInput = By.xpath(".//input[@placeholder='Введите номер заказа']");
    private final By goButton = By.xpath(".//button[text()='Go!']");
    // Используем xpath с текстом для надежности
    private final By errorImage = By.xpath("//div[text()='Такого заказа нет']");

    public TrackOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterOrderNumber(String orderNumber) {
        driver.findElement(orderInput).sendKeys(orderNumber);
    }

    public void clickGoButton() {
        driver.findElement(goButton).click();
    }

    public boolean isErrorImageDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(errorImage))
                .isDisplayed();
    }
}