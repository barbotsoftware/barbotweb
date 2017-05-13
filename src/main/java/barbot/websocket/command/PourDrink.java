package barbot.websocket.command;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import barbot.database.model.DrinkOrder;
import barbot.database.model.Recipe;
import barbot.database.service.DrinkOrderService;
import barbot.database.service.RecipeService;
import barbot.utils.Constants;

/**
 * Created by Naveen on 4/11/17.
 */
public class PourDrink extends BaseCommand {

    @Autowired
    DrinkOrderService drinkOrderService;

    public PourDrink(DrinkOrderService drinkOrderService, HashMap msg) {
        super(msg);
        this.drinkOrderService = drinkOrderService;
    }

    @Override
    public Object execute() {

        String drinkOrderId = (String) data.get(Constants.KEY_DATA_DRINK_ORDER_ID);

        DrinkOrder drinkOrder = this.drinkOrderService.findById(drinkOrderId);

        Recipe recipe = drinkOrder.getRecipe();



        // TODO: send DrinkOrder to Barbot to pour drink

        Map response = new HashMap<>();
        response.put(Constants.KEY_RESULT, Constants.KEY_SUCCESS);

        return response;
    }

    @Override
    public boolean validate() {
        // TODO: Validate DrinkOrderID

        return super.validate();
    }
}
