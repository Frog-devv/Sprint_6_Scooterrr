package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class OrderFlowTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }


    @Test
    public void positiveOrderFlowHeaderTest() {
        new MainPage(driver).openSite().clickCookieButton().clickOrderButton("header");
        fillOrderAndRent("Иван", "Иванов", "Ленина, 1", "Сокольники", "89001112233", "21.06.2026");

        RentPage rent = new RentPage(driver);
        rent.clickConfirmButton();
        assertTrue(rent.isModalDisplayed());
    }

    // Позитивный сценарий: через кнопку в середине
    @Test
    public void positiveOrderFlowMiddleTest() {
        new MainPage(driver).openSite().clickCookieButton().clickOrderButton("middle");
        fillOrderAndRent("Анна", "Петрова", "Мира, 5", "Черкизовская", "89005554433", "22.06.2026");

        RentPage rent = new RentPage(driver);
        rent.clickConfirmButton();
        assertTrue(rent.isModalDisplayed());
    }

    // Негативный сценарий: отмена заказа
    @Test
    public void rejectOrderTest() {
        new MainPage(driver).openSite().clickCookieButton().clickOrderButton("header");
        fillOrderAndRent("Петр", "Сидоров", "Победы, 10", "Лубянка", "89009998877", "23.06.2026");

        RentPage rent = new RentPage(driver);
        rent.clickRejectButton();

        boolean isModalGone = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[text()='Заказ оформлен']")));
        assertTrue(isModalGone, "Окно подтверждения не закрылось после нажатия 'Нет'!");
    }

    // Вспомогательный метод, чтобы не дублировать код заполнения формы
    private void fillOrderAndRent(String name, String sur, String addr, String met, String ph, String date) {
        new OrderPage(driver)
                .sendClientFirstName(name)
                .sendClientLastName(sur)
                .sendDeliveryAddress(addr)
                .selectMetroStation(met)
                .sendDeliveryClientPhoneNumber(ph)
                .clickNextButton();

        new RentPage(driver)
                .sendRentalDate(date)
                .setRentalTime()
                .clickColorBlack()
                .sendComment("Привет")
                .clickOrderButton();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}