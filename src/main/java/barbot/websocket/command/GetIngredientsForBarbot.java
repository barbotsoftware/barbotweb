package barbot.websocket.command;

import java.util.HashMap;
import java.util.Map;

import barbot.database.model.View;
import barbot.utils.Constants;

/**
 * Created by Naveen on 4/11/17.
 */
public class GetIngredientsForBarbot extends BaseCommand {

    public GetIngredientsForBarbot(HashMap msg) {
        super(msg);
        setJsonView(View.Summary.class);
    }

    @Override
    public Object execute() {
        Map data = (HashMap) message.get(Constants.KEY_DATA);

        String barbotId = (String) data.get("barbot_id");

        return "response";
    }

    @Override
    public boolean validate() {
        // TODO: Validate BarbotID

        return super.validate();
    }
}
