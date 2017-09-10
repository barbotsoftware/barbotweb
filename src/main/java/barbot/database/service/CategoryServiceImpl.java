package barbot.database.service;

import barbot.database.dao.CategoryDao;
import barbot.database.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public List<Category> findAllRoots() {
        return categoryDao.findAllRoots();
    }

    public Category findByUid(String id) {
        Assert.hasLength(id, "CategoryId must not be empty");
        return categoryDao.findByUid(id);
    }
}
