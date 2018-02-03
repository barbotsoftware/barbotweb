package barbot.websocket.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import barbot.database.model.Category;
import barbot.database.model.Recipe;
import barbot.database.model.RecipeCategory;
import barbot.database.model.View;
import barbot.database.service.CategoryService;
import barbot.database.service.RecipeService;
import barbot.utils.Constants;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 2/2/18.
 */
public class CreateCategories extends BaseCommand {

    private CategoryService categoryService;

    private RecipeService recipeService;

    public CreateCategories(CategoryService categoryService, RecipeService recipeService, FieldValidator validator, HelperMethods hlpr, HashMap msg) {
        super(msg, hlpr, validator);
        this.categoryService = categoryService;
        this.recipeService = recipeService;
        setJsonView(View.Summary.class);
    }

    @Override
    public Object execute() {
        ArrayList<HashMap> categories = (ArrayList<HashMap>) data.get(Constants.KEY_DATA_CATEGORIES);

        for (HashMap category : categories) {
            Category newCategory = new Category();
            newCategory.setName((String)category.get("name"));
            newCategory.setImageUrl((String)category.get("img"));

            Category parentCategory = categoryService.findByUid((String)category.get("parent"));

            if (parentCategory != null) {
                newCategory.setParentCategory(parentCategory);
            }

            ArrayList<String> recipes = (ArrayList<String>)category.get("recipes");
            Set recipeCategories = new HashSet();

            for (String recipeName : recipes) {
                Recipe recipe = recipeService.findByName(recipeName);

                if (recipe != null) {
                    RecipeCategory recipeCategory = new RecipeCategory();
                    recipeCategory.setCategory(newCategory);
                    recipeCategory.setRecipe(recipe);

                    recipeCategories.add(recipeCategory);
                }
            }

            newCategory.setRecipeCategories(recipeCategories);
            categoryService.create(newCategory);
        }

        HashMap result = new HashMap();
        return result;
    }

    @Override
    public boolean validate() {
        if(!super.validate())
            return false;

        if(!data.containsKey(Constants.KEY_DATA_CATEGORIES))  {
            error.put(Constants.KEY_DATA_CATEGORIES + ".notFound", hlpr.getMessage(Constants.ERROR_MSG_PREFIX +
                    Constants.KEY_DATA_CATEGORIES + ".notFound"));
            return false;
        }

        return true;
    }
}
