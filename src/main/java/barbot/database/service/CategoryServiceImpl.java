package barbot.database.service;

import barbot.database.dao.CategoryDao;
import barbot.database.model.Category;
import barbot.utils.HelperMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Alex on 9/10/2017.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    HelperMethods hlpr;

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public List<Category> findAllRoots() {
        return categoryDao.findAllRoots();
    }

    public Category findByUid(String id) {
        return categoryDao.findByUid(id);
    }

    @Override
    @Transactional
    public void create(Category category) {
        if(StringUtils.isEmpty(category.getUid())) {
            category.setUid(hlpr.generateUid());
        }

        categoryDao.save(category);
    }
}
