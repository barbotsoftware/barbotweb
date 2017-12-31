package barbot.database.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import barbot.database.model.Barbot;
import barbot.database.model.BarbotGarnish;

/**
 * Created by Naveen on 12/31/17.
 */
@Component
@Transactional
public class BarbotGarnishDao extends HibernateDaoSupport {

    public BarbotGarnish findByBarbotAndOptionNumber(Barbot barbot, int optionNumber) {
        List list = getHibernateTemplate().find("FROM BarbotGarnish WHERE barbot = ? and optionNumber = ?", barbot, optionNumber);

        return !list.isEmpty() ? (BarbotGarnish) list.get(0) : null;
    }

    public void update(BarbotGarnish barbotGarnish) {
        if (barbotGarnish != null) {
            getHibernateTemplate().update(barbotGarnish);
        }
    }
}
