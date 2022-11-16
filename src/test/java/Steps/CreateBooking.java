package Steps;

import Constants.Constants;
import Data.DataGenerator;
import Pojo.Booking;
import Pojo.Bookingdates;
import Pojo.Bookingid;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
public class CreateBooking  {

    @Step("creates a booking with parameter name")
    public Response createBooking (String name, DataGenerator dataGenerator)  {
        String lastname = dataGenerator.getLastname();
        JSONObject body = new JSONObject();
        body.put("firstname",name);
        body.put("lastname",lastname);
        body.put("totalprice",111);
        body.put("depositpaid",true);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2022-01-01");
        bookingdates.put("checkout","2022-01-10");

        body.put("bookingdates",bookingdates);

        body.put("additionalneeds","Breakfast");


        Response response = given().log().all()
                .body(body.toString())
                .when()
                .post(Constants.BOOKING);

        response.then().log().all().statusCode(200);


        String factFirstName = response.jsonPath().getString("booking.firstname");
        Assert.assertEquals(name,factFirstName);

        String factLastName = response.jsonPath().getString("booking.lastname");
        Assert.assertEquals(lastname,factLastName);

        return response;

    }


    @Step("creates a booking with a generated name")
    public Response createBooking (DataGenerator dataGenerator)  {
        String firstname = dataGenerator.getFirstname();
        String lastname = dataGenerator.getLastname();

        JSONObject body = new JSONObject();
        body.put("firstname",firstname);
        body.put("lastname",lastname);
        body.put("totalprice",111);
        body.put("depositpaid",true);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2022-01-01");
        bookingdates.put("checkout","2022-01-10");

        body.put("bookingdates",bookingdates);
        body.put("additionalneeds","Breakfast");

        Response response = given().log().all()
                .body(body.toString())
                .when()
                .post(Constants.BOOKING);

        response.then().log().all().statusCode(200);

        String factFirstName = response.jsonPath().getString("booking.firstname");
        Assert.assertEquals(firstname,factFirstName);

        String factLastName = response.jsonPath().getString("booking.lastname");
        Assert.assertEquals(lastname,factLastName);

        return response;

    }

    @Step("creates a booking with using pojo")
    public Response createBookingPojo (DataGenerator dataGenerator) {
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

        return response;
    }


}
