package barbot.database.dao;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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

    private List<User> users;

    private final int userListSize = 10;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        userDao = new UserDao();
        userDao.setHibernateTemplate(mockTemplate);

        setUpTestData();
    }

    @Test
    public void testFindByUid() {
        String uid = users.get(0).getUid();

        (Mockito.doReturn(users).when(mockTemplate))
                .find("FROM User WHERE uid = ?", uid);

        User result = userDao.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    @Test
    public void testFindById() {
        int id = users.get(0).getId();

        (Mockito.doReturn(users.get(0)).when(mockTemplate))
                .get(User.class, id);

        User result = userDao.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    public void testFindByName() {
        String name = users.get(0).getName();

        (Mockito.doReturn(users).when(mockTemplate))
                .find("FROM User WHERE name = ?", name);

        User result = userDao.findByName(name);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
    }

    @Test
    public void testSave() {
        Mockito.when(mockTemplate.save(users.get(0))).thenReturn((long)1);

        userDao.save(users.get(0));

        Mockito.verify(mockTemplate).save(users.get(0));
    }

    private void setUpTestData() {
        users = new ArrayList<>();

        for (int i = 0; i < userListSize; i++) {
            User user = new User();
            user.setId(i);
            user.setUid("user_xxxxx" + i);
            user.setName("User " + i);
            users.add(user);
        }
    }
}
