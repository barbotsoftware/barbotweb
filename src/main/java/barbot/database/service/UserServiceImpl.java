package barbot.database.service;

import javax.transaction.Transactional;

import barbot.database.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import barbot.database.model.User;

/**
 * Created by Naveen on 4/13/17.
 */
@Service
@Component
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public User findById(String userId) {
        Assert.hasLength(userId, "UserId must not be empty");
        return userDao.findByUid(userId);
    }

    @Override
    public User findByName(String name) {
        Assert.hasLength(name, "Username must not be empty");
        return userDao.findByName(name);
    }

    @Override
    public void create(User user) {
        userDao.save(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByName(username);

        if(user == null) {
            throw new UsernameNotFoundException(username + " not found.");
        }

        return user;
    }
}
