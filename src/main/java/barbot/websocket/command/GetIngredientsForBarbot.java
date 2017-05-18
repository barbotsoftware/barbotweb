package barbot.websocket.command;

import java.util.HashMap;
import java.util.Map;

import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;
import org.springframework.beans.factory.annotation.Autowired;

import barbot.database.model.Barbot;
import barbot.database.model.View;
import barbot.database.service.BarbotService;
import barbot.database.service.IngredientService;
import barbot.utils.Constants;

/**
 * Created by Naveen on 4/11/17.
 */
public class GetIngredientsForBarbot extends BaseCommand {

    BarbotService barbotService;

    private FieldValidator fieldValidator;

    public GetIngredientsForBarbot(BarbotService barbotService, FieldValidator validator, HelperMethods hlpr, HashMap msg) {
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
        Barbot barbot = barbotService.findByUid(barbotId);

        // Return ingredients from service
        return this.barbotService.getIngredients(barbot);
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
