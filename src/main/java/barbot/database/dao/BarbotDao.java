package barbot.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import barbot.database.model.*;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

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

    public List<Recipe> getRecipes(Barbot barbot, Category category, List<String> ingredientIds) {
        List<Ingredient> ingredients = ingredientIds.size() > 0 ? getIngredients(barbot, ingredientIds) : getIngredients(barbot);
        List<Recipe> recipes = (List<Recipe>)getHibernateTemplate().find("FROM Recipe WHERE custom = 0");

        List<Recipe> recipesForBarbot = new ArrayList<>();
        for(Recipe recipe : recipes) {
            getHibernateTemplate().initialize(recipe.getIngredients());
            getHibernateTemplate().initialize(recipe.getCategories());
            if(ingredients.containsAll(recipe.getIngredients()) && recipe.getCategories().contains(category)) {
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

    public List<Ingredient> getIngredients(Barbot barbot, List<String> ingredientIdFilter) {
        barbot = getHibernateTemplate().get(Barbot.class, barbot.getId());
        getHibernateTemplate().initialize(barbot.getBarbotContainers());

        List<Ingredient> ingredients = new ArrayList<>();
        for(BarbotContainer container : barbot.getBarbotContainers()) {
            if(ingredientIdFilter.contains(container.getIngredient().getUid())) {
                ingredients.add(container.getIngredient());
            }
        }

        return ingredients;
    }

    public List<BarbotContainer> getBarbotContainers(Barbot barbot) {
        barbot = getHibernateTemplate().get(Barbot.class, barbot.getId());
        getHibernateTemplate().initialize(barbot.getBarbotContainers());

        return new ArrayList<>(barbot.getBarbotContainers());
    }
}
