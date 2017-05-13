package barbot.database.service;


import barbot.database.dao.DrinkOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import barbot.database.model.DrinkOrder;

import javax.transaction.Transactional;

/**
 * Created by Naveen on 4/18/17.
 */
@Service
@Transactional
public class DrinkOrderServiceImpl implements DrinkOrderService {

    @Autowired
    DrinkOrderDao drinkOrderDao;

    @Override
    public void create(DrinkOrder drinkOrder) {
        DrinkOrder createdDrinkOrder = drinkOrder;
        drinkOrderDao.save(createdDrinkOrder);
    }

    @Override
    public DrinkOrder findById(String drinkOrderId) {
        Assert.hasLength(drinkOrderId, "DrinkOrderId must not be empty");
        return drinkOrderDao.findByUid(drinkOrderId);
    }
}
