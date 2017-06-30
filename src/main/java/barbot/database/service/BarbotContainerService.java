package barbot.database.service;

import barbot.database.model.Barbot;
import barbot.database.model.BarbotContainer;

/**
 * Created by Naveen on 6/27/17.
 */
public interface BarbotContainerService {
    BarbotContainer findById(int id);

    BarbotContainer findByBarbotAndNumber(Barbot barbot, int number);

    void update(BarbotContainer barbotContainer);
}
