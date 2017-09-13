package barbot.database.service;

import java.util.List;

import javax.transaction.Transactional;

import barbot.database.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import barbot.database.dao.BarbotDao;

/**
 * Created by Naveen on 4/12/17.
 */
@Service
@Transactional
public class BarbotServiceImpl implements BarbotService {

    @Autowired
    private BarbotDao barbotDao;

    @Override
    public Barbot findByUid(String barbotId) {
        Assert.hasLength(barbotId, "BarbotId must not be empty");
        return barbotDao.findByUid(barbotId);
    }

    @Override
    public Barbot findById(int id) {
        return barbotDao.findById(id);
    }

    @Override
    public List<Recipe> getRecipes(Barbot barbot) {
        if (barbot == null) {
            throw new NullPointerException(barbot + " not found.");
        }

        return barbotDao.getRecipes(barbot);
    }

    @Override
    public List<Recipe> getRecipes(Barbot barbot, Category category, List<String> ingredientIds) {
        if(barbot == null) {
            throw new NullPointerException(barbot + " not found.");
        }

        return barbotDao.getRecipes(barbot, category, ingredientIds);
    }

    @Override
    public List<Ingredient> getIngredients(Barbot barbot) {
        if (barbot == null) {
            throw new NullPointerException(barbot + " not found.");
        }

        return barbotDao.getIngredients(barbot);
    }

    @Override
    public List<BarbotContainer> getBarbotContainers(Barbot barbot) {
        if (barbot == null) {
            throw new NullPointerException(barbot + " not found.");
        }

        return barbotDao.getBarbotContainers(barbot);
    }
}
