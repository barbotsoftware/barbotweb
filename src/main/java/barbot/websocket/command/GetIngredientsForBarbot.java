package barbot.websocket.command;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    BarbotService barbotService;

    public GetIngredientsForBarbot(BarbotService barbotService, HashMap msg) {
        super(msg);
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
        return this.barbotService.getIngredients(barbot);
    }

    @Override
    public boolean validate() {
        // TODO: Validate BarbotID

        return super.validate();
    }
}
