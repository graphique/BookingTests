package Steps;

import Constants.Constants;
import Data.DataGenerator;
import Pojo.Booking;
import Pojo.Bookingdates;
import Pojo.Bookingid;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;

public class CreateBooking  {

    @Step("prepares a booking with parameter name")
    public Booking prepareBooking (String name, DataGenerator dataGenerator)  {
        Bookingdates bookingdates = new Bookingdates(dataGenerator.getCheckin(),dataGenerator.getCheckout());
        Booking booking = new Booking(name,dataGenerator.getLastname(),111,
                true,bookingdates,"Breakfast");
        return booking;

    }


    @Step("prepares a booking with a generated name")
    public Booking prepareBooking (DataGenerator dataGenerator)  {
        Bookingdates bookingdates = new Bookingdates(dataGenerator.getCheckin(),dataGenerator.getCheckout());
        Booking booking = new Booking(dataGenerator.getFirstname(),dataGenerator.getLastname(),111,
                true,bookingdates,"Breakfast");
        return booking;

    }

    @Step("prepares a booking with a external json file")
    public Booking prepareBooking () throws IOException {
        byte [] b = Files.readAllBytes(Paths.get("src/test/java/JsonExamples/createBooking.json"));
        String body = new String(b);
        Booking newBooking = new Gson().fromJson(body,Booking.class);
        return newBooking;
    }

    @Step("creates a created booking")
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
