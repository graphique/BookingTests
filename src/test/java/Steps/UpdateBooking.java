package Steps;

import Constants.Constants;
import Pojo.Booking;
import Pojo.Bookingid;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class UpdateBooking  {

    @Step("updates a firstname of customer")
    public void updateBooking (Bookingid bookingid, String token) {

        int bookingId = bookingid.getBookingid();
        Booking booking = bookingid.getBooking();
        String expectedFirstName = booking.getFirstname();

        booking.setFirstname("James");

        Response response  = given().log().all()
                .body(booking) //взять тело из созданной сущности
                .header("Cookie","token=" + token)
                .when()
                .put(Constants.BOOKING + bookingId);

        response.then().log().all().statusCode(200);


        String factFirstName = response.jsonPath().getString("firstname");
        Assert.assertNotEquals(expectedFirstName,factFirstName);

    }
}
