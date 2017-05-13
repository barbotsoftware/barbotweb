package barbot.websocket.command;

import barbot.database.model.Barbot;
import barbot.database.model.User;
import barbot.database.service.BarbotService;
import barbot.database.service.DrinkOrderService;
import barbot.database.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

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

    public Command create(Class clazz, Object... args) {
        if(clazz.equals(CreateCustomDrink.class)) {
            return new CreateCustomDrink(recipeService, (HashMap)args[0], (User)args[1]);
        } else if (clazz.equals(GetRecipesForBarbot.class)) {
            return new GetRecipesForBarbot(barbotService, (HashMap) args[0]);
        } else if (clazz.equals(GetRecipeDetails.class)) {
            return new GetRecipeDetails(recipeService, (HashMap) args[0]);
        } else if (clazz.equals(GetIngredientsForBarbot.class)) {
            return new GetIngredientsForBarbot(barbotService, (HashMap) args[0]);
        } else if (clazz.equals(OrderDrink.class)) {
            return new OrderDrink(drinkOrderService, recipeService, barbotService, (HashMap) args[0], (User) args[1]);
        } else if (clazz.equals(PourDrink.class)) {
            return new PourDrink(drinkOrderService, (HashMap) args[0]);
        }

        return null;
    }
}
