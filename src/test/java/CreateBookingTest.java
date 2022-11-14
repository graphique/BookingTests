import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CreateBookingTest  extends BaseTest{

    @Test
    @Description("Проверяем создание брони с разными именами")
    @Parameters("bookingName")
    public void createBookingTest (String bookingName) {
        Response resp = createBooking.createBooking(bookingName);
        Assert.assertNotNull(resp);
    }

}
