package barbot.websocket.command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import barbot.database.model.Barbot;
import barbot.database.model.Recipe;
import barbot.database.model.View;
import barbot.database.service.BarbotService;
import barbot.utils.Constants;

/**
 * Created by alexh on 4/6/2017.
 */
public class GetRecipesForBarbot extends BaseCommand {

    @Autowired
    private BarbotService barbotService;

    public GetRecipesForBarbot(HashMap msg) {
        super(msg);

        // Set JsonView for serialization
        setJsonView(View.Summary.class);
    }

    @Override
    public Object execute() {
        Map data = (HashMap) message.get(Constants.KEY_DATA);

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
