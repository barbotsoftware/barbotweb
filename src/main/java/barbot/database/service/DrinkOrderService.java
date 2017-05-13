package barbot.database.service;

import barbot.database.model.DrinkOrder;

/**
 * Created by Naveen on 4/18/17.
 */
public interface DrinkOrderService {
    void create(DrinkOrder drinkOrder);

    DrinkOrder findById(String drinkOrderId);
}
