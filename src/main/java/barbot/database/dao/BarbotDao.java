package barbot.database.dao;

import barbot.database.model.Barbot;
import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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
        return null;
    }

    public List<Ingredient> getIngredients(Barbot barbot) {
        return null;
    }
}
