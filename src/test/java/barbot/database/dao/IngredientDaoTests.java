package barbot.database.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import barbot.config.TestDatabaseConfig;
import barbot.database.model.Ingredient;

/**
 * Created by Naveen on 5/21/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = TestDatabaseConfig.class)
public class IngredientDaoTests extends BaseDaoTests {

    private IngredientDao ingredientDao;

    private List<Ingredient> ingredients;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        ingredientDao = new IngredientDao();
        ingredientDao.setHibernateTemplate(mockTemplate);

        setUpTestData();
    }

    @Test
    public void testFindByUid() {
        String uid = ingredients.get(0).getUid();

        (Mockito.doReturn(ingredients).when(mockTemplate))
                .find("FROM Ingredient WHERE uid = ?", uid);

        Ingredient result = ingredientDao.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    @Test
    public void testFindById() {
        int id = ingredients.get(0).getId();

        (Mockito.doReturn(ingredients.get(0)).when(mockTemplate))
                .get(Ingredient.class, id);

        Ingredient result = ingredientDao.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    private void setUpTestData() {
        ingredients = testDataHelper.createIngredientList(listSize);
    }
}
