package barbot.database.service;

import java.util.List;

import barbot.database.model.Recipe;

/**
 * Created by Naveen on 4/12/17.
 */
public interface RecipeService {
    Recipe create(Recipe recipe);
    Recipe findById(String recipeId);
    List<Recipe> findAll();
}