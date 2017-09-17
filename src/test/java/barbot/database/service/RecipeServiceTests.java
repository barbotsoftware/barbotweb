package barbot.database.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.dao.RecipeDao;
import barbot.database.model.Recipe;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 5/27/17.
 */
public class RecipeServiceTests extends BaseServiceTests {

    @InjectMocks
    private RecipeService recipeService = new RecipeServiceImpl();

    @Mock
    private RecipeDao recipeDao;

    private List<Recipe> recipes;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testCreate() {
        recipeService.create(recipes.get(0));

        Mockito.verify(recipeDao).save(recipes.get(0));
    }

    @Test
    public void testFindByUid() {
        Recipe recipe = recipes.get(0);
        String uid = recipe.getUid();

        (Mockito.doReturn(recipe)).when(recipeDao).findByUid(uid);

        Recipe result = recipeService.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    @Test
    public void testFindAll() {
        (Mockito.doReturn(recipes)).when(recipeDao).findAll();

        List<Recipe> results = recipeService.findAll();

        assertThat(results).isNotNull();
        assertThat(results.size()).isEqualTo(listSize);
        assertThat(results.get(0)).isEqualTo(recipes.get(0));
    }

    private void setUpTestData() {
        recipes = testDataHelper.createRecipeList(listSize, null);
    }
}
