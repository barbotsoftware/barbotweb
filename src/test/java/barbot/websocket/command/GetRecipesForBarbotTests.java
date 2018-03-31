package barbot.websocket.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.model.Barbot;
import barbot.database.model.Category;
import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;
import barbot.database.service.BarbotService;
import barbot.database.service.CategoryService;

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

    private Category category;

    private String categoryId;

    private List<Recipe> recipes;

    private List<String> ingredientIds;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testExecute() {
        (Mockito.doReturn(barbot)).when(barbotService).findByUid(barbotId);

        (Mockito.doReturn(category)).when(categoryService).findByUid(categoryId);

        (Mockito.doReturn(recipes)).when(barbotService).getRecipes(barbot, category, ingredientIds);

        HashMap result = (HashMap) command.execute();

        List<Recipe> recipeList = (List<Recipe>) result.get("recipes");

        assertThat(recipeList).isNotNull();
        assertThat(recipeList).isEqualTo(recipes);
    }

    @Test
    public void testValidate() {
        (Mockito.doReturn(true)).when(fieldValidator).validate((HashMap)msg.get("data"), fieldsToValidate);

        boolean result = command.validate();

        assertThat(result).isTrue();
    }

    private void setUpTestData() {
        barbotId = "barbot_123456";
        categoryId = "category_123456";
        List<Ingredient> ingredients = testDataHelper.createIngredientList(3);

        HashMap data = new HashMap<>();
        data.put("barbot_id", barbotId);
        data.put("category_id", categoryId);

        msg.put("data", data);

        fieldsToValidate.put("barbot_id", "required|exists:barbot");

        barbot = new Barbot(barbotId);

        // empty IngredientIds list
        ingredientIds = new ArrayList<>();

        recipes = testDataHelper.createRecipeList(listSize, ingredients);

        category = new Category();
        category.setUid(categoryId);

        command = new GetRecipesForBarbot(barbotService, categoryService, fieldValidator, helperMethods, msg);
    }
}
