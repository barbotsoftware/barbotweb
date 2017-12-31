package barbot.database.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import barbot.database.model.Garnish;

/**
 * Created by Naveen on 12/30/17.
 */
@Component
@Transactional
public class GarnishDao extends HibernateDaoSupport {

    public Garnish findByUid(String uid) {
        List list = getHibernateTemplate().find("FROM Garnish WHERE uid = ?", uid);

        return !list.isEmpty() ? (Garnish) list.get(0) : null;
    }

    public Garnish findById(int id) {
        return getHibernateTemplate().get(Garnish.class, id);
    }
}
