package barbot.websocket.command;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import barbot.database.model.Recipe;
import barbot.database.service.RecipeService;
import barbot.utils.Constants;

/**
 * Created by Naveen on 4/11/17.
 */
public class GetRecipeDetails extends BaseCommand {

    @Autowired
    RecipeService recipeService;

    public GetRecipeDetails(HashMap msg) {
        message = msg;
    }

    @Override
    public Object execute() {
        String recipeId = (String) message.get("recipe_id");

        Recipe recipe = recipeService.findById(recipeId);

        // TODO: Get Ingredients for Recipe

        Map response = new HashMap<String, Object>();

        response.put(Constants.KEY_TYPE, Constants.KEY_RESPONSE);
        response.put(Constants.KEY_COMMAND, Constants.CMD_GET_RECIPE_DETAILS);

        // reponse.put(Constants.KEY_DATA, recipe.toJSON());

        return response;
    }

    @Override
    public boolean validate() {
        // TODO: Validate RecipeID

        return super.validate();
    }
}
