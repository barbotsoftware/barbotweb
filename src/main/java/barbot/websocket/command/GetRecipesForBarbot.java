package barbot.websocket.command;

import barbot.utils.Constants;

import java.util.HashMap;

/**
 * Created by alexh on 4/6/2017.
 */
public class GetRecipesForBarbot implements Command {

    private HashMap message;

    public GetRecipesForBarbot(HashMap msg) {
        message = msg;
    }

    public Object execute() {
        return "response";
    }

    public boolean validate() {
        if(!message.containsKey(Constants.KEY_DATA)) {
            return false;
        }

        return true;
    }
}
