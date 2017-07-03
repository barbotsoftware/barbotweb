package barbot.database.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.dao.UserDao;
import barbot.database.model.User;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 5/27/17.
 */
public class UserServiceTests extends BaseServiceTests {

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserDao userDao;

    @Mock
    private HelperMethods hlpr;

    private User user;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testFindByUid() {
        String uid = user.getUid();

        (Mockito.doReturn(user)).when(userDao).findByUid(uid);

        User result = userService.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    @Test
    public void testFindByName() {
        String name = user.getName();

        (Mockito.doReturn(user)).when(userDao).findByName(name);

        User result = userService.findByName(name);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
    }

    @Test
    public void testFindByEmailAndPassword() {
        String email = user.getEmail();
        String password = user.getPassword();

        (Mockito.doReturn(user)).when(userDao).findByEmailAndPassword(email, password);

        User result = userService.findByEmailAndPassword(email, password);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(result.getEmail());
        assertThat(result.getPassword()).isEqualTo(result.getPassword());
    }

    @Test
    public void testCreate() {
        userService.create(user);

        Mockito.verify(userDao).save(user);
    }

//  Not exposed by UserService Interface:
//    @Test
//    public void testLoadUserByUsername() {
//        String name = user.getName();
//
//        (Mockito.doReturn(user)).when(userDao).findByName(name);
//
//        User result = userService.loadUserByUsername(name);
//
//        assertThat(result).isNotNull();
//        assertThat(result.getName()).isEqualTo(name);
//    }

    private void setUpTestData() {
        user = new User("user_123456",
                        "Naveen Yadav",
                        "naveen@barbot.io",
                        "test_pwd_123");
    }
}
