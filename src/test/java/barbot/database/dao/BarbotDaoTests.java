package barbot.database.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import barbot.config.TestDatabaseConfig;
import barbot.database.model.Barbot;
import barbot.database.model.BarbotContainer;
import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;

/**
 * Created by Naveen on 5/20/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = TestDatabaseConfig.class)
public class BarbotDaoTests extends BaseDaoTests {

    private BarbotDao barbotDao;

    private List<Barbot> barbots;

    private List<BarbotContainer> barbotContainers;

    private List<Recipe> recipes;

    private List<Ingredient> ingredients;

    private final int listSize = 9;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        barbotDao = new BarbotDao();
        barbotDao.setHibernateTemplate(mockTemplate);

        setUpTestData();
    }

    @Test
    public void testFindByUid() {
        String uid = barbots.get(0).getUid();

        (Mockito.doReturn(barbots).when(mockTemplate))
                .find("FROM Barbot WHERE uid = ?", uid);

        Barbot result = barbotDao.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    @Test
    public void testFindById() {
        int id = barbots.get(0).getId();

        (Mockito.doReturn(barbots.get(0)).when(mockTemplate))
                .get(Barbot.class, id);

        Barbot result = barbotDao.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    public void testGetRecipes() {
        Barbot barbot = barbots.get(0);
        int id = barbot.getId();

        (Mockito.doReturn(barbot).when(mockTemplate))
                .get(Barbot.class, id);

        (Mockito.doReturn(recipes).when(mockTemplate))
                .find("FROM Recipe WHERE custom = 0");

        List<Recipe> results = barbotDao.getRecipes(barbot);

        assertThat(results).isNotNull();
        assertThat(results.size()).isEqualTo(listSize);
    }

    @Test
    public void testGetIngredients() {
        Barbot barbot = barbots.get(0);
        int id = barbot.getId();

        (Mockito.doReturn(barbot).when(mockTemplate))
            .get(Barbot.class, id);

        List<Ingredient> results = barbotDao.getIngredients(barbot);

        assertThat(results).isNotNull();
        assertThat(results.size()).isEqualTo(listSize);
    }

    @Test
    public void testGetBarbotContainers() {
        Barbot barbot = barbots.get(0);
        int id = barbot.getId();

        (Mockito.doReturn(barbot).when(mockTemplate))
            .get(Barbot.class, id);

        List<BarbotContainer> results = barbotDao.getBarbotContainers(barbot);

        assertThat(results).isNotNull();
        assertThat(results.size()).isEqualTo(listSize);
    }

    private void setUpTestData() {

        // Barbots
        barbots = testDataHelper.createBarbotList(listSize);

        // Ingredients
        ingredients = testDataHelper.createIngredientList(listSize);

        // BarbotContainers
        barbotContainers = testDataHelper.createBarbotContainerList(listSize,
                ingredients, barbots);

        // Set BarbotContainers to first Barbot
        barbots.get(0).setBarbotContainers(new HashSet<>(barbotContainers));

        // Recipes
        recipes = testDataHelper.createRecipeList(listSize, ingredients);
    }
}
