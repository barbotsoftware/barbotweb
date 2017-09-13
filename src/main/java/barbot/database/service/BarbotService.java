package barbot.database.service;

import java.util.List;

import barbot.database.model.*;

/**
 * Created by Naveen on 4/12/17.
 */
public interface BarbotService {
    Barbot findById(int id);

    Barbot findByUid(String uid);

    List<Recipe> getRecipes(Barbot barbot);

    List<Recipe> getRecipes(Barbot barbot, Category category, List<String> ingredientIds);

    List<Ingredient> getIngredients(Barbot barbot);

    List<BarbotContainer> getBarbotContainers(Barbot barbot);
}
