package barbot.database.service;

import java.util.List;

import barbot.database.model.Recipe;

/**
 * Created by Naveen on 4/12/17.
 */
public interface RecipeService {
    void create(Recipe recipe);
    Recipe findByUid(String recipeId);
    List<Recipe> findAll();
}