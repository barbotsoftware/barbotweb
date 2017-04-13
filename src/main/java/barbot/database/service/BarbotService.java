package barbot.database.service;

import barbot.database.model.Barbot;

/**
 * Created by Naveen on 4/12/17.
 */
public interface BarbotService {
    Barbot findById(String barbotId);
}
