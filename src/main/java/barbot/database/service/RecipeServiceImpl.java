package barbot.database.service;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import barbot.database.dao.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;

/**
 * Created by Naveen on 4/12/17.
 */
@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeDao recipeDao;

    @Override
    @Transactional
    public void create(Recipe recipe) {
        Recipe createdRecipe = recipe;
        recipeDao.save(createdRecipe);
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
