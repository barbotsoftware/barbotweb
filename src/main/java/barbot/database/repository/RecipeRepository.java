package barbot.database.repository;

import barbot.database.model.Recipes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipes, Long> {
    List<Recipes> findByName(String name);
    Recipes findByUid(String uid);
    List<Recipes> findByCustomTrue();
    List<Recipes> findByCustomFalse();
}
