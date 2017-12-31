package barbot.websocket.command;

import java.util.HashMap;

import barbot.database.model.Barbot;
import barbot.database.model.View;
import barbot.database.service.BarbotService;
import barbot.utils.Constants;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 12/30/17.
 */
public class GetGarnishesForBarbot extends BarbotCommand {

    private BarbotService barbotService;

    public GetGarnishesForBarbot(BarbotService barbotService, FieldValidator validator, HelperMethods hlpr, HashMap msg) {
        super(msg, hlpr, validator);
        setJsonView(View.Summary.class);
        this.barbotService = barbotService;
    }

    @Override
    public Object execute() {
        String barbotId = (String) data.get(Constants.KEY_DATA_BARBOT_ID);
        Barbot barbot = barbotService.findByUid(barbotId);

        // Return garnishes from barbot service
        HashMap result = new HashMap();
        result.put(Constants.KEY_DATA_GARNISHES, this.barbotService.getGarnishes(barbot));
        return result;
    }

    @Override
    public boolean validate() {
        return validateBarbotId();
    }
}
