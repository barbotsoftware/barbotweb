package barbot.websocket.command;

import java.math.BigDecimal;
import java.util.*;

import barbot.database.model.*;
import barbot.database.service.IngredientService;
import barbot.database.service.RecipeServiceImpl;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;
import org.springframework.beans.factory.annotation.Autowired;

import barbot.database.service.DrinkOrderService;
import barbot.database.service.RecipeService;
import barbot.utils.Constants;
import org.springframework.stereotype.Component;

/**
 * Created by Naveen on 4/11/17.
 */
public class CreateCustomDrink extends BaseCommand {

    private RecipeService recipeService;

    private IngredientService ingredientService;

    private User user;

    private FieldValidator fieldValidator;

    public CreateCustomDrink(RecipeService recipeService, IngredientService ingredientService, FieldValidator validator, HelperMethods hlpr, HashMap msg, User user) {
        super(msg, hlpr);
        this.user = user;
        this.recipeService = recipeService;
        this.fieldValidator = validator;
        this.ingredientService = ingredientService;
        setJsonView(View.Summary.class);
    }

    @Override
    public Object execute() {
        HashMap data = (HashMap)message.get("data");

        Recipe recipe = new Recipe();
        recipe.setCustom(true);
        recipe.setName((String)data.get("name"));
        recipe.setCreatedBy(user);
        recipe.setImageUrl(Constants.CUSTOM_IMG_URL);

        Set recipeIngredients = new HashSet();
        ArrayList<HashMap> ingredients = (ArrayList<HashMap>)data.get("ingredients");
        for(HashMap ingredient : ingredients) {
            String id = (String) ingredient.get("ingredient_id");
            Ingredient addIngredient = ingredientService.findById(id);
            if(addIngredient != null) {
                RecipeIngredient recipeIngredient = new RecipeIngredient();
                recipeIngredient.setAmount(BigDecimal.valueOf(Double.parseDouble((String)ingredient.get("amount"))));
                recipeIngredient.setIngredient(addIngredient);
                recipeIngredient.setRecipe(recipe);
                recipeIngredients.add(recipeIngredient);
            }
        }

        recipe.setRecipeIngredients(recipeIngredients);
        recipeService.create(recipe);

        return recipe;
    }

    @Override
    public boolean validate() {
        HashMap fieldsToValidate = new HashMap();
        fieldsToValidate.put("name", "required");

        if(!fieldValidator.validate((HashMap)message.get("data"), fieldsToValidate)) {
            error = fieldValidator.getErrors();
            return false;
        }

        HashMap data = ((HashMap) message.get("data"));
        if(!data.containsKey("ingredients") || !data.get("ingredients").getClass().equals(ArrayList.class))  {
            error.put("ingredients.notFound", hlpr.getMessage(Constants.ERROR_MSG_PREFIX + "ingredients.notFound"));
            return false;
        }

        return true;
    }
}
