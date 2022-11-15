package Steps;

import Constants.Constants;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class DeleteBooking {

    @Step("deletes a particular booking")
    public Integer deleteBooking (Response createdBookingResponse, String token) {
        int bookingId = createdBookingResponse.jsonPath().getInt("bookingid");

        Response response = given().log().all()
                .header("Cookie","token=" + token)
                .when()
                .delete(Constants.BOOKING + bookingId);

        response.then().log().all().statusCode(201);
        System.out.println("booking id " + bookingId + " was deleted");

        Response responseDeletedBooking = RestAssured.get("booking" + bookingId);
        Assert.assertEquals(responseDeletedBooking.getStatusCode(),404);

        return bookingId;

    }


}
