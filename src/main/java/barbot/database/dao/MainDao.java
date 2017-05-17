package barbot.database.dao;

import org.hibernate.SQLQuery;
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
        SQLQuery query = currentSession().createSQLQuery("SELECT * FROM `" + tableName + "` WHERE " + field + " = :value");

        query.setParameter("value", value);

        return query.list().size() == 0;
    }

    /**
     * Checks if an entity exists in the database
     * @param tableName Name of the table/entity to check
     * @param id ID of teh entity
     * @return True is the entity exists
     */
    public boolean checkEntityExists(String tableName, String id) {
        SQLQuery query = currentSession().createSQLQuery("SELECT * FROM `" + tableName + "` WHERE uid = :id");

        query.setParameter("id", id);

        return query.list().size() > 0;
    }
}
