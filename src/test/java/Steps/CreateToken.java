import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateToken {

    @Step("creates an auth token")
    public String createToken (){
        JSONObject body = new JSONObject();
        body.put("username","admin");
        body.put("password","password123");

        Response response =    given().log().all()
                .body(body.toString())
                .contentType(ContentType.JSON)
                .when()
                .post("auth")
                .then().extract().response();

        String token = response.body().jsonPath().getString("token");
        return token;
    }

}
