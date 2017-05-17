package barbot.websocket.command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;
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

    private BarbotService barbotService;

    private FieldValidator fieldValidator;

    public GetRecipesForBarbot(BarbotService barbotService, FieldValidator validator, HelperMethods hlpr, HashMap msg) {
        super(msg, hlpr);
        setJsonView(View.Summary.class);
        this.barbotService = barbotService;
        this.fieldValidator = validator;
    }

    @Override
    public Object execute() {

        // Get Barbot ID from request
        String barbotId = (String) data.get(Constants.KEY_DATA_BARBOT_ID);

        // Get Barbot from service
        Barbot barbot = this.barbotService.findByUid(barbotId);

        // Return Recipes for Barbot from service
        return this.barbotService.getRecipes(barbot);
    }

    @Override
    public boolean validate() {
        // TODO: Validate BarbotID

        return super.validate();
    }
}
