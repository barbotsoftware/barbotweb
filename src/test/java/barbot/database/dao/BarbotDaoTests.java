package barbot.database.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import barbot.config.TestDatabaseConfig;
import barbot.database.model.Barbot;
import barbot.database.model.BarbotContainer;
import barbot.database.model.BarbotGarnish;
import barbot.database.model.Category;
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

    private BCryptPasswordEncoder passwordEncoder;

    private List<Barbot> barbots;

    private List<BarbotContainer> barbotContainers;

    private List<BarbotGarnish> barbotGarnishes;

    private List<Recipe> recipes;

    private List<Ingredient> ingredients;

    private Category category;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        passwordEncoder = new BCryptPasswordEncoder();

        barbotDao = new BarbotDao();
        barbotDao.setHibernateTemplate(mockTemplate);
        barbotDao.setPasswordEncoder(passwordEncoder);

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
    public void testFindByName() {
        Barbot barbot = barbots.get(0);
        String name = barbots.get(0).getName();

        List<Barbot> results = new ArrayList<>();
        results.add(barbot);

        (Mockito.doReturn(results).when(mockTemplate))
                .find("FROM Barbot WHERE name = ?", name);

        Barbot result = barbotDao.findByName(name);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
    }

    @Test
    public void testFindByNameAndPassword() {
        Barbot barbot = barbots.get(0);
        String name = barbot.getName();
        String password = barbot.getPassword();

        barbot.setPassword(passwordEncoder.encode(barbot.getPassword()));

        List<Barbot> results = new ArrayList<>();
        results.add(barbot);

        (Mockito.doReturn(results).when(mockTemplate))
                .find("FROM Barbot WHERE name = ?", name);

        Barbot result = barbotDao.findByNameAndPassword(name, password);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getPassword()).isEqualTo(barbot.getPassword());
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

//    @Test
//    public void testGetRecipesWithCategory() {
//        Barbot barbot = barbots.get(0);
//
//        (Mockito.doReturn(ingredients).when(barbotDao))
//                .getIngredients(barbot);
//
//        (Mockito.doReturn(recipes).when(mockTemplate))
//                .find("FROM Recipe WHERE custom = 0");
//
//        List<Recipe> results = barbotDao.getRecipes(barbot, category, null);
//
//        assertThat(results).isNotNull();
//        assertThat(results.size()).isEqualTo(listSize);
//    }

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

//    @Test
//    public void testGetIngredientsWithFilter() {
//
//    }

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

    @Test
    public void testGetGarnishes() {
        Barbot barbot = barbots.get(0);
        int id = barbot.getId();

        (Mockito.doReturn(barbot).when(mockTemplate))
            .get(Barbot.class, id);

        List<BarbotGarnish> results = barbotDao.getGarnishes(barbot);

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

        // BarbotGarnishes
        barbotGarnishes = testDataHelper.createBarbotGarnishList(listSize, barbots);

        barbots.get(0).setGarnishes(new HashSet<>(barbotGarnishes));

        // Recipes
        recipes = testDataHelper.createRecipeList(listSize, ingredients);
    }
}
