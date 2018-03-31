package barbot.websocket.command;

import java.util.HashMap;

import barbot.database.model.Barbot;
import barbot.database.model.BarbotGarnish;
import barbot.database.model.View;
import barbot.database.service.BarbotService;
import barbot.database.service.GarnishService;
import barbot.utils.Constants;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 12/31/17.
 */
public class UpdateGarnish extends BaseCommand {

    private GarnishService garnishService;

    private BarbotService barbotService;

    public UpdateGarnish(GarnishService garnishService, BarbotService barbotService, FieldValidator validator,
                         HelperMethods hlpr, HashMap msg) {
        super(msg, hlpr, validator);

        setJsonView(View.Summary.class);

        this.garnishService = garnishService;
        this.barbotService = barbotService;
    }

    @Override
    public Object execute() {
        HashMap result = new HashMap();

        // Get Barbot
        String barbotId = (String) data.get(Constants.KEY_DATA_BARBOT_ID);
        Barbot barbot = this.barbotService.findByUid(barbotId);

        HashMap barbotGarnish = (HashMap) data.get(Constants.KEY_DATA_GARNISH);
        int optionNumber = (int) barbotGarnish.get(Constants.KEY_DATA_OPTION_NUMBER);

        BarbotGarnish garnish = this.garnishService.findByBarbotAndOptionNumber(barbot, optionNumber);

        int quantity = (int)barbotGarnish.get(Constants.KEY_DATA_QUANTITY);
        if (quantity < 0) {
            quantity = 0;
        }
        garnish.setQuantity(quantity);

        this.garnishService.update(garnish);

        result.put(Constants.KEY_DATA_GARNISH, garnish);
        return result;
    }

    @Override
    public boolean validate() {
        if(!super.validate())
            return false;

        HashMap fieldsToValidate = new HashMap();
        fieldsToValidate.put(Constants.KEY_DATA_BARBOT_ID, "required|exists:barbot");

        if(!fieldValidator.validate((HashMap)message.get(Constants.KEY_DATA), fieldsToValidate)) {
            error = fieldValidator.getErrors();
            return false;
        }

        if(!data.containsKey(Constants.KEY_DATA_GARNISH))  {
            error.put(Constants.KEY_DATA_GARNISH + ".notFound", hlpr.getMessage(Constants.ERROR_MSG_PREFIX +
                    Constants.KEY_DATA_GARNISH + ".notFound"));
            return false;
        }

        return true;
    }
}
