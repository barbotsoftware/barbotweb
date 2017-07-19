package barbot.database.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import barbot.config.TestDatabaseConfig;
import barbot.database.model.User;

/**
 * Created by Naveen on 5/21/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = TestDatabaseConfig.class)
public class UserDaoTests extends BaseDaoTests {

    private UserDao userDao;

    private BCryptPasswordEncoder passwordEncoder;

    private List<User> users;

    private User user;

    private final int userListSize = 5;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        passwordEncoder = new BCryptPasswordEncoder();

        userDao = new UserDao();
        userDao.setHibernateTemplate(mockTemplate);
        userDao.setPasswordEncoder(passwordEncoder);

        setUpTestData();
    }

    @Test
    public void testFindByUid() {
        String uid = user.getUid();

        (Mockito.doReturn(users).when(mockTemplate))
                .find("FROM User WHERE uid = ?", uid);

        User result = userDao.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    @Test
    public void testFindById() {
        int id = user.getId();

        (Mockito.doReturn(users.get(0)).when(mockTemplate))
                .get(User.class, id);

        User result = userDao.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    public void testFindByName() {
        String name = user.getName();

        (Mockito.doReturn(users).when(mockTemplate))
                .find("FROM User WHERE name = ?", name);

        User result = userDao.findByName(name);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
    }

    @Test
    public void testFindByEmailAndPassword() {
        String email = user.getEmail();
        String password = user.getPassword();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<User> results = new ArrayList<>();
        results.add(user);

        (Mockito.doReturn(results).when(mockTemplate))
                .find("FROM User WHERE email = ?", email);

        User result = userDao.findByEmailAndPassword(email, password);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    public void testSave() {
        Mockito.when(mockTemplate.save(users.get(0))).thenReturn((long)1);

        userDao.save(users.get(0));

        Mockito.verify(mockTemplate).save(users.get(0));
    }

    private void setUpTestData() {
        users = testDataHelper.createUserList(userListSize);

        user = users.get(0);
    }
}
