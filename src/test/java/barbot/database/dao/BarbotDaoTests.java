package barbot.database.dao;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private Set<BarbotContainer> barbotContainers;

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

    private void setUpTestData() {

        // Barbots
        barbots = new ArrayList<>();

        for (int i = 1; i <= listSize; i++) {
            Barbot barbot = new Barbot();
            barbot.setId(i);
            barbot.setUid("drinkOrder_xxxxx" + i);
            barbots.add(barbot);
        }

        // Ingredients
        ingredients = new ArrayList<>();

        for (int i = 1; i <= listSize; i++) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(i);
            ingredient.setUid("ingredient_xxxxx" + i);
            ingredients.add(ingredient);
        }

        // BarbotContainers
        barbotContainers = new HashSet<>();

        for (int i = 1; i <= listSize; i++) {
            BarbotContainer bc = new BarbotContainer();
            bc.setId(i);
            bc.setIngredient(ingredients.get(i-1));
            bc.setBarbot(barbots.get(0));
            barbotContainers.add(bc);
        }

        // Set BarbotContainers to first Barbot
        barbots.get(0).setBarbotContainers(barbotContainers);

        // Recipes
        recipes = new ArrayList<>();

        for (int i = 1; i <= listSize; i++) {
            Recipe recipe = new Recipe();
            recipe.setId(i);
            recipe.setUid("recipe_xxxxx" + i);
            recipe.setIngredients(new HashSet(ingredients));
            recipes.add(recipe);
        }
    }
}
