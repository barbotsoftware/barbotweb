package barbot.database.service;

import barbot.database.model.Ingredient;

/**
 * Created by Naveen on 4/13/17.
 */
public interface IngredientService {
    Ingredient findByUid(String ingredientId);
    Ingredient findByName(String name);
}
