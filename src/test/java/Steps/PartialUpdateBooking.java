import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class PartialUpdateBooking  {

    @Step("partially updates a booking (firstname and lastname)")
    public void partialUpdateBooking (Response createdBookingResponse, String token) {

        int bookingId = createdBookingResponse.jsonPath().getInt("bookingid");

        JSONObject body = new JSONObject();
        body.put("firstname","Dick");//это будет отличаться
        body.put("lastname","Chaney");//это будет отличаться

        Response response  = given().log().all()
                .contentType(ContentType.JSON)
                .body(body.toString()) //взять тело из созданной сущности
                .header("Cookie","token=" + token)
                .when()
                .patch("booking/" + bookingId);

        response.then().log().all().statusCode(200);

        String expectedFirstName = createdBookingResponse.jsonPath().getString("booking.firstname");
        String factFirstName = response.jsonPath().getString("firstname");
        Assert.assertNotEquals(expectedFirstName,factFirstName);

    }
}
