package barbot.database.service;

import barbot.database.model.Ingredient;

/**
 * Created by Naveen on 4/13/17.
 */
public interface IngredientService {
    Ingredient findById(String ingredientId);
}
