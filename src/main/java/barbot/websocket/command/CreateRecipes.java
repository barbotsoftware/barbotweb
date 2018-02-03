package barbot.websocket.command;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import barbot.database.model.Barbot;
import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;
import barbot.database.model.RecipeIngredient;
import barbot.database.model.User;
import barbot.database.model.View;
import barbot.database.service.BarbotService;
import barbot.database.service.RecipeService;
import barbot.utils.Constants;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 2/1/18.
 */
public class CreateRecipes extends BaseCommand {
    private RecipeService recipeService;

    private BarbotService barbotService;

    private User user;

    public CreateRecipes(RecipeService recipeService, BarbotService barbotService, FieldValidator validator, HelperMethods hlpr, HashMap msg, User user) {
        super(msg, hlpr, validator);
        this.user = user;
        this.recipeService = recipeService;
        this.barbotService = barbotService;
        setJsonView(View.Summary.class);
    }

    @Override
    public Object execute() {
        String barbotId = (String) data.get(Constants.KEY_DATA_BARBOT_ID);
        Barbot barbot = barbotService.findByUid(barbotId);

        // Map Ingredients for quick retrieval by name
        List<Ingredient> ingredientsInBarbot = barbotService.getIngredients(barbot);
        HashMap ingredientMap = new HashMap<>();

        for (Ingredient i : ingredientsInBarbot) {
            ingredientMap.put(i.getName(), i);
        }

        ArrayList<HashMap> recipes = (ArrayList<HashMap>) data.get(Constants.KEY_DATA_RECIPES);

        // Add all recipes
        for (HashMap recipe : recipes) {
            Recipe newRecipe = new Recipe();
            newRecipe.setCreatedBy(user);
            newRecipe.setCustom(false);
            newRecipe.setName((String)recipe.get("name"));
            newRecipe.setImageUrl((String)recipe.get("img"));

            ArrayList<HashMap> ingredients = (ArrayList<HashMap>)recipe.get("ingredients");
            Set recipeIngredients = new HashSet();

            // Add ingredients
            for (HashMap ingredient : ingredients) {
                String name = (String) ingredient.get("name");
                Ingredient addIngredient = (Ingredient) ingredientMap.get(name);

                if (addIngredient != null) {
                    double amount;
                    if (ingredient.get("amount").getClass().equals(Integer.class)) {
                        amount = (double)((Integer)ingredient.get("amount")).intValue();
                    } else {
                        amount = (double)ingredient.get("amount");
                    }
                    RecipeIngredient recipeIngredient = new RecipeIngredient(newRecipe,
                                                            addIngredient,
                                                            BigDecimal.valueOf(amount));
                    recipeIngredients.add(recipeIngredient);
                }
            }

            newRecipe.setRecipeIngredients(recipeIngredients);
            recipeService.create(newRecipe);
        }

        HashMap result = new HashMap();
        return result;
    }

    @Override
    public boolean validate() {
        if(!super.validate())
            return false;

        HashMap fieldsToValidate = new HashMap();
        fieldsToValidate.put(Constants.KEY_DATA_BARBOT_ID, "required|exists:barbot");

        if(!fieldValidator.validate((HashMap)message.get(Constants.KEY_DATA), fieldsToValidate)) {
            error = fieldValidator.getErrors();
            return false;
        }

        if(!data.containsKey(Constants.KEY_DATA_RECIPES))  {
            error.put(Constants.KEY_DATA_RECIPES + ".notFound", hlpr.getMessage(Constants.ERROR_MSG_PREFIX +
                    Constants.KEY_DATA_RECIPES + ".notFound"));
            return false;
        }

        return true;
    }
}
