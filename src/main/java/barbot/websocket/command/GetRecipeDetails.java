package barbot.websocket.command;

import java.util.HashMap;

import barbot.database.model.View;
import barbot.database.service.RecipeService;
import barbot.utils.Constants;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 4/11/17.
 */
public class GetRecipeDetails extends BaseCommand {

    RecipeService recipeService;

    public GetRecipeDetails(RecipeService recipeService, FieldValidator validator, HelperMethods hlpr, HashMap msg) {
        super(msg, hlpr, validator);
        setJsonView(View.Summary.class);
        this.recipeService = recipeService;
    }

    @Override
    public Object execute() {
        // Get Recipe ID from request
        String recipeId = (String) data.get(Constants.KEY_DATA_RECIPE_ID);

        // Get Recipe from service
        HashMap result = new HashMap();
        result.put("recipe", recipeService.findByUid(recipeId));
        return result;
    }

    @Override
    public boolean validate() {
        if(!super.validate())
            return false;

        HashMap fieldsToValidate = new HashMap();
        fieldsToValidate.put(Constants.KEY_DATA_RECIPE_ID, "required|exists:recipe");

        if(!fieldValidator.validate((HashMap)message.get(Constants.KEY_DATA), fieldsToValidate)) {
            error = fieldValidator.getErrors();
            return false;
        }

        return true;
    }
}
