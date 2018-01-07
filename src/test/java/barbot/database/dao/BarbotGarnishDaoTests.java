package barbot.database.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import barbot.config.TestDatabaseConfig;
import barbot.database.model.Barbot;
import barbot.database.model.BarbotContainer;
import barbot.database.model.BarbotGarnish;

/**
 * Created by Naveen on 1/7/18.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = TestDatabaseConfig.class)
public class BarbotGarnishDaoTests extends BaseDaoTests {

    private BarbotGarnishDao barbotGarnishDao;

    private List<Barbot> barbots;

    private List<BarbotGarnish> barbotGarnishes;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        barbotGarnishDao = new BarbotGarnishDao();
        barbotGarnishDao.setHibernateTemplate(mockTemplate);

        setUpTestData();
    }

    @Test
    public void testFindByBarbotAndOptionNumber() {
        Barbot barbot = barbots.get(0);
        int optionNumber = barbotGarnishes.get(0).getOptionNumber();

        (Mockito.doReturn(barbotGarnishes).when(mockTemplate))
                .find("FROM BarbotGarnish WHERE barbot = ? and optionNumber = ?", barbot, optionNumber);

        BarbotGarnish result = barbotGarnishDao.findByBarbotAndOptionNumber(barbot, optionNumber);

        assertThat(result).isNotNull();
        assertThat(result.getBarbot()).isEqualTo(barbot);
        assertThat(result.getOptionNumber()).isEqualTo(optionNumber);
    }

    @Test
    public void testUpdate() {
        Mockito.when(mockTemplate.save(barbotGarnishes.get(0))).thenReturn((long)1);

        barbotGarnishDao.update(barbotGarnishes.get(0));

        Mockito.verify(mockTemplate).update(barbotGarnishes.get(0));
    }

    private void setUpTestData() {
        // Barbots
        barbots = testDataHelper.createBarbotList(listSize);

        // BarbotContainers
        barbotGarnishes = testDataHelper.createBarbotGarnishList(listSize, barbots);

        // Set BarbotContainers to first Barbot
        barbots.get(0).setGarnishes(new HashSet<>(barbotGarnishes));
    }
}
