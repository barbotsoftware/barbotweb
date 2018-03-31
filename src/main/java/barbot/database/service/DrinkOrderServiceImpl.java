package barbot.database.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import barbot.database.dao.DrinkOrderDao;
import barbot.database.model.DrinkOrder;
import barbot.utils.Constants;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 4/18/17.
 */
@Service
@Transactional
public class DrinkOrderServiceImpl implements DrinkOrderService {

    @Autowired
    DrinkOrderDao drinkOrderDao;

    @Autowired
    HelperMethods hlpr;

    @Override
    public void create(DrinkOrder drinkOrder) {
        if(StringUtils.isEmpty(drinkOrder.getUid())) {
            drinkOrder.setUid(hlpr.generateUid());
        }

        drinkOrderDao.save(drinkOrder);
    }

    @Override
    public DrinkOrder findByUid(String drinkOrderId) {
        Assert.hasLength(drinkOrderId, "DrinkOrderId must not be empty");
        return drinkOrderDao.findByUid(drinkOrderId);
    }
}
