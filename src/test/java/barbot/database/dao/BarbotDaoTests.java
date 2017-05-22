package barbot.database.dao;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.ArrayList;
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

    private final int listSize = 10;

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

    }

    @Test
    public void testGetIngredients() {

    }

    private void setUpTestData() {
        barbots = new ArrayList<>();

        for (int i = 1; i < listSize; i++) {
            Barbot barbot = new Barbot();
            barbot.setId(i);
            barbot.setUid("drinkOrder_xxxxx" + i);
            barbots.add(barbot);
        }

        ingredients = new ArrayList<>();

        for (int i = 1; i < listSize; i++) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(i);
            ingredient.setUid("ingredient_xxxxx" + i);
            ingredients.add(ingredient);
        }

        // init BarbotContainers, set Ingredients

        // init Recipes
    }
}
