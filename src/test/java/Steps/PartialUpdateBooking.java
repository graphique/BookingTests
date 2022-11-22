package Steps;

import Constants.Constants;
import Pojo.Booking;
import Pojo.Bookingid;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class PartialUpdateBooking  {

    @Step("partially updates a booking (firstname and lastname)")
    public void partialUpdateBooking (Bookingid bookingid, String token) {

        int bookingId = bookingid.getBookingid();
        Booking booking = bookingid.getBooking();

        booking.setFirstname("Dick");
        booking.setLastname("Chaney");

        Response response  = given().log().all()
                .body(booking) //взять тело из созданной сущности
                .header("Cookie","token=" + token)
                .when()
                .patch(Constants.BOOKING + bookingId);

        response.then().log().all().statusCode(200);

        Booking newbooking = response.as(Booking.class);

        Assert.assertNotEquals(booking,newbooking);

    }
}
