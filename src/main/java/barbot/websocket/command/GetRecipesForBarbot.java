package barbot.websocket.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import barbot.database.model.Barbot;
import barbot.database.model.Category;
import barbot.database.model.View;
import barbot.database.service.BarbotService;
import barbot.database.service.CategoryService;
import barbot.utils.Constants;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

/**
 * Created by alexh on 4/6/2017.
 */
public class GetRecipesForBarbot extends BarbotCommand {

    private BarbotService barbotService;

    private CategoryService categoryService;

    public GetRecipesForBarbot(BarbotService barbotService, CategoryService categoryService, FieldValidator validator, HelperMethods hlpr, HashMap msg) {
        super(msg, hlpr, validator);
        setJsonView(View.Summary.class);
        this.barbotService = barbotService;
        this.categoryService = categoryService;
    }

    @Override
    public Object execute() {

        // Get Barbot ID from request
        String barbotId = (String) data.get(Constants.KEY_DATA_BARBOT_ID);

        // Get category ID and ingredient ID's to filter by
        String categoryId = (String) data.get(Constants.KEY_DATA_CATEGORY_ID);
        List<String> ingredientIds = new ArrayList<>();
        ArrayList<HashMap> ingredients = (ArrayList<HashMap>)data.get("ingredients");
        for(HashMap ingredient : ingredients) {
            ingredientIds.add(ingredient.get(Constants.KEY_DATA_INGREDIENT_ID).toString());
        }

        Barbot barbot = this.barbotService.findByUid(barbotId);
        Category category = categoryService.findByUid(categoryId);

        // Return Recipes for Barbot from service
        HashMap result = new HashMap();
        result.put("recipes", barbotService.getRecipes(barbot, category, ingredientIds));
        return result;
    }

    @Override
    public boolean validate() {
        return validateBarbotId();
    }
}
