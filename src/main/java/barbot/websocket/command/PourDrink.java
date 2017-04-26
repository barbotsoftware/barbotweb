package barbot.websocket.command;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import barbot.database.model.DrinkOrder;
import barbot.database.service.DrinkOrderService;
import barbot.utils.Constants;

/**
 * Created by Naveen on 4/11/17.
 */
public class PourDrink extends BaseCommand {

    @Autowired
    DrinkOrderService drinkOrderService;

    public PourDrink(HashMap msg) {
        super(msg);
    }

    @Override
    public Object execute() {
        Map data = (HashMap) message.get(Constants.KEY_DATA);

        String drinkOrderId = (String) data.get("drink_order_id");
        DrinkOrder drinkOrder = drinkOrderService.findById(drinkOrderId);

        // TODO: send DrinkOrder to Barbot to pour drink

        return "success";
    }

    @Override
    public boolean validate() {
        // TODO: Validate DrinkOrderID

        return super.validate();
    }
}
