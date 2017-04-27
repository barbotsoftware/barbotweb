package barbot.websocket.command;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import barbot.database.model.Barbot;
import barbot.database.model.DrinkOrder;
import barbot.database.model.Recipe;
import barbot.database.model.User;
import barbot.database.model.View;
import barbot.database.service.BarbotService;
import barbot.database.service.DrinkOrderService;
import barbot.database.service.RecipeService;
import barbot.utils.Constants;

/**
 * Created by Naveen on 4/11/17.
 */
public class OrderDrink extends BaseCommand {

    @Autowired
    RecipeService recipeService;

    @Autowired
    BarbotService barbotService;

    @Autowired
    DrinkOrderService drinkOrderService;

    public OrderDrink(HashMap msg) {
        super(msg);

        // Return Drink Order Response
        setJsonView(View.Response.class);
    }

    @Override
    public Object execute() {

        Map data = (HashMap) message.get(Constants.KEY_DATA);

        // TODO: generate uid
        String uid = "";

        // Get Barbot
        String barbotId = (String) data.get("barbot_id");
        Barbot barbot = barbotService.findById(barbotId);

        // Get Recipe
        String recipeId = (String) data.get("recipe_id");
        Recipe recipe = recipeService.findById(recipeId);

        // Get User
        User user = new User();

        // get Ice and Garnish
        Integer ice = Integer.valueOf((int) data.get("ice"));
        Integer garnish = Integer.valueOf((int) data.get("garnish"));

        DrinkOrder drinkOrder = new DrinkOrder(uid, user, recipe, barbot, ice, garnish);

        return drinkOrderService.create(drinkOrder);
    }

    @Override
    public boolean validate() {
        // TODO: Validate BarbotID, RecipeID, Ice, Garnish

        return super.validate();
    }
}
