package barbot.database.service;

import barbot.database.model.Garnish;

/**
 * Created by Naveen on 12/30/17.
 */
public interface GarnishService {
    Garnish findByUid(String garnishId);
}
