package barbot.database.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;

import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;

public interface RecipeRepository extends BaseRepository<Recipe, Long> {

    List<Recipe> findByName(String name);

    Recipe findByUid(String uid);

    List<Recipe> findByCustomTrue();

    List<Recipe> findByCustomFalse();

    @Query("select ingredient from Ingredient ingredient " +
            "inner join ingredient.recipes recipe " +
            "where recipe = :recipe")
    Set<Ingredient> findIngredients(Recipe recipe);
}
