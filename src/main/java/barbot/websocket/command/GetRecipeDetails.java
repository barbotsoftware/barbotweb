package barbot.websocket.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;
import barbot.database.model.View;
import barbot.database.service.RecipeService;
import barbot.utils.Constants;

/**
 * Created by Naveen on 4/11/17.
 */
public class GetRecipeDetails extends BaseCommand {

    @Autowired
    RecipeService recipeService;

    public GetRecipeDetails(RecipeService recipeService, HashMap msg) {
        super(msg);
        setJsonView(View.Detail.class);
        this.recipeService = recipeService;
    }

    @Override
    public Object execute() {

        // Get Recipe ID from request
        String recipeId = (String) data.get(Constants.KEY_DATA_RECIPE_ID);

        // Get Recipe from service
        Recipe recipe = recipeService.findById(recipeId);

        // Are Ingredients loaded automatically from Recipe retrieval?
        // Get Ingredients for Recipe from service
        // Set<Ingredient> ingredients = recipeService.getIngredients(recipe);

        // Set Ingredients
        // recipe.setIngredients(ingredients);

        return recipe;
    }

    @Override
    public boolean validate() {
        // TODO: Validate RecipeID

        return super.validate();
    }
}
