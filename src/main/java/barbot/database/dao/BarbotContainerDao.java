package barbot.database.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import barbot.database.model.Barbot;
import barbot.database.model.BarbotContainer;

/**
 * Created by Naveen on 6/27/17.
 */
@Component
@Transactional
public class BarbotContainerDao extends HibernateDaoSupport {

    public BarbotContainer findById(int barbotContainerId) {
        return getHibernateTemplate().get(BarbotContainer.class, barbotContainerId);
    }

    public BarbotContainer findByBarbotAndNumber(Barbot barbot, int number) {
        List list = getHibernateTemplate().find("FROM BarbotContainer WHERE barbot = ? and number = ?", barbot, number);

        return !list.isEmpty() ? (BarbotContainer) list.get(0) : null;
    }

    public void update(BarbotContainer barbotContainer) {
        if (barbotContainer != null) {
            getHibernateTemplate().update(barbotContainer);
        }
    }
}
