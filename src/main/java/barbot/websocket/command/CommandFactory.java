package barbot.websocket.command;

import java.util.HashMap;

import barbot.database.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import barbot.database.model.User;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

/**
 * Created by Alex on 5/11/2017.
 */
@Component
public class CommandFactory {

    @Autowired
    RecipeService recipeService;

    @Autowired
    BarbotService barbotService;

    @Autowired
    DrinkOrderService drinkOrderService;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    BarbotContainerService barbotContainerService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    GarnishService garnishService;

    @Autowired
    FieldValidator fieldValidator;

    @Autowired
    HelperMethods hlpr;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Command create(Class clazz, Object... args) {
        if (clazz.equals(CreateCustomRecipe.class)) {
            return new CreateCustomRecipe(recipeService, ingredientService, fieldValidator, hlpr, (HashMap)args[0], (User)args[1]);
        } else if (clazz.equals(GetRecipesForBarbot.class)) {
            return new GetRecipesForBarbot(barbotService, categoryService, fieldValidator, hlpr, (HashMap) args[0]);
        } else if (clazz.equals(GetRecipeDetails.class)) {
            return new GetRecipeDetails(recipeService, fieldValidator, hlpr, (HashMap) args[0]);
        } else if (clazz.equals(GetIngredientsForBarbot.class)) {
            return new GetIngredientsForBarbot(barbotService, fieldValidator, hlpr, (HashMap) args[0]);
        } else if (clazz.equals(OrderDrink.class)) {
            return new OrderDrink(drinkOrderService, recipeService, barbotService, fieldValidator, hlpr, publisher, (HashMap) args[0], (User) args[1]);
        } else if (clazz.equals(PourDrink.class)) {
            return new PourDrink(drinkOrderService, fieldValidator, hlpr, (HashMap) args[0]);
        } else if (clazz.equals(GetContainersForBarbot.class)) {
            return new GetContainersForBarbot(barbotService, fieldValidator, hlpr, (HashMap) args[0]);
        } else if (clazz.equals(SetContainersForBarbot.class)) {
            return new SetContainersForBarbot(barbotService, ingredientService, barbotContainerService, fieldValidator, hlpr, (HashMap) args[0]);
        } else if (clazz.equals(GetCategories.class)) {
            return new GetCategories(categoryService, fieldValidator, hlpr, (HashMap)args[0]);
        } else if (clazz.equals(GetCategory.class)) {
            return new GetCategory(categoryService, fieldValidator, hlpr, (HashMap)args[0]);
        } else if (clazz.equals(BaseCommand.class)) {
            return new BaseCommand((HashMap) args[0], hlpr, fieldValidator);
        } else if (clazz.equals(UpdateContainer.class)) {
            return new UpdateContainer(barbotService, ingredientService, barbotContainerService, fieldValidator, hlpr, (HashMap) args[0]);
        } else if (clazz.equals(UpdateGarnish.class)) {
            return new UpdateGarnish(garnishService, barbotService, fieldValidator, hlpr, (HashMap)args[0]);
        } else if (clazz.equals(GetGarnishesForBarbot.class)) {
            return new GetGarnishesForBarbot(barbotService, fieldValidator, hlpr, (HashMap) args[0]);
        } else if (clazz.equals(CreateRecipes.class)) {
            return new CreateRecipes(recipeService, barbotService, fieldValidator, hlpr, (HashMap)args[0], (User)args[1]);
        } else if (clazz.equals(CreateCategories.class)) {
            return new CreateCategories(categoryService, recipeService, fieldValidator, hlpr, (HashMap)args[0]);
        }

        return null;
    }
}
