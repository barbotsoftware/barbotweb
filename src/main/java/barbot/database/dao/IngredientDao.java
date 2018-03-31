package barbot.database.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import barbot.database.model.Ingredient;

/**
 * Created by Alex on 5/11/2017.
 */
@Component
@Transactional
public class IngredientDao extends HibernateDaoSupport {

    public Ingredient findByUid(String uid) {
        List list = getHibernateTemplate().find("FROM Ingredient WHERE uid = ?", uid);

        return !list.isEmpty() ? (Ingredient) list.get(0) : null;
    }

    public Ingredient findById(int id) {
        return getHibernateTemplate().get(Ingredient.class, id);
    }

    public Ingredient findByName(String name) {
        List list = getHibernateTemplate().find("FROM Ingredient WHERE name = ?", name);

        return !list.isEmpty() ? (Ingredient) list.get(0) : null;
    }
}
