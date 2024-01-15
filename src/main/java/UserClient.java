import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static constaints.TestData.*;
import static io.restassured.RestAssured.given;

public class UserClient extends Client {
    @Step("Register")
    public ValidatableResponse register(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then();
    }

    @Step("Login")
    public ValidatableResponse login(UserAccountData userCredentials) {
        return given()
                .spec(getSpec())
                .body(userCredentials)
                .when()
                .post(LOGIN_PATH)
                .then();
    }

    @Step("Logout")
    public ValidatableResponse logout(String token) {
        Gson deleteGson = new GsonBuilder().setPrettyPrinting().create();
        TokenRefresh refreshToken = new TokenRefresh(String.valueOf(token));
        String refreshTokenJson = deleteGson.toJson(refreshToken);
        return given()
                .spec(getSpec())
                .body(refreshTokenJson)
                .when()
                .post(LOGOUT_PATH)
                .then();
    }

    @Step("Refresh token")
    public ValidatableResponse refreshToken(UserAccountData userAccountData) {
        return given()
                .spec(getSpec())
                .body(userAccountData)
                .when()
                .post(TOKEN_PATH)
                .then();
    }

    @Step("Get user data")
    public ValidatableResponse getUserData(String accessToken) {
        return given()
                .spec(getSpec())
                .header("authorization", accessToken)
                .when()
                .get(USER_PATH)
                .then();
    }

    @Step("Delete user")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(getSpec())
                .header("authorization", accessToken)
                .when()
                .delete(USER_PATH)
                .then();
    }

    @Step("Update authorized user data")
    public ValidatableResponse updateAuthorizedUserData(String accessToken, UserData userData) {
        return given()
                .spec(getSpec())
                .header("authorization", accessToken)
                .body(userData)
                .when()
                .patch(USER_PATH)
                .then();
    }

    @Step("Update unauthorized user data")
    public ValidatableResponse updateUnauthorizedUserData(UserData userData) {
        return given()
                .spec(getSpec())
                .body(userData)
                .when()
                .patch(USER_PATH)
                .then();
    }
}