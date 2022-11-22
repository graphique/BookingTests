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
    public Booking prepareBooking (String name, DataGenerator dataGenerator)  {
        Bookingdates bookingdates = new Bookingdates(dataGenerator.getCheckin(),dataGenerator.getCheckout());
        Booking booking = new Booking(name,dataGenerator.getLastname(),111,
                true,bookingdates,"Breakfast");
        return booking;

    }


    @Step("creates a booking with a generated name")
    public Booking prepareBooking (DataGenerator dataGenerator)  {
        Bookingdates bookingdates = new Bookingdates(dataGenerator.getCheckin(),dataGenerator.getCheckout());
        Booking booking = new Booking(dataGenerator.getFirstname(),dataGenerator.getLastname(),111,
                true,bookingdates,"Breakfast");
        return booking;

    }

    @Step("checks a created booking")
    public Bookingid createBooking (Booking booking) {
        Response response = given().log().all()
                .body(booking)
                .when()
                .post(Constants.BOOKING);

        response.then().log().all().statusCode(200);

        Bookingid bookingidnew = response.as(Bookingid.class);

        String actual = bookingidnew.getBooking().toString();
        String expected = booking.toString();

        Assert.assertEquals(actual,expected);
        return bookingidnew;

    }

}
