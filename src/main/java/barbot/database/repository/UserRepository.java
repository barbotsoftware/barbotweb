package barbot.database.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import barbot.database.model.Users;

public interface UserRepository extends CrudRepository<Users, Long> {
    List<Users> findByName(String name);
    Users findByEmail(String email);
}
