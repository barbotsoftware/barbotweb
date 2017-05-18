package barbot.database.dao;

import barbot.database.model.Barbot;
import barbot.database.model.BarbotContainer;
import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;
import org.hibernate.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 5/11/2017.
 */
@Component
@Transactional
public class BarbotDao extends HibernateDaoSupport {

    public Barbot findByUid(String barbotId) {
        List list = getHibernateTemplate().find("FROM Barbot WHERE uid = ?", barbotId);

        return !list.isEmpty() ? (Barbot) list.get(0) : null;
    }

    public Barbot findById(int barbotId) {
        return getHibernateTemplate().get(Barbot.class, barbotId);
    }

    public List<Recipe> getRecipes(Barbot barbot) {
        List<Ingredient> ingredients = getIngredients(barbot);
        List<Recipe> recipes = (List<Recipe>)getHibernateTemplate().find("FROM Recipe WHERE custom = 0");

        List<Recipe> recipesForBarbot = new ArrayList<>();
        for(Recipe recipe : recipes) {
            getHibernateTemplate().initialize(recipe.getIngredients());
            if(ingredients.containsAll(recipe.getIngredients())) {
                recipesForBarbot.add(recipe);
            }
        }

        return recipesForBarbot;
    }

    public List<Ingredient> getIngredients(Barbot barbot) {
        barbot = getHibernateTemplate().get(Barbot.class, barbot.getId());
        getHibernateTemplate().initialize(barbot.getBarbotContainers());

        List<Ingredient> ingredients = new ArrayList<>();
        for(BarbotContainer container : barbot.getBarbotContainers()) {
            ingredients.add(container.getIngredient());
        }

        return ingredients;
    }
}
