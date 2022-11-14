import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.given;


public class BaseTest {

    CreateBooking createBooking  = new CreateBooking();
    CreateToken createToken = new CreateToken();
    DeleteBooking deleteBooking = new DeleteBooking();
    GetBookingIds getBookingIds = new GetBookingIds();
    PartialUpdateBooking partialUpdateBooking = new PartialUpdateBooking();
    UpdateBooking updateBooking = new UpdateBooking();


    @BeforeClass
    public static void setUp () {
       // RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        //setting base uri
        RestAssured.baseURI  = "https://restful-booker.herokuapp.com/";

        //Healthcheck before tests
        given().log().all()
                .when().get("ping")
                .then().log().all()
                .statusCode(201);
        }
    }



