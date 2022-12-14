import Constants.Constants;
import Pojo.Booking;
import Pojo.Bookingid;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class BookingTests extends BaseTest {


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
        String token = createToken.createToken();
        Booking booking = createBooking.prepareBooking(dataGenerator);
        Bookingid bookingid = createBooking.createBooking(booking);
        deleteBooking.deleteBooking(bookingid,token);
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
      Booking booking =   createBooking.prepareBooking(dataGenerator);
      Bookingid bookingid = createBooking.createBooking(booking);
      List<Integer> ids =   getBookingIds.getBookingIdsByName(bookingid.getBooking());
      Assert.assertNotNull(ids);
    }


    @Test
    @Description("Проверка получения всех id бронирований по дате вьезда и выезда")
    public void getBookingIdsByDateTest () {
        Booking booking = createBooking.prepareBooking(dataGenerator);
        Bookingid bookingid = createBooking.createBooking(booking);
        List<Integer> ids =   getBookingIds.getBookingIdsByDate(bookingid.getBooking());
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
    @Description("Проверка получения информации по конкретному ")
    public void getBookingTestWithXml () {
        List<Integer> bookingIdList = getBookingIds.getBookingIds();
        int id =   getBookingIds.getScpecificBookingId(bookingIdList);
        getBookingIds.getBooking(id,true);
    }


    @Test
    @Description("Проверка успешного частичного апдейта информации по бронированию")
    public void partialUpdateBookingTest () {
        String token = createToken.createToken();
        Booking booking = createBooking.prepareBooking(dataGenerator);
        Bookingid bookingid = createBooking.createBooking(booking);
        partialUpdateBooking.partialUpdateBooking(bookingid,token);
    }

    @Description("Проверка полного апдейта бронирования")
    @Test
    public void updateBookingTest () {
        String token = createToken.createToken();
        Booking booking = createBooking.prepareBooking(dataGenerator);
        Bookingid bookingid = createBooking.createBooking(booking);
        updateBooking.updateBooking(bookingid, token);
    }

    @Description("Проверка создания брони через pojo")
    @Test
    public void createBookingTest () {
        Booking booking = createBooking.prepareBooking(dataGenerator);
        createBooking.createBooking(booking);
    }

    @Description("Проверка создания брони через внешний json файл")
    @Test
    public void createBokingWithJsonTest () throws IOException {
        Booking booking = createBooking.prepareBooking();
        createBooking.createBooking(booking);
    }

    @Description("Проверка получения куки и хедеров ответа")
    @Test
    public void headersAndCookiesTest () {
        /*Header someHeader = new Header("some name", "some value");
        spec.header(someHeader);

        Cookie someCookie = new Cookie.Builder("some cookie", "some cookie value").build();
        spec.cookie(someCookie);*/

        Response response = RestAssured.given()
                .cookie("Test cookie name", "Test cookie value ")
                .header("Test header name", "Test header value")
                .log().all()
                .get(Constants.PING);
        Headers headers = response.getHeaders();
        System.out.println("Headers: " + headers);

        Header serverHeader1 = headers.get("Server");
        System.out.println(serverHeader1.getName() + ": "  + serverHeader1.getValue());

        String serverHeader2 = response.getHeader("Server");
        System.out.println("Server: " +  serverHeader2);

        Cookies cookies = response.getDetailedCookies();
        System.out.println("Cookies: " + cookies);
    }
}
