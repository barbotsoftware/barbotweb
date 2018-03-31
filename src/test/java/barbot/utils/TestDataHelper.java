package barbot.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import barbot.database.model.Barbot;
import barbot.database.model.BarbotContainer;
import barbot.database.model.BarbotGarnish;
import barbot.database.model.Category;
import barbot.database.model.DrinkOrder;
import barbot.database.model.Garnish;
import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;
import barbot.database.model.User;

/**
 * Created by Naveen on 5/27/17.
 */
public class TestDataHelper {

    public TestDataHelper() {
    }

    public List<Barbot> createBarbotList(int listSize) {
        List<Barbot> barbots = new ArrayList<>();

        for (int i = 0; i < listSize; i++) {
            Barbot barbot = new Barbot();
            barbot.setId(i);
            barbot.setUid("barbot_xxxxx" + i);
            barbot.setName("barbot" + i);
            barbot.setPassword("password");
            barbots.add(barbot);
        }

        return barbots;
    }

    public List<BarbotContainer> createBarbotContainerList(int listSize,
                                                           List<Ingredient> ingredients,
                                                           List<Barbot> barbots) {
        List<BarbotContainer> barbotContainers = new ArrayList<>();

        for (int i = 0; i < listSize; i++) {
            BarbotContainer bc = new BarbotContainer();
            bc.setId(i);
            bc.setNumber(i);
            bc.setIngredient(ingredients.get(i));
            bc.setBarbot(barbots.get(0));
            barbotContainers.add(bc);
        }

        return barbotContainers;
    }

    public List<BarbotGarnish> createBarbotGarnishList(int listSize,
                                                       List<Barbot> barbots) {
        List<BarbotGarnish> barbotGarnishes = new ArrayList<>();

        for (int i = 0; i < listSize; i++) {
            BarbotGarnish garnish = new BarbotGarnish(barbots.get(0), new Garnish(), i, i);
            garnish.setId(i);
            barbotGarnishes.add(garnish);
        }

        return barbotGarnishes;
    }

    public List<Category> createCategoryList(int listSize) {
        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < listSize; i++) {
            Category category = new Category();
            category.setId(i);
            category.setUid("category_xxxxx" + i);

            if (i % 2 == 0 && i > 0) {
                category.setParentCategory(categories.get(i - 1));
            }

            categories.add(category);
        }

        return categories;
    }

    public List<DrinkOrder> createDrinkOrderList(int listSize) {
        List<DrinkOrder> drinkOrders = new ArrayList<>();

        for (int i = 0; i < listSize; i++) {
            DrinkOrder drinkOrder = new DrinkOrder();
            drinkOrder.setId(i);
            drinkOrder.setUid("drinkOrder_xxxxx" + i);
            drinkOrders.add(drinkOrder);
        }

        return drinkOrders;
    }

    public List<Garnish> createGarnishList(int listSize) {
        List<Garnish> garnishes = new ArrayList<>();

        for (int i = 0; i < listSize; i++) {
            Garnish garnish = new Garnish("garnish_" + i, "Garnish " + i);
            garnish.setId(i);
            garnishes.add(garnish);
        }

        return garnishes;
    }

    public List<Ingredient> createIngredientList(int listSize) {
        List<Ingredient> ingredients = new ArrayList<>();

        for (int i = 0; i < listSize; i++) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(i);
            ingredient.setUid("ingredient_xxxxx" + i);
            ingredients.add(ingredient);
        }

        return ingredients;
    }

    public List<Recipe> createRecipeList(int listSize,
                                         List<Ingredient> ingredients) {
        List<Recipe> recipes = new ArrayList<>();

        for (int i = 0; i < listSize; i++) {
            Recipe recipe = new Recipe();
            recipe.setId(i);
            recipe.setUid("recipe_xxxxx" + i);
            if (ingredients != null) {
                recipe.setIngredients(new HashSet(ingredients));
            }
            recipes.add(recipe);
        }

        return recipes;
    }

    public List<Category> createRootCategoryList(List<Category> categoryList) {
        List<Category> rootCategoryList = new ArrayList<>();

        for (Category c : categoryList) {
            if (c.getParentCategory() != null) {
                rootCategoryList.add(c);
            }
        }

        return rootCategoryList;
    }

    public List<User> createUserList(int listSize) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < listSize; i++) {
            User user = new User();
            user.setId(i);
            user.setUid("user_xxxxx" + i);
            user.setName("User" + i);
            user.setEmail(user.getName() + "@barbot.io");
            user.setPassword("password");
            users.add(user);
        }

        return users;
    }
}
