package barbot.database.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import barbot.database.dao.IngredientDao;
import barbot.database.model.Ingredient;

/**
 * Created by Naveen on 4/13/17.
 */
@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    IngredientDao ingredientDao;

    @Override
    @Transactional
    public Ingredient findByUid(String ingredientId) {
        Assert.hasLength(ingredientId, "IngredientId must not be empty");
        return ingredientDao.findByUid(ingredientId);
    }
}
