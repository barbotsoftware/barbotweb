package barbot.database.repository;

import java.util.List;

import barbot.database.model.Recipe;

public interface RecipeRepository extends BaseRepository<Recipe, Long> {

    List<Recipe> findByName(String name);

    Recipe findByUid(String uid);

    List<Recipe> findByCustomTrue();

    List<Recipe> findByCustomFalse();
}
