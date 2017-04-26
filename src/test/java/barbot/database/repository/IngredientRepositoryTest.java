package barbot.database.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import barbot.database.model.Ingredient;

/**
 * Created by Naveen on 4/25/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IngredientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IngredientRepository repository;

    @Test
    public void testFindByUid() throws Exception {
        String uid = "ingredient_123456";
        String name = "Gin";

        this.entityManager.persist(new Ingredient(uid, name));
        Ingredient ingredient = this.repository.findByUid(uid);

        assertThat(ingredient.getUid()).isEqualTo(uid);
        assertThat(ingredient.getName()).isEqualTo(name);
    }

    @Test
    public void testFindByName() throws Exception {

        // Persist test Recipes
        for (int i = 0; i < 10; i++) {
            this.entityManager.persist(new Ingredient("ingredient_12345" + i, "Ingredient" + i));
        }

        List<Ingredient> ingredients = this.repository.findByName("Ingredient");
        assertThat(ingredients.size() == 10);
    }
}
