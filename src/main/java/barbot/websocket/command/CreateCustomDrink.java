package barbot.websocket.command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;
import barbot.database.model.User;
import barbot.database.model.View;
import barbot.database.service.DrinkOrderService;
import barbot.database.service.RecipeService;
import barbot.utils.Constants;

/**
 * Created by Naveen on 4/11/17.
 */
public class CreateCustomDrink extends BaseCommand {

    @Autowired
    RecipeService recipeService;

    private User user;

    public CreateCustomDrink(HashMap msg, User user) {
        super(msg);

        this.user = user;

        setJsonView(View.Id.class);
    }

    @Override
    public Object execute() {

        // TODO: Generate Recipe UID
        String uid = "";
        String name = (String) data.get("name");
        String imageUrl = "";
        Set<Ingredient> ingredients = new HashSet<>(); // data.get("ingredients");

        Recipe recipe = new Recipe(uid, name, this.user, true, imageUrl, ingredients);

        this.recipeService.create(recipe);

        Map response = new HashMap<>();
        response.put(Constants.KEY_DATA_RECIPE_ID, recipe.getUid());

        return response;
    }

    @Override
    public boolean validate() {
        // TODO: Validate Recipe

        return super.validate();
    }
}
