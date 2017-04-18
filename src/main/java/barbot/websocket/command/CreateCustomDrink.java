package barbot.websocket.command;

import java.util.HashMap;

import barbot.database.model.View;

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
        return "response";
    }

    @Override
    public boolean validate() {
        // TODO: Validate Recipe

        return super.validate();
    }
}
