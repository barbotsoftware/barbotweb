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
public class RecipeDaoTests {

    private RecipeDao recipeDao;

    @Mock
    private HibernateTemplate mockTemplate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeDao = new RecipeDao();
        recipeDao.setHibernateTemplate(mockTemplate);
    }

    @Test
    public void testFindByUid() {
        String uid = "recipe_2beb3b";

        Recipe recipe = new Recipe(uid);

        List list = new ArrayList<>();
        list.add(recipe);

        (Mockito.doReturn(list).when(mockTemplate))
                .find("FROM Recipe WHERE uid = ?", uid);

        assertThat(recipeDao.findByUid(uid).getUid()).isEqualTo(uid);
    }
}
