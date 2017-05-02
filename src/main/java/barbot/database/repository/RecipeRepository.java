package barbot.database.repository;

import java.util.List;

import barbot.database.model.Barbot;
import barbot.database.model.Recipe;

public interface RecipeRepository extends BaseRepository<Recipe, Long> {

    List<Recipe> findByName(String name);

    Recipe findByUid(String uid);

    // TODO: Write Query for method
    List<Recipe> findByBarbot(Barbot barbot);
}
