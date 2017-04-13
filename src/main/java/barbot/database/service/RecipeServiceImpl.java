package barbot.database.service;

import barbot.database.model.Recipe;
import barbot.database.repository.RecipeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Naveen on 4/12/17.
 */
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

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
