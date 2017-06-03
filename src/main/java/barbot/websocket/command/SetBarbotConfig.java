package barbot.websocket.command;

import java.util.HashMap;

import barbot.database.service.BarbotService;
import barbot.database.service.IngredientService;
import barbot.utils.Constants;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 6/1/17.
 */
public class SetBarbotConfig extends BaseCommand {

    private BarbotService barbotService;

    private IngredientService ingredientService;

    public SetBarbotConfig(BarbotService barbotService, IngredientService ingredientService, FieldValidator validator,
                           HelperMethods hlpr, HashMap msg) {
        super(msg, hlpr, validator);

        this.barbotService = barbotService;
        this.ingredientService = ingredientService;
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

        if(!fieldValidator.validate((HashMap)message.get(Constants.KEY_DATA), fieldsToValidate)) {
            error = fieldValidator.getErrors();
            return false;
        }

        return true;
    }
}
