package barbot.websocket.command;

import java.util.HashMap;

/**
 * Created by Naveen on 4/11/17.
 */
public class CreateCustomDrink extends BaseCommand {

    public CreateCustomDrink(HashMap msg) {
        message = msg;
    }

    @Override
    public Object execute() {
        return "response";
    }

    @Override
    public boolean validate() {
        // TODO: Validate Recipe

        return super.validate();
    }
}
