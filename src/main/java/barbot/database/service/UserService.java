package barbot.database.service;

import barbot.database.model.User;

/**
 * Created by Naveen on 4/13/17.
 */
public interface UserService {
    User findById(String userId);
    User findByName(String name);
    void create(User user);
}
