package barbot.websocket.command;

import java.util.HashMap;

import barbot.event.BarbotEvent;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

import barbot.database.model.Barbot;
import barbot.database.model.DrinkOrder;
import barbot.database.model.Recipe;
import barbot.database.model.User;
import barbot.database.model.View;
import barbot.database.service.BarbotService;
import barbot.database.service.DrinkOrderService;
import barbot.database.service.RecipeService;
import barbot.utils.Constants;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Created by Naveen on 4/11/17.
 */
public class OrderDrink extends BaseCommand {

    BarbotService barbotService;

    RecipeService recipeService;

    DrinkOrderService drinkOrderService;

    private ApplicationEventPublisher publisher;

    private FieldValidator fieldValidator;

    private User user;

    public OrderDrink(DrinkOrderService drinkOrderService, RecipeService recipeService, BarbotService barbotService,
                      FieldValidator validator, HelperMethods hlpr, ApplicationEventPublisher publisher, HashMap msg,
                      User user) {
        super(msg, hlpr);
        this.user = user;
        // Return Drink Order Response
        setJsonView(View.Response.class);
        this.drinkOrderService = drinkOrderService;
        this.recipeService = recipeService;
        this.barbotService = barbotService;
        this.fieldValidator = validator;
        this.publisher = publisher;
    }

    @Override
    public Object execute() {
        // Get Barbot
        String barbotId = (String) data.get(Constants.KEY_DATA_BARBOT_ID);
        Barbot barbot = this.barbotService.findByUid(barbotId);

        // Get Recipe
        String recipeId = (String) data.get(Constants.KEY_DATA_RECIPE_ID);
        Recipe recipe = this.recipeService.findByUid(recipeId);

        // Get Ice and Garnish
        Integer ice = data.containsKey(Constants.KEY_DATA_ICE) ? (int) data.get(Constants.KEY_DATA_ICE) : 0;
        Integer garnish = data.containsKey(Constants.KEY_DATA_GARNISH) ? (int) data.get(Constants.KEY_DATA_GARNISH) : 0;

        // Create drink order
        DrinkOrder drinkOrder = new DrinkOrder(this.user, recipe, barbot, ice, garnish);

        // Save the drink order
        drinkOrderService.create(drinkOrder);

        // Send a message to the barbot with the drink order
        BarbotEvent barbotEvent = new BarbotEvent(barbot, drinkOrder, Constants.EVENT_DRINK_ORDERED);

        publisher.publishEvent(barbotEvent);

        return drinkOrder;
    }

    @Override
    public boolean validate() {
        if(!super.validate())
            return false;

        HashMap fieldsToValidate = new HashMap();
        fieldsToValidate.put(Constants.KEY_DATA_BARBOT_ID, "required|exists:barbot");
        fieldsToValidate.put(Constants.KEY_DATA_RECIPE_ID, "required|exists:recipe");
        if(!fieldValidator.validate((HashMap)message.get(Constants.KEY_DATA), fieldsToValidate)) {
            error = fieldValidator.getErrors();
            return false;
        }

        return true;
    }
}
