package barbot.websocket.command;

import java.util.HashMap;
import java.util.Map;

import barbot.utils.Constants;

/**
 * Created by Naveen on 4/11/17.
 */
public class PourDrink extends BaseCommand {

    public PourDrink(HashMap msg) {
        super(msg);
    }

    @Override
    public Object execute() {
        Map data = (HashMap) message.get(Constants.KEY_DATA);

        return "response";
    }

    @Override
    public boolean validate() {
        // TODO: Validate DrinkOrderID

        return super.validate();
    }
}
