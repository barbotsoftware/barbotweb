package barbot.websocket.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;
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

    RecipeService recipeService;

    private FieldValidator fieldValidator;

    public GetRecipeDetails(RecipeService recipeService, FieldValidator validator, HelperMethods hlpr, HashMap msg) {
        super(msg, hlpr);
        setJsonView(View.Summary.class);
        this.recipeService = recipeService;
        this.fieldValidator = validator;
    }

    @Override
    public Object execute() {
        // Get Recipe ID from request
        String recipeId = (String) data.get(Constants.KEY_DATA_RECIPE_ID);

        // Get Recipe from service
        return recipeService.findById(recipeId);
    }

    @Override
    public boolean validate() {
        if(!super.validate())
            return false;

        HashMap fieldsToValidate = new HashMap();
        fieldsToValidate.put("recipe_id", "required|exists:recipe");

        if(!fieldValidator.validate((HashMap)message.get("data"), fieldsToValidate)) {
            error = fieldValidator.getErrors();
            return false;
        }

        return true;
    }
}
