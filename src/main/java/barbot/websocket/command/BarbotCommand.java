package barbot.websocket.command;

import java.util.HashMap;

import barbot.utils.Constants;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 6/27/17.
 */
public class BarbotCommand extends BaseCommand {

    public BarbotCommand(HashMap msg, HelperMethods hlpr, FieldValidator validator) {
        super(msg, hlpr, validator);
    }

    /**
     * Method to validate requests with only a Barbot ID
     * @return True if the Barbot ID is valid
     */
    protected boolean validateBarbotId() {
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
