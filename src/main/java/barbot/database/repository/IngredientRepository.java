package barbot.database.repository;

import barbot.database.model.Ingredients;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientRepository extends BaseRepository<Ingredients, Long> {

    @Query("SELECT i FROM Ingredients i"
        + " JOIN BarbotContainers bc ON bc.ingredientId = i.id"
        + " WHERE bc.barbotId = ?1")
    List<Ingredients> findByBarbotId(String barbotId);

    Ingredients findByUid(String uid);
    Ingredients findByName(String name);
}
