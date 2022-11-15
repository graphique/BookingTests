package Steps;

import Constants.Constants;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateToken {

    @Step("creates an auth token")
    public String createToken (){
        JSONObject body = new JSONObject();
        body.put("username", Constants.USERNAME);
        body.put("password",Constants.PASSWORD);

        Response response =    given().log().all()
                .body(body.toString())
                .when()
                .post(Constants.AUTH)
                .then().extract().response();

        String token = response.body().jsonPath().getString("token");
        return token;
    }

}
