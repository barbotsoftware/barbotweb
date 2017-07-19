package barbot.database.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import barbot.database.model.Recipe;

/**
 * Created by Alex on 5/11/2017.
 */
@Component
@Transactional
public class RecipeDao extends HibernateDaoSupport {

    public Recipe findByUid(String uid) {
        List list = getHibernateTemplate().find("FROM Recipe WHERE uid = ?", uid);

        return !list.isEmpty() ? (Recipe) list.get(0) : null;
    }

    public Recipe findById(int id) {
        return getHibernateTemplate().get(Recipe.class, id);
    }

    public List<Recipe> findAll() {
        return (List<Recipe>) getHibernateTemplate().find("FROM Recipe");
    }

    public void save(Recipe recipe) {
        getHibernateTemplate().save(recipe);
    }
}
