package barbot.database.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import barbot.database.model.Recipe;
import barbot.database.repository.RecipeRepository;

/**
 * Created by Naveen on 4/12/17.
 */
@Service
public class RecipeServiceImpl implements RecipeService {

    @Resource
    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public Recipe create(Recipe recipe) {
        Recipe createdRecipe = recipe;
        return recipeRepository.save(createdRecipe);
    }

    @Override
    @Transactional
    public Recipe findById(String recipeId) {
        Assert.hasLength(recipeId, "RecipeId must not be empty");
        return recipeRepository.findByUid(recipeId);
    }

    @Override
    @Transactional
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }
}
