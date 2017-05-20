package barbot.database.dao;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.hibernate5.HibernateTemplate;
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

        recipes = new ArrayList<>();

        for (int i = 0; i < recipeListSize; i++) {
            Recipe recipe = new Recipe();
            recipe.setId(i);
            recipe.setUid("recipe_xxxxx" + i);
            recipes.add(recipe);
        }
    }

    @Test
    public void testFindByUid() {
        String uid = recipes.get(0).getUid();

        (Mockito.doReturn(recipes).when(mockTemplate))
                .find("FROM Recipe WHERE uid = ?", uid);

        assertThat(recipeDao.findByUid(uid).getUid()).isEqualTo(uid);
    }

    @Test
    public void testFindById() {
        int id = recipes.get(0).getId();

        (Mockito.doReturn(recipes.get(0)).when(mockTemplate))
                .get(Recipe.class, id);

        assertThat(recipeDao.findById(id).getId()).isEqualTo(id);
    }

    @Test
    public void testFindAll() {
        (Mockito.doReturn(recipes).when(mockTemplate))
                .find("FROM Recipe");

        assertThat(recipeDao.findAll().size()).isEqualTo(recipeListSize);
    }

    @Test
    public void testSave() {

    }
}
