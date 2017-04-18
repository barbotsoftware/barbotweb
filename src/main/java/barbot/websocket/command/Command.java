package barbot.websocket.command;

import java.util.Map;

/**
 * Created by alexh on 4/6/2017.
 */
public interface Command {
    Object execute();
    boolean validate();
    Map getError();
    Map getData();
    Class<?> getJsonView();
    void setJsonView(Class<?> jsonView);
}
