package barbot.websocket.command;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import barbot.utils.Constants;

/**
 * Created by Naveen on 4/11/17.
 */
public class BaseCommand implements Command {
    HashMap message;
    ObjectMapper mapper;
    private Map error;

    @Override
    public Map getError() {
        return error;
    }

    BaseCommand() { }

    public BaseCommand(HashMap msg) {
        message = msg;
        mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
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
