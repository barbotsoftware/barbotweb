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
    public Barbot findByNameAndPassword(String name, String password) {
        Assert.hasLength(name, "Name must not be empty");
        Assert.hasLength(password, "Password must not be empty");
        return barbotDao.findByNameAndPassword(name, password);
    }

    @Override
    public List<Recipe> getRecipes(Barbot barbot) {
        validateBarbot(barbot);

        return barbotDao.getRecipes(barbot);
    }

    @Override
    public List<Recipe> getRecipes(Barbot barbot, Category category, List<String> ingredientIds) {
        validateBarbot(barbot);

        return barbotDao.getRecipes(barbot, category, ingredientIds);
    }

    @Override
    public List<Ingredient> getIngredients(Barbot barbot) {
        validateBarbot(barbot);

        return barbotDao.getIngredients(barbot);
    }

    @Override
    public List<BarbotContainer> getBarbotContainers(Barbot barbot) {
        validateBarbot(barbot);

        return barbotDao.getBarbotContainers(barbot);
    }

    @Override
    public List<BarbotGarnish> getGarnishes(Barbot barbot) {
        validateBarbot(barbot);

        return barbotDao.getGarnishes(barbot);
    }

    private void validateBarbot(Barbot barbot) {
        if (barbot == null) {
            throw new NullPointerException(barbot + " not found.");
        }
    }
}
