package barbot.websocket.command;

import java.util.HashMap;

/**
 * Created by Naveen on 4/11/17.
 */
public class GetIngredientsForBarbot extends BaseCommand {

    public GetIngredientsForBarbot(HashMap msg) {
        message = msg;
    }

    @Override
    public Object execute() {
        return "response";
    }

    @Override
    public boolean validate() {
        // TODO: Validate BarbotID

        return super.validate();
    }
}
