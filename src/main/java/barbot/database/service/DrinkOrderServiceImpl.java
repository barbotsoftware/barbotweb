package barbot.database.service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import barbot.database.model.DrinkOrder;
import barbot.database.repository.DrinkOrderRepository;

/**
 * Created by Naveen on 4/18/17.
 */
public class DrinkOrderServiceImpl implements DrinkOrderService {

    @Resource
    private DrinkOrderRepository drinkOrderRepository;

    public DrinkOrderServiceImpl(DrinkOrderRepository drinkOrderRepository) {
        this.drinkOrderRepository = drinkOrderRepository;
    }

    @Override
    @Transactional
    public DrinkOrder create(DrinkOrder drinkOrder) {
        DrinkOrder createdDrinkOrder = drinkOrder;
        return drinkOrderRepository.save(createdDrinkOrder);
    }
}
