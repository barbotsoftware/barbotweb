package barbot.websocket.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;

import barbot.database.model.Barbot;
import barbot.database.model.DrinkOrder;
import barbot.database.model.Recipe;
import barbot.database.model.User;
import barbot.database.service.BarbotService;
import barbot.database.service.DrinkOrderService;
import barbot.database.service.RecipeService;

/**
 * Created by Naveen on 5/29/17.
 */
public class OrderDrinkTests extends CommandTests {

    @Mock
    private BarbotService barbotService;

    @Mock
    private RecipeService recipeService;

    @Mock
    private DrinkOrderService drinkOrderService;

    @Mock
    private ApplicationEventPublisher publisher;

    private String barbotId;
    private String recipeId;
    private Barbot barbot;
    private Recipe recipe;
    private User user;
    private DrinkOrder drinkOrder;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testExecute() {
        (Mockito.doReturn(barbot)).when(barbotService).findByUid(barbotId);
        (Mockito.doReturn(recipe)).when(recipeService).findByUid(recipeId);

        DrinkOrder result = (DrinkOrder) command.execute();

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(drinkOrder);
    }

    @Test
    public void testValidate() {
        (Mockito.doReturn(true)).when(fieldValidator).validate((HashMap)msg.get("data"), fieldsToValidate);

        boolean result = command.validate();

        assertThat(result).isTrue();
    }

    private void setUpTestData() {
        barbotId = "barbot_123456";
        recipeId = "recipe_123456";

        barbot = new Barbot(barbotId);
        recipe = new Recipe(recipeId);
        user = new User();
        drinkOrder = new DrinkOrder(user, recipe, barbot, 0, 0);

        HashMap data = new HashMap<>();
        data.put("barbot_id", barbotId);
        data.put("recipe_id", recipeId);

        msg.put("data", data);

        fieldsToValidate.put("barbot_id", "required|exists:barbot");
        fieldsToValidate.put("recipe_id", "required|exists:recipe");

        command = new OrderDrink(drinkOrderService, recipeService, barbotService, fieldValidator, helperMethods, publisher,
                msg, user);
    }
}
