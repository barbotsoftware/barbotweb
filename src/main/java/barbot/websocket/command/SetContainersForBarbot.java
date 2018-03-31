package barbot.websocket.command;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import barbot.database.model.Barbot;
import barbot.database.model.BarbotContainer;
import barbot.database.model.Ingredient;
import barbot.database.service.BarbotContainerService;
import barbot.database.service.BarbotService;
import barbot.database.service.IngredientService;
import barbot.utils.Constants;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 6/1/17.
 */
public class SetContainersForBarbot extends BaseCommand {

    private BarbotService barbotService;

    private IngredientService ingredientService;

    private BarbotContainerService barbotContainerService;

    public SetContainersForBarbot(BarbotService barbotService, IngredientService ingredientService,
                                  BarbotContainerService barbotContainerService, FieldValidator validator,
                                  HelperMethods hlpr, HashMap msg) {
        super(msg, hlpr, validator);

        this.barbotService = barbotService;
        this.ingredientService = ingredientService;
        this.barbotContainerService = barbotContainerService;
    }

    @Override
    public Object execute() {
        // Get Barbot
        String barbotId = (String) data.get(Constants.KEY_DATA_BARBOT_ID);
        Barbot barbot = this.barbotService.findByUid(barbotId);

        ArrayList<HashMap> barbotContainers = (ArrayList<HashMap>) data.get(Constants.KEY_DATA_CONTAINERS);

        for (HashMap barbotContainer : barbotContainers) {

            // Get Container Number
            int number = (int) barbotContainer.get(Constants.KEY_DATA_NUMBER);

            // Find Container via service
            BarbotContainer bbc = this.barbotContainerService.findByBarbotAndNumber(barbot, number);

            // Get Ingredient
            String ingredientId = (String) barbotContainer.get(Constants.KEY_DATA_INGREDIENT_ID);
            Ingredient ingredient = this.ingredientService.findByUid(ingredientId);
            bbc.setIngredient(ingredient);

            // Get Volumes
            BigDecimal currentVolume = new BigDecimal((double)barbotContainer.get(Constants.KEY_DATA_CURRENT_VOLUME));
            BigDecimal maxVolume = new BigDecimal((double)barbotContainer.get(Constants.KEY_DATA_MAX_VOLUME));

            // Reduce currentVolume if greater than maxVolume
            if (currentVolume.compareTo(maxVolume) == 1) {
                currentVolume = maxVolume;
            }

            bbc.setCurrentVolume(currentVolume);
            bbc.setMaxVolume(maxVolume);

            this.barbotContainerService.update(bbc);
        }

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

        if(!data.containsKey(Constants.KEY_DATA_CONTAINERS) || !data.get(Constants.KEY_DATA_CONTAINERS).getClass()
                .equals(ArrayList.class))  {
            error.put(Constants.KEY_DATA_CONTAINERS + ".notFound", hlpr.getMessage(Constants.ERROR_MSG_PREFIX +
                    Constants.KEY_DATA_CONTAINERS + ".notFound"));
            return false;
        }

        return true;
    }
}
