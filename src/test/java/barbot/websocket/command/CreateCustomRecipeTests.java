package barbot.websocket.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;
import barbot.database.model.User;
import barbot.database.service.IngredientService;
import barbot.database.service.RecipeService;

/**
 * Created by Naveen on 5/27/17.
 */
public class CreateCustomRecipeTests extends CommandTests {

    @Mock
    private RecipeService recipeService;

    @Mock
    private IngredientService ingredientService;

    private String recipeName;
    private String ingredientId;
    private User user;
    private ArrayList<HashMap> ingredients;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testExecute() {
        (Mockito.doReturn(new Ingredient(ingredientId))).when(ingredientService).findByUid(ingredientId);

        Recipe result = (Recipe) command.execute();

        assertThat(result.getCreatedBy()).isEqualTo(user);
        assertThat(result.getRecipeIngredients()).isNotNull();
        assertThat(result.getRecipeIngredients().size()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo(recipeName);
        assertThat(result.getCustom()).isTrue();
    }

    @Test
    public void testValidate() {
        (Mockito.doReturn(true)).when(fieldValidator).validate((HashMap)msg.get("data"), fieldsToValidate);

        boolean result = command.validate();

        assertThat(result).isTrue();
    }

    private void setUpTestData() {

        recipeName = "Test Recipe";
        ingredientId = "ingredient_123456";
        user = new User();

        ingredients = new ArrayList<>();
        HashMap ingredient = new HashMap<>();
        ingredient.put("ingredient_id", ingredientId);
        ingredient.put("amount", "1.0");
        ingredients.add(ingredient);

        HashMap data = new HashMap<>();
        data.put("name", recipeName);
        data.put("ingredients", ingredients);

        msg.put("data", data);

        fieldsToValidate.put("name", "required");

        command = new CreateCustomRecipe(recipeService, ingredientService, fieldValidator, helperMethods, msg, user);
    }
}
