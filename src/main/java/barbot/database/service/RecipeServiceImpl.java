package barbot.database.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import barbot.database.dao.RecipeDao;
import barbot.database.model.Recipe;
import barbot.utils.Constants;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 4/12/17.
 */
@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    HelperMethods hlpr;

    @Override
    @Transactional
    public void create(Recipe recipe) {
        if(StringUtils.isEmpty(recipe.getUid())) {
            recipe.setUid(hlpr.generateUid());
        }

        recipeDao.save(recipe);
    }

    @Override
    @Transactional
    public Recipe findByUid(String recipeId) {
        Assert.hasLength(recipeId, "RecipeId must not be empty");
        return recipeDao.findByUid(recipeId);
    }

    @Override
    @Transactional
    public Recipe findByName(String name) {
        Assert.hasLength(name, "Name must not be empty");
        return recipeDao.findByName(name);
    }

    @Override
    @Transactional
    public List<Recipe> findAll() {
        return recipeDao.findAll();
    }
}
