package barbot.database.service;

import barbot.database.model.User;

/**
 * Created by Naveen on 4/13/17.
 */
public interface UserService {
    User findByUid(String userId);
    User findByName(String name);
    User findByUsernameAndPassword(String username, String password);
    User findByEmailAndPassword(String email, String password);
    void create(User user);
}
