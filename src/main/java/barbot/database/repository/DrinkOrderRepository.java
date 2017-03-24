package barbot.database.repository;

import barbot.database.model.DrinkOrders;

public interface DrinkOrderRepository extends BaseRepository<DrinkOrders, Long> {

    DrinkOrders findByUid(String uid);
}
