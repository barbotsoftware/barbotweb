package barbot.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import barbot.database.model.Ingredient;

public interface IngredientRepository extends BaseRepository<Ingredient, Long> {

    /*@Query("SELECT i FROM Ingredient i"
        + " JOIN BarbotContainer bc ON bc.ingredientId = i.id"
        + " WHERE bc.barbotId = ?1")
    List<Ingredient> findByBarbotId(String barbotId);*/

    Ingredient findByUid(String uid);
    List<Ingredient> findByName(String name);
}
