package barbot.websocket.command;

import java.util.HashMap;

import barbot.database.service.BarbotService;
import barbot.utils.Constants;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 6/1/17.
 */
public class GetBarbotConfig extends BaseCommand {

    private BarbotService barbotService;

    public GetBarbotConfig(BarbotService barbotService, FieldValidator validator, HelperMethods hlpr, HashMap msg) {
        super(msg, hlpr, validator);

        this.barbotService = barbotService;
    }

    @Override
    public Object execute() {
        return null;
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

        return true;
    }
}
