package barbot.database.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import barbot.database.dao.BarbotDao;
import barbot.database.model.Barbot;
import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;

/**
 * Created by Naveen on 5/27/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BarbotServiceTests extends BaseServiceTests {

    @InjectMocks
    private BarbotService barbotService = new BarbotServiceImpl();

    @Mock
    private BarbotDao barbotDao;

    private Barbot barbot;

    private List<Recipe> recipes;

    private List<Ingredient> ingredients;

    private final int listSize = 10;

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

    private void setUpTestData() {
        barbot = new Barbot("barbot_123456");
        barbot.setId(1);

        ingredients = testDataHelper.createIngredientList(listSize);

        recipes = testDataHelper.createRecipeList(listSize, ingredients);
    }
}
