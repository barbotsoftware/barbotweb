package barbot.websocket.command;

import java.util.HashMap;
import java.util.Map;

import barbot.database.model.View;
import barbot.utils.Constants;

/**
 * Created by Naveen on 4/11/17.
 */
public class CreateCustomDrink extends BaseCommand {

    public CreateCustomDrink(HashMap msg) {
        super(msg);
        setJsonView(View.Id.class);
    }

    @Override
    public Object execute() {
        Map data = (HashMap) message.get(Constants.KEY_DATA);

        return "response";
    }

    @Override
    public boolean validate() {
        // TODO: Validate Recipe

        return super.validate();
    }
}
