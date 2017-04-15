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

    public GetRecipeDetails(HashMap msg) {
        super(msg);
    }

    @Override
    public Object execute() {

        // Get Recipe ID from request
        String recipeId = (String) message.get("recipe_id");

        // Get Recipe from DB
        Recipe recipe = recipeService.findById(recipeId);

        // Get Ingredients for Recipe
        Set<Ingredient> ingredients = recipeService.getIngredients(recipe);

        // Set Ingredients
        recipe.setIngredients(ingredients);

        String recipeJSON = "";

        try {
            recipeJSON = mapper.writerWithView(View.Detail.class).writeValueAsString(recipe);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map response = new HashMap<String, Object>();

        response.put(Constants.KEY_TYPE, Constants.KEY_RESPONSE);
        response.put(Constants.KEY_COMMAND, Constants.CMD_GET_RECIPE_DETAILS);
        response.put(Constants.KEY_DATA, recipeJSON);

        // TODO: Return as map or string?
        return response;
    }

    @Override
    public boolean validate() {
        // TODO: Validate RecipeID

        return super.validate();
    }
}
