import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RegisterUserWithoutDataTest {
    private UserClient userClient;

    @Before
    @Step("Prepare data for creating user without data")
    public void setUp() {
        userClient = new UserClient();
    }

    @Test
    @DisplayName("Can't create user without email")
    public void userWithoutEmailCanNotBeCreated() {
        User user = User.getUserWithoutEmail();
        ValidatableResponse responseRegister = userClient.register(user);
        int actualStatusCodeCreate = responseRegister.extract().statusCode();
        boolean isSuccessInMessageFalseRegister = responseRegister.extract().path("success");
        String responseMessage = responseRegister.extract().path("message");
        assertEquals(403, actualStatusCodeCreate);
        assertEquals("Email, password and name are required fields", responseMessage);
        assertFalse(isSuccessInMessageFalseRegister);
    }

    @Test
    @DisplayName("Can't create user without password")
    public void userWithoutPasswordCanNotBeCreated() {
        User user = User.getUserWithoutPassword();
        ValidatableResponse responseRegister = userClient.register(user);
        int actualStatusCodeCreate = responseRegister.extract().statusCode();
        boolean isSuccessInMessageFalseRegister = responseRegister.extract().path("success");
        String responseMessage = responseRegister.extract().path("message");
        assertEquals(403, actualStatusCodeCreate);
        assertEquals("Email, password and name are required fields", responseMessage);
        assertFalse(isSuccessInMessageFalseRegister);
    }

    @Test
    @DisplayName("Can't create user without name")
    public void userWithoutNameCanNotBeCreated() {
        User user = User.getUserWithoutName();
        ValidatableResponse responseRegister = userClient.register(user);
        int actualStatusCodeCreate = responseRegister.extract().statusCode();
        boolean isSuccessInMessageFalseRegister = responseRegister.extract().path("success");
        String responseMessage = responseRegister.extract().path("message");
        assertEquals(403, actualStatusCodeCreate);
        assertEquals("Email, password and name are required fields", responseMessage);
        assertFalse(isSuccessInMessageFalseRegister);
    }
}