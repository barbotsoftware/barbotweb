package barbot.database.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;

import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;

public interface IngredientRepository extends BaseRepository<Ingredient, Long> {

    Ingredient findByUid(String uid);
    List<Ingredient> findByName(String name);

    @Query("select ingredient from Ingredient ingredient " +
            "inner join ingredient.recipes recipe " +
            "where recipe = ?1")
    Set<Ingredient> findByRecipe(Recipe recipe);

    /*@Query("SELECT i FROM Ingredient i"
        + " JOIN BarbotContainer bc ON bc.ingredientId = i.id"
        + " WHERE bc.barbotId = ?1")
    List<Ingredient> findByBarbotId(String barbotId);*/
}
