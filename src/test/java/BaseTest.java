import Constants.Constants;
import Data.DataGenerator;
import Steps.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.given;


public class BaseTest {

    CreateBooking createBooking  = new CreateBooking();
    CreateToken createToken = new CreateToken();
    DeleteBooking deleteBooking = new DeleteBooking();
    GetBookingIds getBookingIds = new GetBookingIds();
    PartialUpdateBooking partialUpdateBooking = new PartialUpdateBooking();
    UpdateBooking updateBooking = new UpdateBooking();
    DataGenerator dataGenerator = new DataGenerator();



    @BeforeClass
    public static void setUp () {
//request spec
        RequestSpecification spec = new RequestSpecBuilder()
                .setBaseUri(Constants.baseURI)
                .setContentType(ContentType.JSON)
                .build();

        RestAssured.requestSpecification = spec;

        //Healthcheck before tests
        given().log().all()
                .when().get(Constants.PING)
                .then().log().all()
                .statusCode(201);
        }
    }



