package barbot.database.service;

import java.util.List;

import barbot.database.model.Barbot;
import barbot.database.model.BarbotContainer;
import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;

/**
 * Created by Naveen on 4/12/17.
 */
public interface BarbotService {
    Barbot findById(int id);

    Barbot findByUid(String uid);

    List<Recipe> getRecipes(Barbot barbot);

    List<Ingredient> getIngredients(Barbot barbot);

    List<BarbotContainer> getBarbotContainers(Barbot barbot);
}
