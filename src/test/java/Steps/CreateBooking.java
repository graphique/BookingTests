package Steps;

import Constants.Constants;
import Data.DataGenerator;
import Pojo.Booking;
import Pojo.Bookingdates;
import Pojo.Bookingid;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
public class CreateBooking  {

    @Step("creates a booking with parameter name")
    public Bookingid createBooking (String name, DataGenerator dataGenerator)  {

        Bookingdates bookingdates = new Bookingdates("2022-01-01","2022-01-10");
        Booking booking = new Booking(name,dataGenerator.getLastname(),111,
                true,bookingdates,"Breakfast");

        Response response = given().log().all()
                .body(booking)
                .when()
                .post(Constants.BOOKING);

        response.then().log().all().statusCode(200);

        Bookingid bookingid = response.as(Bookingid.class);

        String actual = bookingid.getBooking().toString();
        String expected = booking.toString();

        Assert.assertEquals(actual,expected);

        return bookingid;

    }


    @Step("creates a booking with a generated name")
    public Bookingid createBooking (DataGenerator dataGenerator)  {
        Bookingdates bookingdates = new Bookingdates("2022-01-01","2022-01-10");
        Booking booking = new Booking(dataGenerator.getFirstname(),dataGenerator.getLastname(),111,
                true,bookingdates,"Breakfast");

        Response response = given().log().all()
                .body(booking)
                .when()
                .post(Constants.BOOKING);

        response.then().log().all().statusCode(200);

        Bookingid bookingid = response.as(Bookingid.class);

        String actual = bookingid.getBooking().toString();
        String expected = booking.toString();

        Assert.assertEquals(actual,expected);

        return bookingid;

    }

    @Step("creates a booking with using pojo")
    public Bookingid createBookingPojo (DataGenerator dataGenerator) {
        Bookingdates bookingdates = new Bookingdates("2022-01-01","2022-01-10");
        Booking booking = new Booking(dataGenerator.getFirstname(),dataGenerator.getLastname(),111,
                true,bookingdates,"Breakfast");

        Response response = given().log().all()
                .body(booking)
                .when()
                .post(Constants.BOOKING);

        response.then().log().all().statusCode(200);

        Bookingid bookingid = response.as(Bookingid.class);

        String actual = bookingid.getBooking().toString();
        String expected = booking.toString();

        Assert.assertEquals(actual,expected);

        return bookingid;
    }


}
