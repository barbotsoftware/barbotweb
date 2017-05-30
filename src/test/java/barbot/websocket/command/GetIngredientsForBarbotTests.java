package barbot.websocket.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.model.Barbot;
import barbot.database.model.Ingredient;
import barbot.database.service.BarbotService;
import barbot.utils.Constants;

/**
 * Created by Naveen on 5/29/17.
 */
public class GetIngredientsForBarbotTests extends CommandTests {

    @Mock
    private BarbotService barbotService;

    private Barbot barbot;

    private String barbotId;

    private List<Ingredient> ingredients;

    private final int listSize = 9;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testExecute() {
        (Mockito.doReturn(barbot)).when(barbotService).findByUid(barbotId);

        (Mockito.doReturn(ingredients)).when(barbotService).getIngredients(barbot);

        List<Ingredient> results = (List<Ingredient>) command.execute();

        assertThat(results).isNotNull();
        assertThat(results).isEqualTo(ingredients);
    }

    @Test
    public void testValidate() {
        (Mockito.doReturn(true)).when(fieldValidator).validate((HashMap)msg.get("data"), fieldsToValidate);

        boolean result = command.validate();

        assertThat(result).isTrue();
    }

    private void setUpTestData() {
        barbotId = "barbot_123456";

        HashMap data = new HashMap<>();
        data.put("barbot_id", barbotId);

        msg.put(Constants.KEY_DATA, data);

        fieldsToValidate.put(Constants.KEY_DATA_BARBOT_ID, "required|exists:barbot");

        barbot = new Barbot(barbotId);

        ingredients = testDataHelper.createIngredientList(listSize);

        command = new GetIngredientsForBarbot(barbotService, fieldValidator, helperMethods, msg);
    }
}