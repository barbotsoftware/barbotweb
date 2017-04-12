package barbot.websocket.command;

import barbot.utils.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Naveen on 4/11/17.
 */
public class BaseCommand implements Command {
    protected HashMap message;

    protected Map error;

    @Override
    public Map getError() {
        return error;
    }

    public BaseCommand() { }

    public BaseCommand(HashMap msg) {
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
