package barbot.database.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import barbot.database.model.User;
import barbot.database.repository.UserRepository;

import java.util.List;

/**
 * Created by Naveen on 4/13/17.
 */
@Service
@Component
public class UserServiceImpl implements UserService, UserDetailsService {

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

    @Override
    @Transactional
    public User findByName(String name) {
        Assert.hasLength(name, "Username must not be empty");
        List<User> users = this.userRepository.findByName(name);
        return users.isEmpty() ? null : users.get(0);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByName(username);

        if(user == null) {
            throw new UsernameNotFoundException(username + " not found.");
        }

        return user;
    }
}
