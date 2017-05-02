package barbot.database.dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by Alex on 5/1/2017.
 */
@Component
@Transactional
public class MainDao extends HibernateDaoSupport {

    /**
     * Checks if the given value exists in the given table
     * @param tableName Name of the table
     * @param field Column name of the field to check
     * @param value Value to check
     * @return True if field is unique
     */
    public boolean checkFieldIsUnique(String tableName, String field, String value) {
        Session session = getSessionFactory().getCurrentSession();

        SQLQuery query = session.createSQLQuery("SELECT * FROM `" + tableName + "` WHERE " + field + " = :value");

        query.setParameter("value", value);

        return query.list().size() == 0;
    }
}