package barbot.websocket.command;

import java.util.HashMap;

import barbot.database.model.Barbot;
import barbot.database.model.View;
import barbot.database.service.BarbotService;
import barbot.utils.Constants;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 4/11/17.
 */
public class GetIngredientsForBarbot extends BarbotCommand {

    BarbotService barbotService;

    public GetIngredientsForBarbot(BarbotService barbotService, FieldValidator validator, HelperMethods hlpr, HashMap msg) {
        super(msg, hlpr, validator);
        setJsonView(View.Summary.class);
        this.barbotService = barbotService;
    }

    @Override
    public Object execute() {

        // Get Barbot ID from request
        String barbotId = (String) data.get(Constants.KEY_DATA_BARBOT_ID);

        // Get Barbot from service
        Barbot barbot = barbotService.findByUid(barbotId);

        // Return ingredients from service
        HashMap result = new HashMap();
        result.put("ingredients", barbotService.getIngredients(barbot));
        return result;
    }

    @Override
    public boolean validate() {
        return validateBarbotId();
    }
}
