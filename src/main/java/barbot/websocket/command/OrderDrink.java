package barbot.websocket.command;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;

import barbot.database.model.Barbot;
import barbot.database.model.DrinkOrder;
import barbot.database.model.Recipe;
import barbot.database.model.User;
import barbot.database.model.View;
import barbot.database.service.BarbotService;
import barbot.database.service.DrinkOrderService;
import barbot.database.service.RecipeService;
import barbot.database.service.UserService;
import barbot.utils.Constants;

/**
 * Created by Naveen on 4/11/17.
 */
public class OrderDrink extends BaseCommand {

    @Autowired
    BarbotService barbotService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    UserService userService;

    @Autowired
    DrinkOrderService drinkOrderService;

    private User user;

    public OrderDrink(HashMap msg, User user) {
        super(msg);

        this.user = user;

        // Return Drink Order Response
        setJsonView(View.Response.class);
    }

    @Override
    public Object execute() {

        // TODO: generate drink order uid
        String uid = "";

        // Get Barbot
        String barbotId = (String) data.get(Constants.KEY_DATA_BARBOT_ID);
        Barbot barbot = this.barbotService.findById(barbotId);

        // Get Recipe
        String recipeId = (String) data.get(Constants.KEY_DATA_RECIPE_ID);
        Recipe recipe = this.recipeService.findById(recipeId);

        // Get Ice and Garnish
        Integer ice = (int) data.get(Constants.KEY_DATA_ICE);
        Integer garnish = (int) data.get(Constants.KEY_DATA_GARNISH);

        DrinkOrder drinkOrder = new DrinkOrder(uid, this.user, recipe, barbot, ice, garnish);

        return this.drinkOrderService.create(drinkOrder);
    }

    @Override
    public boolean validate() {
        // TODO: Validate BarbotID, RecipeID, Ice, Garnish

        return super.validate();
    }
}
