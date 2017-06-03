package barbot.websocket.command;

import java.util.HashMap;
import java.util.Map;

import barbot.utils.Constants;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 4/11/17.
 */
public class BaseCommand implements Command {
    HashMap message;
    HashMap data;
    protected Map error;
    private Class<?> jsonView;
    protected HelperMethods hlpr;
    protected FieldValidator fieldValidator;

    BaseCommand() { }

    public BaseCommand(HashMap msg, HelperMethods hlpr, FieldValidator fieldValidator) {
        message = msg;
        data = (HashMap) message.get(Constants.KEY_DATA);
        error = new HashMap();
        this.hlpr = hlpr;
        this.fieldValidator = fieldValidator;
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
            error.put(Constants.ERROR_MSG_INVALID_COMMAND, hlpr.getMessage(Constants.ERROR_MSG_PREFIX + Constants.ERROR_MSG_INVALID_COMMAND));
            return false;
        }

        return true;
    }
}
