import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static constaints.TestData.INGREDIENTS_PATH;
import static io.restassured.RestAssured.given;

public class ClientIngredients extends Client {
    @Step("Get ingredients list")
    public ValidatableResponse getIngredients() {
        return given()
                .spec(getSpec())
                .when()
                .get(INGREDIENTS_PATH)
                .then();
    }
}