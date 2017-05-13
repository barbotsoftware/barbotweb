package barbot.database.dao;

import barbot.database.model.Ingredient;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

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
}
