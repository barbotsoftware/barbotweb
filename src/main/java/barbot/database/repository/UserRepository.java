package barbot.database.repository;

import java.util.List;

import barbot.database.model.User;

public interface UserRepository extends BaseRepository<User, Long> {

    List<User> findByName(String name);

    User findByEmail(String email);

    User findByUid(String uid);
}
