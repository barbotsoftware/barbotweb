package barbot.database.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import barbot.database.model.User;
import barbot.database.repository.UserRepository;

/**
 * Created by Naveen on 4/13/17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User findById(String userId) {
        Assert.hasLength(userId, "UserId must not be empty");
        return this.userRepository.findByUid(userId);
    }
}
