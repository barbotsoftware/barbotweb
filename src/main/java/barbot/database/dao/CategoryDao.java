package barbot.database.dao;

import barbot.database.model.Category;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Alex on 9/10/2017.
 */
@Component
@Transactional
public class CategoryDao extends HibernateDaoSupport{

    public List<Category> findAll() {
        List list = getHibernateTemplate().find("FROM Category");

        return list;
    }

    public List<Category> findAllRoots() {
        List list = getHibernateTemplate().find("FROM Category WHERE parent_category_id = NULL");

        return list;
    }

    public Category findByUid(String id) {
        List list = getHibernateTemplate().find("FROM Category WHERE uid = ?", id);

        Category category = list.size() > 0 ? (Category) list.get(0) : null;

        if(category != null) {
            getHibernateTemplate().initialize(category.getRecipes());
        }

        return category;
    }
}
