package barbot.websocket.command;

import java.util.HashMap;
import java.util.Map;

import barbot.utils.Constants;

/**
 * Created by Naveen on 4/11/17.
 */
public class BaseCommand implements Command {
    HashMap message;
    HashMap data;
    private Map error;
    private Class<?> jsonView;

    BaseCommand() { }

    public BaseCommand(HashMap msg) {
        message = msg;
        data = (HashMap) message.get(Constants.KEY_DATA);
    }

    @Override
    public Map getError() {
        return error;
    }

    // Use JsonView to format Json Responses

    @Override
    public Class<?> getJsonView() {
        return this.jsonView;
    }

    @Override
    public void setJsonView(Class<?> jsonView) {
        this.jsonView = jsonView;
    }

    public Object execute() {
        return Constants.KEY_RESPONSE;
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
