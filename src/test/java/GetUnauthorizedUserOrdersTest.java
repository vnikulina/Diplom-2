import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GetUnauthorizedUserOrdersTest {
    private ClientOrders clientOrders;

    @Before
    @Step("Prepare to get orders list of not authenticated user")
    public void setUp() {
        clientOrders = new ClientOrders();
    }

    @Test
    @DisplayName("Can't get orders list of unauthorized user")
    public void canNotGetOrdersListOfNotAuthenticatedUser() {
        ValidatableResponse responseUserOrders = clientOrders.getUnauthorizedUserOrders();
        int actualStatusCodeOrders = responseUserOrders.extract().statusCode();
        boolean isSuccessInMessageFalseOrders = responseUserOrders.extract().path("success");
        String responseMessage = responseUserOrders.extract().path("message");
        assertEquals(401, actualStatusCodeOrders);
        assertFalse(isSuccessInMessageFalseOrders);
        assertEquals("You should be authorised", responseMessage);
    }
}