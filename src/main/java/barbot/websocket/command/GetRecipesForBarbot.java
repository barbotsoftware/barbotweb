package barbot.websocket.command;

import barbot.utils.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexh on 4/6/2017.
 */
public class GetRecipesForBarbot implements Command {

    private HashMap message;

    private Map error;

    @Override
    public Map getError() {
        return error;
    }

    public GetRecipesForBarbot(HashMap msg) {
        message = msg;
    }

    public Object execute() {
        return "response";
    }

    public boolean validate() {
        if(!message.containsKey(Constants.KEY_DATA)) {
            error = new HashMap();
            error.put(Constants.ERROR_INVALID_COMMAND, Constants.ERROR_MSG_INVALID_COMMAND);
            return false;
        }

        return true;
    }
}
