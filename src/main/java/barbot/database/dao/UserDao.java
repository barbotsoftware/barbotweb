package barbot.database.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import barbot.database.model.User;

/**
 * Created by Alex on 5/11/2017.
 */
@Component
@Transactional
public class UserDao extends HibernateDaoSupport {

    private BCryptPasswordEncoder passwordEncoder;

    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUid(String uid) {
        List list = getHibernateTemplate().find("FROM User WHERE uid = ?", uid);

        return !list.isEmpty() ? (User) list.get(0) : null;
    }

    public User findById(int id) {
        return getHibernateTemplate().get(User.class, id);
    }

    public User findByName(String name) {
        List list = getHibernateTemplate().find("FROM User WHERE name = ?", name);

        return !list.isEmpty() ? (User) list.get(0) : null;
    }

    public User findByEmailAndPassword(String email, String password) {
        List list = getHibernateTemplate().find("FROM User WHERE email = ?", email);

        if (list != null) {
            User u = (User) list.get(0);
            if (u != null) {
                if (passwordEncoder.matches(password, u.getPassword())){
                    return u;
                }
            }
        }

        return null;
    }

    public void save(User user) {
        getHibernateTemplate().save(user);
    }
}
