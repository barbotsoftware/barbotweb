package barbot.websocket.command;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import barbot.database.model.Barbot;
import barbot.database.service.BarbotService;

/**
 * Created by alexh on 4/6/2017.
 */
public class GetRecipesForBarbot extends BaseCommand {

    @Autowired
    private BarbotService barbotService;

    public GetRecipesForBarbot(HashMap msg) {
        message = msg;
    }

    @Override
    public Object execute() {
        String barbotId = (String) message.get("barbot_id");

        Barbot barbot = barbotService.findById(barbotId);

        return "response";
    }

    @Override
    public boolean validate() {
        // TODO: Validate BarbotID

        return super.validate();
    }
}