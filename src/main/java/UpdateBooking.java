import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class UpdateBooking  {

    @Step("updates a firstname of customer")
    public void updateBooking (Response createdBookingResponse, String token) {

        int bookingId = createdBookingResponse.jsonPath().getInt("bookingid");

        JSONObject body = new JSONObject();
        body.put("firstname","James");//это будет отличаться
        body.put("lastname",createdBookingResponse.jsonPath().getString("booking.lastname"));
        body.put("totalprice",createdBookingResponse.jsonPath().getInt("booking.totalprice"));
        body.put("depositpaid",createdBookingResponse.jsonPath().getBoolean("booking.depositpaid"));

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin",createdBookingResponse.jsonPath().getString("booking.bookingdates.checkin"));
        bookingdates.put("checkout",createdBookingResponse.jsonPath().getString("booking.bookingdates.checkout"));

        body.put("bookingdates",bookingdates);

        body.put("additionalneeds",createdBookingResponse.jsonPath().getString("booking.additionalneeds"));

        Response response  = given().log().all()
                .contentType(ContentType.JSON)
                .body(body.toString()) //взять тело из созданной сущности
                .header("Cookie","token=" + token)
                .when()
                .put("booking/" + bookingId);

        response.then().log().all().statusCode(200);

        String expectedFirstName = createdBookingResponse.jsonPath().getString("booking.firstname");
        String factFirstName = response.jsonPath().getString("firstname");
        Assert.assertNotEquals(expectedFirstName,factFirstName);

    }
}
