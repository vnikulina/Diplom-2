import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UpdateUnauthorizedUserDataTest {
    private UserClient userClient;

    @Before
    @Step("Prepare to change data of unauthorized user")
    public void setUp() {
        userClient = new UserClient();
    }

    @Test
    @DisplayName("Can't update email because unauthorized")
    public void notAuthorizedUserCanNotUpdateEmail() {
        User changedUser = User.getRandomUser();
        ValidatableResponse responseUpdateName = userClient.updateUnauthorizedUserData(UserData.from(changedUser));
        int actualStatusCodeChange = responseUpdateName.extract().statusCode();
        boolean isSuccessInMessageFalseChange = responseUpdateName.extract().path("success");
        String responseMessage = responseUpdateName.extract().path("message");
        assertEquals(401, actualStatusCodeChange);
        assertFalse(isSuccessInMessageFalseChange);
        assertEquals("You should be authorised", responseMessage);
    }

    @Test
    @DisplayName("Can't update name because unauthorized")
    public void notAuthorizedUserCanNotUpdateName() {
        User changedUser = User.getRandomUser();
        ValidatableResponse responseUpdateName = userClient.updateUnauthorizedUserData(UserData.from(changedUser));
        int actualStatusCodeChange = responseUpdateName.extract().statusCode();
        boolean isSuccessInMessageFalseChange = responseUpdateName.extract().path("success");
        String responseMessage = responseUpdateName.extract().path("message");
        assertEquals(401, actualStatusCodeChange);
        assertFalse(isSuccessInMessageFalseChange);
        assertEquals("You should be authorised", responseMessage);
    }
}