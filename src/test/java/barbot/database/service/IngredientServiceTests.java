package barbot.database.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import barbot.database.dao.IngredientDao;
import barbot.database.model.Ingredient;

/**
 * Created by Naveen on 5/27/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientServiceTests extends BaseServiceTests {

    @InjectMocks
    private IngredientService ingredientService = new IngredientServiceImpl();

    @Mock
    private IngredientDao ingredientDao;

    private Ingredient ingredient;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testFindByUid() {
        String uid = ingredient.getUid();

        (Mockito.doReturn(ingredient)).when(ingredientDao).findByUid(uid);

        Ingredient result = ingredientService.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    private void setUpTestData() {
        ingredient = new Ingredient("ingredient_123456");
    }
}
