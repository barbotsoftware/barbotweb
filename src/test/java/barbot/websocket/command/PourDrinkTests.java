package barbot.websocket.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.model.DrinkOrder;
import barbot.database.model.Recipe;
import barbot.database.service.DrinkOrderService;

/**
 * Created by Naveen on 5/29/17.
 */
public class PourDrinkTests extends CommandTests {

    @Mock
    private DrinkOrderService drinkOrderService;

    private DrinkOrder drinkOrder;

    private String drinkOrderId;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testExecute() {
        (Mockito.doReturn(drinkOrder)).when(drinkOrderService).findByUid(drinkOrderId);

        Map results = (Map) command.execute();

        assertThat(results.get("result")).isEqualTo("success");
    }

    @Test
    public void testValidate() {
        boolean result = command.validate();

        assertThat(result).isTrue();
    }

    private void setUpTestData() {
        drinkOrderId = "drinkOrder_123456";

        drinkOrder = new DrinkOrder(drinkOrderId);
        drinkOrder.setRecipe(new Recipe());

        HashMap data = new HashMap<>();
        data.put("drink_order_id", drinkOrderId);

        msg.put("data", data);

        command = new PourDrink(drinkOrderService, fieldValidator, helperMethods, msg);
    }
}
