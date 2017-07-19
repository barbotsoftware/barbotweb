package barbot.database.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import barbot.database.model.DrinkOrder;

/**
 * Created by Alex on 5/11/2017.
 */
@Component
@Transactional
public class DrinkOrderDao extends HibernateDaoSupport{

    public DrinkOrder findByUid(String uid) {
        List list = getHibernateTemplate().find("FROM DrinkOrder WHERE uid = ?", uid);

        return !list.isEmpty() ? (DrinkOrder) list.get(0) : null;
    }

    public DrinkOrder findById(int id) {
        return getHibernateTemplate().get(DrinkOrder.class, id);
    }

    public void save(DrinkOrder drinkOrder) {
        getHibernateTemplate().save(drinkOrder);
    }
}
