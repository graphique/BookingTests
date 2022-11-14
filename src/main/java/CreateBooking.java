import Pojo.Booking;
import Pojo.Bookingdates;
import Pojo.Bookingid;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class CreateBooking {


    @Step("creates a booking with parameter name")
    public Response createBooking (String name)  {
        JSONObject body = new JSONObject();
        body.put("firstname",name);
        body.put("lastname","Brown");
        body.put("totalprice",111);
        body.put("depositpaid",true);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2022-01-01");
        bookingdates.put("checkout","2022-01-10");

        body.put("bookingdates",bookingdates);

        body.put("additionalneeds","Breakfast");


        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post("booking");

        response.then().log().all().statusCode(200);


        String expectedFirstName = name;
        String factFirstName = response.jsonPath().getString("booking.firstname");
        Assert.assertEquals(expectedFirstName,factFirstName);

        return response;

    }


    @Step("creates a booking")
    public Response createBooking ()  {
        JSONObject body = new JSONObject();
        body.put("firstname","Jim");
        body.put("lastname","Brown");
        body.put("totalprice",111);
        body.put("depositpaid",true);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2022-01-01");
        bookingdates.put("checkout","2022-01-10");

        body.put("bookingdates",bookingdates);

        body.put("additionalneeds","Breakfast");


        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post("booking");

        response.then().log().all().statusCode(200);


        String expectedFirstName = "Jim";
        String factFirstName = response.jsonPath().getString("booking.firstname");
        Assert.assertEquals(expectedFirstName,factFirstName);

        return response;

    }

    @Step("creates a booking")
    public Response createBookingPojo () {
        Bookingdates bookingdates = new Bookingdates("2022-01-01","2022-01-10");
        Booking booking = new Booking("Jim","Brown",111,
                true,bookingdates,"Breakfast");

        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("booking");

        response.then().log().all().statusCode(200);

        Bookingid bookingid = response.as(Bookingid.class);

        String actual = bookingid.getBooking().toString();
        String expected = booking.toString();

        Assert.assertEquals(actual,expected);

        return response;
    }


}
