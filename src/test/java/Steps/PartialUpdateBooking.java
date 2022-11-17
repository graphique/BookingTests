package Steps;

import Constants.Constants;
import Pojo.Booking;
import Pojo.Bookingid;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class PartialUpdateBooking  {

    @Step("partially updates a booking (firstname and lastname)")
    public void partialUpdateBooking (Bookingid bookingid, String token) {

        int bookingId = bookingid.getBookingid();

        JSONObject body = new JSONObject();
        body.put("firstname","Dick");//это будет отличаться
        body.put("lastname","Chaney");//это будет отличаться

        Response response  = given().log().all()
                .body(body.toString()) //взять тело из созданной сущности
                .header("Cookie","token=" + token)
                .when()
                .patch(Constants.BOOKING + bookingId);

        response.then().log().all().statusCode(200);

        String expectedFirstName = bookingid.getBooking().getFirstname();
        String factFirstName = response.jsonPath().getString("firstname");
        Assert.assertNotEquals(expectedFirstName,factFirstName);

    }
}
