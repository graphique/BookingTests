import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
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
        Response resp = createBooking.createBooking();
        partialUpdateBooking.partialUpdateBooking(resp,token);
    }

    @Description("Проверка полного апдейта бронирования")
    @Test
    public void updateBookingTest () {
        String token = createToken.createToken();
        Response resp = createBooking.createBooking();
        updateBooking.updateBooking(resp, token);
    }

    @Description("Проверка создания брони через pojo")
    @Test
    public void createBookingTest () {
        Response response = createBooking.createBookingPojo();
        Assert.assertNotNull(response);
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
                .get("/ping.");
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
