package barbot.websocket.command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import barbot.database.service.RecipeServiceImpl;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;
import org.springframework.beans.factory.annotation.Autowired;

import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;
import barbot.database.model.User;
import barbot.database.model.View;
import barbot.database.service.DrinkOrderService;
import barbot.database.service.RecipeService;
import barbot.utils.Constants;
import org.springframework.stereotype.Component;

/**
 * Created by Naveen on 4/11/17.
 */
public class CreateCustomDrink extends BaseCommand {

    private RecipeService recipeService;

    private User user;

    private FieldValidator fieldValidator;

    public CreateCustomDrink(RecipeService recipeService, FieldValidator validator, HelperMethods hlpr, HashMap msg, User user) {
        super(msg, hlpr);
        this.user = user;
        this.recipeService = recipeService;
        this.fieldValidator = validator;
        setJsonView(View.Id.class);
    }

    @Override
    public Object execute() {
        Set<Ingredient> ingredients = new HashSet<>(); // data.get("ingredients");

        //Recipe recipe = new Recipe(uid, name, this.user, true, imageUrl, ingredients);

        //recipeService.create(recipe);

        Map response = new HashMap<>();
        //response.put(Constants.KEY_DATA_RECIPE_ID, recipe.getUid());

        return response;
    }

    @Override
    public boolean validate() {
        HashMap fieldsToValidate = new HashMap();
        fieldsToValidate.put("ingredients", "required");

        if(!fieldValidator.validate((HashMap)message.get("data"), fieldsToValidate)) {
            error = fieldValidator.getErrors();
            return false;
        }

        return true;
    }
}
