package barbot.websocket.command;

import java.util.HashMap;

/**
 * Created by Naveen on 4/11/17.
 */
public class OrderDrink extends BaseCommand {

    public OrderDrink(HashMap msg) {
        message = msg;
    }

    @Override
    public Object execute() {
        return "response";
    }

    @Override
    public boolean validate() {
        // TODO: Validate BarbotID, RecipeID, Ice, Garnish

        return super.validate();
    }
}
