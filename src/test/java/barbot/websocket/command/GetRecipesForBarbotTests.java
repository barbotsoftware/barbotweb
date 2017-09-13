package barbot.websocket.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;

import barbot.database.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.model.Barbot;
import barbot.database.model.Recipe;
import barbot.database.service.BarbotService;

/**
 * Created by Naveen on 5/29/17.
 */
public class GetRecipesForBarbotTests extends CommandTests {

    @Mock
    private BarbotService barbotService;

    @Mock
    private CategoryService categoryService;

    private Barbot barbot;

    private String barbotId;

    private List<Recipe> recipes;

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

        (Mockito.doReturn(recipes)).when(barbotService).getRecipes(barbot);

        List<Recipe> results = (List<Recipe>) command.execute();

        assertThat(results).isNotNull();
        assertThat(results).isEqualTo(recipes);
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

        msg.put("data", data);

        fieldsToValidate.put("barbot_id", "required|exists:barbot");

        barbot = new Barbot(barbotId);

        recipes = testDataHelper.createRecipeList(listSize, null);

        command = new GetRecipesForBarbot(barbotService, categoryService, fieldValidator, helperMethods, msg);
    }
}
