package barbot.database.service;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import barbot.database.dao.RecipeDao;
import barbot.utils.Constants;
import barbot.utils.HelperMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;
import org.springframework.util.StringUtils;

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
            recipe.setUid(Constants.RECIPE_UID_PREFIX + hlpr.generateUid());
        }

        recipeDao.save(recipe);
    }

    @Override
    @Transactional
    public Recipe findById(String recipeId) {
        Assert.hasLength(recipeId, "RecipeId must not be empty");
        return recipeDao.findByUid(recipeId);
    }

    @Override
    @Transactional
    public List<Recipe> findAll() {
        return recipeDao.findAll();
    }
}
