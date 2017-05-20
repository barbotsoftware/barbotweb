package barbot.database.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.hibernate5.HibernateTemplate;

/**
 * Created by Naveen on 5/20/17.
 */
@Ignore
public abstract class BaseDaoTests {
    @Mock
    protected HibernateTemplate mockTemplate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}
