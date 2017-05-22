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
import barbot.database.model.Recipe;

/**
 * Created by Naveen on 5/15/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = TestDatabaseConfig.class)
public class RecipeDaoTests extends BaseDaoTests {

    private RecipeDao recipeDao;

    private List<Recipe> recipes;

    private final int recipeListSize = 10;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        recipeDao = new RecipeDao();
        recipeDao.setHibernateTemplate(mockTemplate);

        setUpTestData();
    }

    @Test
    public void testFindByUid() {
        String uid = recipes.get(0).getUid();

        (Mockito.doReturn(recipes).when(mockTemplate))
                .find("FROM Recipe WHERE uid = ?", uid);

        Recipe result = recipeDao.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    @Test
    public void testFindById() {
        int id = recipes.get(0).getId();

        (Mockito.doReturn(recipes.get(0)).when(mockTemplate))
                .get(Recipe.class, id);

        Recipe result = recipeDao.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    public void testFindAll() {
        (Mockito.doReturn(recipes).when(mockTemplate))
                .find("FROM Recipe");

        List<Recipe> result = recipeDao.findAll();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(recipeListSize);
    }

    @Test
    public void testSave() {
        Mockito.when(mockTemplate.save(recipes.get(0))).thenReturn((long)1);

        recipeDao.save(recipes.get(0));

        Mockito.verify(mockTemplate).save(recipes.get(0));
    }

    private void setUpTestData() {
        recipes = new ArrayList<>();

        for (int i = 0; i < recipeListSize; i++) {
            Recipe recipe = new Recipe();
            recipe.setId(i);
            recipe.setUid("recipe_xxxxx" + i);
            recipes.add(recipe);
        }
    }
}
