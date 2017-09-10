package barbot.database.service;

import barbot.database.model.Category;

import java.util.List;

/**
 * Created by Alex on 9/10/2017.
 */
public interface CategoryService {

    List<Category> findAll();
    List<Category> findAllRoots();
    Category findByUid(String id);
}
