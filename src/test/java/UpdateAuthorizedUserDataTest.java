import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;

import static constaints.TestData.REGISTERED_EMAIL;
import static org.junit.Assert.*;

public class UpdateAuthorizedUserDataTest {
    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    @Step("Prepare to change data of authorized user")
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandomUser();
    }

    @Test
    @DisplayName("Update email to different")
    public void userCanBeUpdateEmailToDifferent() {
        ValidatableResponse responseRegister = userClient.register(user);
        accessToken = responseRegister.extract().path("accessToken");
        User changedUser = User.getRandomUser();
        String changedEmail = changedUser.getEmail();
        ValidatableResponse responseUpdateName = userClient.updateAuthorizedUserData(accessToken, UserData.from(changedUser));
        int actualStatusCodeChange = responseUpdateName.extract().statusCode();
        boolean isSuccessInMessageTrueChange = responseUpdateName.extract().path("success");
        HashMap<String, String> changedUserData = responseUpdateName.extract().path("user");
        String responseEmail = changedUserData.get("email");
        assertEquals(200, actualStatusCodeChange);
        assertTrue(isSuccessInMessageTrueChange);
        assertEquals(changedEmail, responseEmail);
    }

    @Test
    @DisplayName("Update email to existing")
    public void userCanNotBeUpdateEmailToExisting() {
        ValidatableResponse responseRegister = userClient.register(user);
        accessToken = responseRegister.extract().path("accessToken");
        user.setEmail(REGISTERED_EMAIL);
        ValidatableResponse responseUpdateName = userClient.updateAuthorizedUserData(accessToken, UserData.from(user));
        int actualStatusCodeChange = responseUpdateName.extract().statusCode();
        boolean isSuccessInMessageTrueChange = responseUpdateName.extract().path("success");
        String responseMessage = responseUpdateName.extract().path("message");
        assertEquals(403, actualStatusCodeChange);
        assertFalse(isSuccessInMessageTrueChange);
        assertEquals("User with such email already exists", responseMessage);
    }

    @Test
    @DisplayName("Update name")
    public void userCanBeUpdateName() {
        ValidatableResponse responseRegister = userClient.register(user);
        accessToken = responseRegister.extract().path("accessToken");
        User changedUser = User.getRandomUser();
        String changedName = changedUser.getName();
        ValidatableResponse responseUpdateName = userClient.updateAuthorizedUserData(accessToken, UserData.from(changedUser));
        int actualStatusCodeChange = responseUpdateName.extract().statusCode();
        boolean isSuccessInMessageTrueChange = responseUpdateName.extract().path("success");
        HashMap<String, String> changedUserData = responseUpdateName.extract().path("user");
        String responseName = changedUserData.get("name");
        assertEquals(200, actualStatusCodeChange);
        assertTrue(isSuccessInMessageTrueChange);
        assertEquals(changedName, responseName);
    }

    @After
    @Step("Delete user")
    public void cleanUp() {
        userClient.deleteUser(accessToken);
    }
}