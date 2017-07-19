package barbot.websocket.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.model.Barbot;
import barbot.database.model.BarbotContainer;
import barbot.database.model.Ingredient;
import barbot.database.service.BarbotContainerService;
import barbot.database.service.BarbotService;
import barbot.database.service.IngredientService;

/**
 * Created by Naveen on 6/6/17.
 */
public class SetContainersForBarbotTests extends CommandTests {

    @Mock
    private BarbotService barbotService;

    @Mock
    private BarbotContainerService barbotContainerService;

    @Mock
    private IngredientService ingredientService;

    private Barbot barbot;

    private String barbotId;

    private Ingredient ingredient;

    private BarbotContainer barbotContainer;

    private ArrayList<HashMap> barbotContainers;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testExecute() {
        (Mockito.doReturn(barbot)).when(barbotService).findByUid(barbotId);

        (Mockito.doReturn(ingredient)).when(ingredientService).findByUid(ingredient.getUid());

        (Mockito.doReturn(barbotContainer)).when(barbotContainerService).findByBarbotAndNumber(barbot, barbotContainer.getNumber());

        command.execute();

        Mockito.verify(barbotContainerService).update(barbotContainer);
    }

    @Test
    public void testValidate() {
        (Mockito.doReturn(true)).when(fieldValidator).validate((HashMap)msg.get("data"), fieldsToValidate);

        boolean result = command.validate();

        assertThat(result).isTrue();
    }

    private void setUpTestData() {
        barbotId = "barbot_123456";

        barbot = new Barbot(barbotId);

        ingredient = new Ingredient("ingredient_123456", "Vodka");
        ingredient.setId(1);

        barbotContainer = new BarbotContainer(barbot, ingredient, 1, 48, 48);
        barbotContainer.setId(1);

        barbotContainers = new ArrayList<>();
        HashMap bbc = new HashMap<>();
        bbc.put("number", barbotContainer.getNumber());
        bbc.put("ingredient_id", ingredient.getUid());
        bbc.put("current_volume", barbotContainer.getCurrentVolume());
        bbc.put("max_volume", barbotContainer.getMaxVolume());
        barbotContainers.add(bbc);

        HashMap data = new HashMap<>();
        data.put("barbot_id", barbotId);
        data.put("barbot_containers", barbotContainers);

        msg.put("data", data);

        fieldsToValidate.put("barbot_id", "required|exists:barbot");

        command = new SetContainersForBarbot(barbotService, ingredientService, barbotContainerService, fieldValidator,
                helperMethods, msg);
    }
}
