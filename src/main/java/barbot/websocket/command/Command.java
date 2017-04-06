package barbot.websocket.command;

/**
 * Created by alexh on 4/6/2017.
 */
public interface Command {
    Object execute();
    boolean validate();
}
