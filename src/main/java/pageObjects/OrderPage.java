package pageObjects;
/////
import org.openqa.selenium.*;

public class OrderPage {
    private WebDriver driver;
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metro = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    public OrderPage(WebDriver driver) { this.driver = driver; }

    public OrderPage sendClientFirstName(String n) { driver.findElement(name).sendKeys(n); return this; }
    public OrderPage sendClientLastName(String s) { driver.findElement(surname).sendKeys(s); return this; }
    public OrderPage sendDeliveryAddress(String a) { driver.findElement(address).sendKeys(a); return this; }
    public OrderPage selectMetroStation(String m) {
        driver.findElement(metro).sendKeys(m, Keys.DOWN, Keys.ENTER);
        return this;
    }
    public OrderPage sendDeliveryClientPhoneNumber(String ph) { driver.findElement(phone).sendKeys(ph); return this; }
    public void clickNextButton() { driver.findElement(nextButton).click(); }
}