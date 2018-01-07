package barbot.database.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import barbot.config.TestDatabaseConfig;
import barbot.database.model.Garnish;

/**
 * Created by Naveen on 1/7/18.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = TestDatabaseConfig.class)
public class GarnishDaoTests extends BaseDaoTests {

    private GarnishDao garnishDao;

    private List<Garnish> garnishes;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        garnishDao = new GarnishDao();
        garnishDao.setHibernateTemplate(mockTemplate);

        setUpTestData();
    }

    @Test
    public void testFindByUid() {
        String uid = garnishes.get(0).getUid();

        (Mockito.doReturn(garnishes).when(mockTemplate))
                .find("FROM Garnish WHERE uid = ?", uid);

        Garnish result = garnishDao.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    @Test
    public void testFindById() {
        int id = garnishes.get(0).getId();

        (Mockito.doReturn(garnishes.get(0)).when(mockTemplate))
                .get(Garnish.class, id);

        Garnish result = garnishDao.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    private void setUpTestData() {
        garnishes = testDataHelper.createGarnishList(listSize);
    }
}
