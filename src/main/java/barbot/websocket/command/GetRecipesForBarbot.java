package barbot.websocket.command;

import java.util.HashMap;

/**
 * Created by alexh on 4/6/2017.
 */
public class GetRecipesForBarbot extends BaseCommand {

    public GetRecipesForBarbot(HashMap msg) {
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
