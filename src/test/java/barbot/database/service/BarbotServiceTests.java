package barbot.database.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.dao.BarbotDao;
import barbot.database.model.Barbot;
import barbot.database.model.BarbotContainer;
import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;

/**
 * Created by Naveen on 5/27/17.
 */
public class BarbotServiceTests extends BaseServiceTests {

    @InjectMocks
    private BarbotService barbotService = new BarbotServiceImpl();

    @Mock
    private BarbotDao barbotDao;

    private Barbot barbot;

    private List<Recipe> recipes;

    private List<Ingredient> ingredients;

    private List<BarbotContainer> barbotContainers;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testFindById() {
        int id = barbot.getId();

        (Mockito.doReturn(barbot)).when(barbotDao).findById(id);

        Barbot result = barbotService.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    public void testFindByUid() {
        String uid = barbot.getUid();

        (Mockito.doReturn(barbot)).when(barbotDao).findByUid(uid);

        Barbot result = barbotService.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    @Test
    public void testGetRecipes() {
        (Mockito.doReturn(recipes)).when(barbotDao).getRecipes(barbot);

        List<Recipe> results = barbotService.getRecipes(barbot);

        assertThat(results).isNotNull();
        assertThat(results.size()).isEqualTo(listSize);
    }

    @Test
    public void testGetIngredients() {
        (Mockito.doReturn(ingredients)).when(barbotDao).getIngredients(barbot);

        List<Ingredient> results = barbotService.getIngredients(barbot);

        assertThat(results).isNotNull();
        assertThat(results.size()).isEqualTo(listSize);
    }

    @Test
    public void testGetBarbotContainers() {
        (Mockito.doReturn(barbotContainers)).when(barbotDao).getBarbotContainers(barbot);

        List<BarbotContainer> results = barbotService.getBarbotContainers(barbot);

        assertThat(results).isNotNull();
        assertThat(results.size()).isEqualTo(listSize);
    }

    private void setUpTestData() {

        // Barbot
        barbot = new Barbot("barbot_123456");
        barbot.setId(1);

        List<Barbot> barbots = new ArrayList<>();
        barbots.add(barbot);

        // Ingredients
        ingredients = testDataHelper.createIngredientList(listSize);

        // Recipes
        recipes = testDataHelper.createRecipeList(listSize, ingredients);

        // BarbotContainers
        barbotContainers = testDataHelper.createBarbotContainerList(listSize,
                ingredients, barbots);

        // Set BarbotContainers to first Barbot
        barbot.setBarbotContainers(new HashSet<>(barbotContainers));
    }
}
