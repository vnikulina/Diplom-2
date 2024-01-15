import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.*;

public class CreateOrderWithoutAuthorizationTest {
    private ClientOrders clientOrders;
    private ClientIngredients clientIngredients;

    @Before
    @Step("Prepare to create order by not authenticated user")
    public void setUp() {
        clientIngredients = new ClientIngredients();
        clientOrders = new ClientOrders();
    }

    @Test
    @DisplayName("Create order by not authenticated user")
    public void notAuthenticatedUserCanCreateOrder() {
        // create order with all ingredients
        ValidatableResponse responseIngredients = clientIngredients.getIngredients();
        ArrayList<HashMap<String, String>> responseData = responseIngredients.extract().path("data");
        Ingredients ingredients = GeneratorIngredients.getValidIngredientsHash(responseData);
        // create order
        ValidatableResponse responseCreateOrder = clientOrders.createOrderUnauthorizedUserWithIngredients(ingredients);
        int actualStatusCodeCreateOrder = responseCreateOrder.extract().statusCode();
        boolean isSuccessInMessageTrueCreateOrder = responseCreateOrder.extract().path("success");
        assertEquals(200, actualStatusCodeCreateOrder);
        assertTrue(isSuccessInMessageTrueCreateOrder);
    }

    @Test
    @DisplayName("Create order by not authenticated user with invalid ingredients hash")
    public void notAuthenticatedUserCanNotCreateOrderWithInvalidIngredients() {
        Ingredients ingredients = GeneratorIngredients.getInvalidIngredientsHash();
        // create order
        ValidatableResponse responseCreateOrder = clientOrders.createOrderUnauthorizedUserWithIngredients(ingredients);
        int actualStatusCodeCreateOrder = responseCreateOrder.extract().statusCode();
        assertEquals(500, actualStatusCodeCreateOrder);
    }

    @Test
    @DisplayName("Create order by not authenticated user with without ingredients")
    public void notAuthorizedUserCanNotCreateOrderWithoutIngredients() {
        ValidatableResponse responseCreateOrder = clientOrders.createOrderUnauthorizedUserWithoutIngredients();
        int actualStatusCodeCreateOrder = responseCreateOrder.extract().statusCode();
        boolean isSuccessInMessageFalseCreateOrder = responseCreateOrder.extract().path("success");
        String responseMessage = responseCreateOrder.extract().path("message");
        assertEquals(400, actualStatusCodeCreateOrder);
        assertFalse(isSuccessInMessageFalseCreateOrder);
        assertEquals("Ingredient ids must be provided", responseMessage);
    }
}