import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class BookingTests extends BaseTest {

    @Test
    @Description("Проверяем создание брони")
    public void createBookingTest () {
        Response resp =  createBooking.createBooking();
        Assert.assertNotNull(resp);
    }

    @Test
    @Description("Проверка создания токена для дальнейшей авторизации")
    public void createTokenTest () {
        String token = createToken.createToken();
        Assert.assertNotNull(token);
        System.out.println("the token is " + token);
    }

    @Test
    @Description("Проверка удаления созданной записи бронирования")
    public void deleteBookingTest () {
        String token =  createToken.createToken();
        Response resp = createBooking.createBooking();
        deleteBooking.deleteBooking(resp,token);

    }

    @Test
    @Description("Проверка получения всех id бронирований")
    public void getBookingIdsTest() {
        List<Integer> integerList = getBookingIds.getBookingIds();
        Assert.assertNotNull(integerList);
    }


    @Test
    @Description("Проверка получения всех id бронирований по имени и фамилии гостя")
    public void getBookingIdsByNameTest () {
      Response resp =   createBooking.createBooking();
      List<Integer> ids =   getBookingIds.getBookingIdsByName(resp);
      Assert.assertNotNull(ids);
    }


    @Test
    @Description("Проверка получения всех id бронирований по дате вьезда и выезда")
    public void getBookingIdsByDateTest () {
        Response resp = createBooking.createBooking();
        List<Integer> ids =   getBookingIds.getBookingIdsByDate(resp);
        Assert.assertNotNull(ids);
    }


    @Test
    @Description("Проверка получения информации по конкретному бронированию")
    public void getBookingTest () {
        List<Integer> bookingIdList = getBookingIds.getBookingIds();
        int id =   getBookingIds.getScpecificBookingId(bookingIdList);
        getBookingIds.getBooking(id);
    }

    @Test
    @Description("Проверка успешного частичного апдейта информации по бронированию")
    public void partialUpdateBookingTest () {
        String token = createToken.createToken();
        Response resp = createBooking.createBooking();
        partialUpdateBooking.partialUpdateBooking(resp,token);
    }

    @Description("Проверка полного апдейта бронирования")
    @Test
    public void updateBookingTest () {
        String token  = createToken.createToken();
        Response resp = createBooking.createBooking();
        updateBooking.updateBooking(resp, token);
    }

}
