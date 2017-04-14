package barbot.database.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import barbot.database.model.Ingredient;
import barbot.database.repository.IngredientRepository;

/**
 * Created by Naveen on 4/13/17.
 */
@Service
public class IngredientServiceImpl implements IngredientService {

    @Resource
    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    @Transactional
    public Ingredient findById(String ingredientId) {
        Assert.hasLength(ingredientId, "IngredientId must not be empty");
        return ingredientRepository.findByUid(ingredientId);
    }
}
