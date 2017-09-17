package barbot.websocket.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.model.Recipe;
import barbot.database.service.RecipeService;

/**
 * Created by Naveen on 5/29/17.
 */
public class GetRecipeDetailsTests extends CommandTests {

    @Mock
    private RecipeService recipeService;

    private Recipe recipe;

    private String recipeId;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testExecute() {
        (Mockito.doReturn(recipe)).when(recipeService).findByUid(recipeId);

        HashMap result = (HashMap) command.execute();

        Recipe recipe = (Recipe) result.get("recipe");

        assertThat(recipe).isNotNull();
        assertThat(recipe.getUid()).isEqualTo(recipeId);
    }

    @Test
    public void testValidate() {
        (Mockito.doReturn(true)).when(fieldValidator).validate((HashMap)msg.get("data"), fieldsToValidate);

        boolean result = command.validate();

        assertThat(result).isTrue();
    }

    private void setUpTestData() {
        recipeId = "recipe_123456";

        recipe = new Recipe(recipeId);

        HashMap data = new HashMap<>();
        data.put("recipe_id", recipeId);

        msg.put("data", data);

        fieldsToValidate.put("recipe_id", "required|exists:recipe");

        command = new GetRecipeDetails(recipeService, fieldValidator, helperMethods, msg);
    }
}
