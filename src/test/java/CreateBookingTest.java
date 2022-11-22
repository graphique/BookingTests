import Pojo.Booking;
import Pojo.Bookingid;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CreateBookingTest  extends BaseTest{

    @Test
    @Description("Проверяем создание брони с разными именами")
    @Parameters("bookingName")
    public void createBookingTest (String bookingName) {
        Booking booking = createBooking.prepareBooking(bookingName,dataGenerator);
        Bookingid bookingid = createBooking.createBooking(booking);
        Assert.assertNotNull(bookingid);
    }

}
