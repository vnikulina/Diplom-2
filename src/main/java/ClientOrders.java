import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static constaints.TestData.ALL_ORDERS_PATH;
import static constaints.TestData.ORDERS_PATH;
import static io.restassured.RestAssured.given;

public class ClientOrders extends Client {
    @Step("Get authenticated user orders list")
    public ValidatableResponse getUserOrders(String accessToken) {
        return given()
                .spec(getSpec())
                .header("authorization", accessToken)
                .when()
                .get(ORDERS_PATH)
                .then();
    }

    @Step("Get not authenticated user orders list")
    public ValidatableResponse getUnauthorizedUserOrders() {
        return given()
                .spec(getSpec())
                .when()
                .get(ORDERS_PATH)
                .then();
    }

    @Step("Get all orders list")
    public ValidatableResponse getAllOrders() {
        return given()
                .spec(getSpec())
                .when()
                .get(ALL_ORDERS_PATH)
                .then();
    }

    @Step("Create order by not authenticated user with ingredients")
    public ValidatableResponse createOrderUnauthorizedUserWithIngredients(Ingredients ingredients) {
        return given()
                .spec(getSpec())
                .body(ingredients)
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @Step("Create order by authenticated user with ingredients")
    public ValidatableResponse createOrderAuthorizedUserWithIngredients(String accessToken, Ingredients ingredients) {
        return given()
                .spec(getSpec())
                .header("authorization", accessToken)
                .body(ingredients)
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @Step("Create order by not authenticated user without ingredients")
    public ValidatableResponse createOrderUnauthorizedUserWithoutIngredients() {
        return given()
                .spec(getSpec())
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @Step("Create order by authenticated user without ingredients")
    public ValidatableResponse createOrderAuthorizedUserWithoutIngredients(String accessToken) {
        return given()
                .spec(getSpec())
                .header("authorization", accessToken)
                .when()
                .post(ORDERS_PATH)
                .then();
    }
}