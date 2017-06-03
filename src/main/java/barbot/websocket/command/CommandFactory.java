package barbot.websocket.command;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import barbot.database.model.User;
import barbot.database.service.BarbotService;
import barbot.database.service.DrinkOrderService;
import barbot.database.service.IngredientService;
import barbot.database.service.RecipeService;
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
    FieldValidator fieldValidator;

    @Autowired
    HelperMethods hlpr;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Command create(Class clazz, Object... args) {
        if(clazz.equals(CreateCustomRecipe.class)) {
            return new CreateCustomRecipe(recipeService, ingredientService, fieldValidator, hlpr, (HashMap)args[0], (User)args[1]);
        } else if (clazz.equals(GetRecipesForBarbot.class)) {
            return new GetRecipesForBarbot(barbotService, fieldValidator, hlpr, (HashMap) args[0]);
        } else if (clazz.equals(GetRecipeDetails.class)) {
            return new GetRecipeDetails(recipeService, fieldValidator, hlpr, (HashMap) args[0]);
        } else if (clazz.equals(GetIngredientsForBarbot.class)) {
            return new GetIngredientsForBarbot(barbotService, fieldValidator, hlpr, (HashMap) args[0]);
        } else if (clazz.equals(OrderDrink.class)) {
            return new OrderDrink(drinkOrderService, recipeService, barbotService, fieldValidator, hlpr, publisher, (HashMap) args[0], (User) args[1]);
        } else if (clazz.equals(PourDrink.class)) {
            return new PourDrink(drinkOrderService, fieldValidator, hlpr, (HashMap) args[0]);
        } else if (clazz.equals(GetBarbotConfig.class)) {
            return new GetBarbotConfig(barbotService, fieldValidator, hlpr, (HashMap) args[0]);
        } else if (clazz.equals(SetBarbotConfig.class)) {
            return new SetBarbotConfig(barbotService, ingredientService, fieldValidator, hlpr, (HashMap) args[0]);
        }

        return null;
    }
}
