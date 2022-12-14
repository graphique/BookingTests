package Steps;

import Constants.Constants;
import Pojo.Booking;
import Pojo.Bookingdates;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import static io.restassured.RestAssured.given;


public class GetBookingIds  {

    @Step("gets all booking ids")
    public List<Integer> getBookingIds () {
       Response response = given().log().all()
            .when()
            .get(Constants.BOOKING);
              List<Integer > ids = response.jsonPath().getList(Constants.BOOKINGID);
              return ids;
    }

    @Step("gets all booking ids by firstname and lastname")
    public List<Integer> getBookingIdsByName (Booking booking) {
        String firstname = booking.getFirstname();
        String lastname = booking.getLastname();

        Response response = given().log().all()
                .queryParam("firstname",firstname)
                .queryParam("lastname",lastname)
                .when()
                .get(Constants.BOOKING);
        List<Integer > ids = response.jsonPath().getList(Constants.BOOKINGID);

        response.then().log().all().statusCode(200);
        return ids;

    }

    @Step("gets all booking ids by checkin and checkout date")
    public List<Integer> getBookingIdsByDate (Booking booking) {
        Bookingdates bookingdates = booking.getBookingdates();

        String checkin = bookingdates.getCheckin();
        String checkout = bookingdates.getCheckout();

        Response response = given().log().all()
                .queryParam("checkin",checkin)
                .queryParam("checkout",checkout)
                .when()
                .get(Constants.BOOKING);
        List<Integer > ids = response.jsonPath().getList(Constants.BOOKINGID);

        response.then().log().all().statusCode(200);
        return ids;

    }


    @Step("gets a second booking id from booking ids")
    public Integer getScpecificBookingId (List<Integer> bookingIds) {
        int id = bookingIds.get(1);
        return id;
    }


    @Step("gets an information about booking by specific id")
    public Booking getBooking (Integer id) {
        Response response = given().log().all()
                .when()
                .get(Constants.BOOKING + id);

        response.then().log().all().statusCode(200);
        Booking booking = response.as(Booking.class);
        return booking;
    }


    @Step("gets an xml information about booking by specific id")
    public void getBooking (Integer id, boolean xml) {
        RequestSpecification spec = new RequestSpecBuilder().build();

        if (xml == true ){
            spec.header("Accept", "application/xml");
            // spec.contentType(ContentType.XML);
        } else  {

        }
        Response response = given().spec(spec).log().all()
                .when()
                .get(Constants.BOOKING + id);

        response.then().log().all().statusCode(200);
    }

}
