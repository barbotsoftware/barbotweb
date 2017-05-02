package barbot.database.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import barbot.database.model.Barbot;
import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;
import barbot.database.repository.BarbotRepository;
import barbot.database.repository.IngredientRepository;
import barbot.database.repository.RecipeRepository;

/**
 * Created by Naveen on 4/12/17.
 */
@Service
public class BarbotServiceImpl implements BarbotService {

    @Resource
    private BarbotRepository barbotRepository;

    @Resource
    private RecipeRepository recipeRepository;

    @Resource
    private IngredientRepository ingredientRepository;

    public BarbotServiceImpl(BarbotRepository barbotRepository,
                             RecipeRepository recipeRepository,
                             IngredientRepository ingredientRepository) {
        this.barbotRepository = barbotRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    @Transactional
    public Barbot findById(String barbotId) {
        Assert.hasLength(barbotId, "BarbotId must not be empty");
        return this.barbotRepository.findByUid(barbotId);
    }

    @Override
    @Transactional
    public List<Recipe> getRecipes(Barbot barbot) {
        if (barbot == null) {
            throw new NullPointerException(barbot + " not found.");
        }

        return this.recipeRepository.findByBarbot(barbot);
    }

    @Override
    @Transactional
    public List<Ingredient> getIngredients(Barbot barbot) {
        if (barbot == null) {
            throw new NullPointerException(barbot + " not found.");
        }

        return this.ingredientRepository.findByBarbot(barbot);
    }
}
