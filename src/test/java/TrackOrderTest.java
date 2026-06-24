package tests;
/////
import org.junit.jupiter.api.Test;
import pageObjects.MainPage;
import pageObjects.TrackOrderPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrackOrderTest extends tests.CommonBaseTest {

    @Test
    public void checkInvalidOrderNumberShowsError() {
        MainPage mainPage = new MainPage(driver);
        TrackOrderPage trackPage = new TrackOrderPage(driver);

        mainPage.openSite().clickStatusButton();

        trackPage.enterOrderNumber("000000");
        trackPage.clickGoButton();

        assertTrue(trackPage.isErrorImageDisplayed(), "Картинка ошибки не появилась!");
    }
}