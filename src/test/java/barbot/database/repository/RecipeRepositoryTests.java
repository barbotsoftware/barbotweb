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

import barbot.database.model.Recipe;

/**
 * Created by Naveen on 4/15/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecipeRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RecipeRepository repository;

    @Test
    public void testFindByUid() throws Exception {
        this.entityManager.persist(new Recipe("recipe_123456", "Gin and Tonic", ""));
        Recipe recipe = this.repository.findByUid("recipe_123456");
        assertThat(recipe.getUid()).isEqualTo("recipe_123456");
        assertThat(recipe.getName()).isEqualTo("Gin and Tonic");
    }

    @Test
    public void testFindByName() throws Exception {

        // Persist test Recipes
        for (int i = 0; i < 10; i++) {
            this.entityManager.persist(new Recipe("recipe_12345" + i, "Recipe" + i, "http://barbot.io/" + i));
        }

        List<Recipe> recipes = this.repository.findByName("Recipe");
        assertThat(recipes.size() == 10);
    }
}
