package barbot.database.service;

import javax.transaction.Transactional;

import barbot.database.dao.UserDao;
import barbot.utils.Constants;
import barbot.utils.HelperMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import barbot.database.model.User;
import org.springframework.util.StringUtils;

/**
 * Created by Naveen on 4/13/17.
 */
@Service
@Component
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    HelperMethods hlpr;

    @Override
    public User findByUid(String userId) {
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
        if(StringUtils.isEmpty(user.getUid())) {
            user.setUid(Constants.USER_UID_PREFIX + hlpr.generateUid());
        }
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
