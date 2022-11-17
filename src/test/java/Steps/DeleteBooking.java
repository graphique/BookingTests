package Steps;

import Constants.Constants;
import Pojo.Bookingid;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class DeleteBooking {

    @Step("deletes a particular booking")
    public Integer deleteBooking (Bookingid bookingid, String token) {
        int id = bookingid.getBookingid();

        Response response = given().log().all()
                .header("Cookie","token=" + token)
                .when()
                .delete(Constants.BOOKING + id);

        response.then().log().all().statusCode(201);
        System.out.println("booking id " + id + " was deleted");

        Response responseDeletedBooking = RestAssured.get("booking" + id);
        Assert.assertEquals(responseDeletedBooking.getStatusCode(),404);

        return id;

    }
}
