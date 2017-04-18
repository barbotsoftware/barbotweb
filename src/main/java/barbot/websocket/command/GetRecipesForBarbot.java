package barbot.websocket.command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import barbot.database.model.Barbot;
import barbot.database.model.Recipe;
import barbot.database.service.BarbotService;

/**
 * Created by alexh on 4/6/2017.
 */
public class GetRecipesForBarbot extends BaseCommand {

    @Autowired
    private BarbotService barbotService;

    public GetRecipesForBarbot(HashMap msg) {
        super(msg);
    }

    @Override
    public Object execute() {
        String barbotId = (String) data.get("barbot_id");

        Barbot barbot = barbotService.findById(barbotId);

        Set<Recipe> recipes = new HashSet<Recipe>();

        return recipes;
    }

    @Override
    public boolean validate() {
        // TODO: Validate BarbotID

        return super.validate();
    }
}
