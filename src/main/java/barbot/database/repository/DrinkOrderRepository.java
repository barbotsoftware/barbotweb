package barbot.database.repository;

import barbot.database.model.DrinkOrder;

public interface DrinkOrderRepository extends BaseRepository<DrinkOrder, Long> {

    DrinkOrder findByUid(String uid);
}
