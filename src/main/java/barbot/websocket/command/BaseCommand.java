package barbot.websocket.command;

import barbot.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;

/**
 * Created by Naveen on 4/11/17.
 */
public class BaseCommand implements Command {
    protected HashMap message;
    protected ObjectMapper mapper;
    protected Map error;

    @Override
    public Map getError() {
        return error;
    }

    public BaseCommand() { }

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
