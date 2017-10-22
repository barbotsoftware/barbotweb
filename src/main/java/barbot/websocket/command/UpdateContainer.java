package barbot.websocket.command;

import java.util.HashMap;

import barbot.database.model.Barbot;
import barbot.database.model.BarbotContainer;
import barbot.database.model.Ingredient;
import barbot.database.model.View;
import barbot.database.service.BarbotContainerService;
import barbot.database.service.BarbotService;
import barbot.database.service.IngredientService;
import barbot.utils.Constants;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 10/22/17.
 */
public class UpdateContainer extends BaseCommand {

    private BarbotService barbotService;

    private IngredientService ingredientService;

    private BarbotContainerService barbotContainerService;

    public UpdateContainer(BarbotService barbotService, IngredientService ingredientService,
                           BarbotContainerService barbotContainerService, FieldValidator validator,
                           HelperMethods hlpr, HashMap msg) {
        super(msg, hlpr, validator);

        setJsonView(View.Summary.class);

        this.barbotService = barbotService;
        this.ingredientService = ingredientService;
        this.barbotContainerService = barbotContainerService;
    }

    @Override
    public Object execute() {
        HashMap result = new HashMap();

        // Get Barbot
        String barbotId = (String) data.get(Constants.KEY_DATA_BARBOT_ID);
        Barbot barbot = this.barbotService.findByUid(barbotId);

        HashMap barbotContainer = (HashMap) data.get(Constants.KEY_DATA_CONTAINER);

        // Get Container Number
        int number = (int) barbotContainer.get(Constants.KEY_DATA_NUMBER);

        // Find Container via service
        BarbotContainer bbc = this.barbotContainerService.findByBarbotAndNumber(barbot, number);

        // Get Ingredient
        String ingredientId = (String) barbotContainer.get(Constants.KEY_DATA_INGREDIENT_ID);
        Ingredient ingredient = this.ingredientService.findByUid(ingredientId);
        bbc.setIngredient(ingredient);

        // Get Volumes
        int currentVolume = (int) barbotContainer.get(Constants.KEY_DATA_CURRENT_VOLUME);
        int maxVolume = (int) barbotContainer.get(Constants.KEY_DATA_MAX_VOLUME);

        // Reduce currentVolume if greater than maxVolume
        if (currentVolume > maxVolume) {
            currentVolume = maxVolume;
        }

        bbc.setCurrentVolume(currentVolume);
        bbc.setMaxVolume(maxVolume);

        this.barbotContainerService.update(bbc);

        result.put(Constants.KEY_DATA_CONTAINER, bbc);

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

        if(!data.containsKey(Constants.KEY_DATA_CONTAINER))  {
            error.put(Constants.KEY_DATA_CONTAINER + ".notFound", hlpr.getMessage(Constants.ERROR_MSG_PREFIX +
                    Constants.KEY_DATA_CONTAINER + ".notFound"));
            return false;
        }

        return true;
    }
}
