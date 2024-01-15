import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetAuthorizedUserOrdersTest {
    private UserClient userClient;
    private User user;
    private String accessToken;
    private ClientOrders clientOrders;

    @Before
    @Step("Prepare to get orders list of authenticated user")
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandomUser();
        clientOrders = new ClientOrders();
    }

    @Test
    @DisplayName("Get orders list of authenticated user")
    public void canGetOrdersListOfAuthenticated() {
        ValidatableResponse responseRegister = userClient.register(user);
        accessToken = responseRegister.extract().path("accessToken");
        ValidatableResponse responseUserOrders = clientOrders.getUserOrders(accessToken);
        int actualStatusCodeOrders = responseUserOrders.extract().statusCode();
        boolean isSuccessInMessageTrueOrders = responseUserOrders.extract().path("success");
        assertEquals(200, actualStatusCodeOrders);
        assertTrue(isSuccessInMessageTrueOrders);
    }

    @After
    @Step("Delete user")
    public void cleanUp() {
        userClient.deleteUser(accessToken);
    }
}