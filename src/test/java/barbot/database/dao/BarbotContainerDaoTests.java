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
import barbot.database.model.Ingredient;

/**
 * Created by Naveen on 6/27/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = TestDatabaseConfig.class)
public class BarbotContainerDaoTests extends BaseDaoTests {

    private BarbotContainerDao barbotContainerDao;

    private List<Barbot> barbots;

    private List<Ingredient> ingredients;

    private List<BarbotContainer> barbotContainers;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        barbotContainerDao = new BarbotContainerDao();
        barbotContainerDao.setHibernateTemplate(mockTemplate);

        setUpTestData();
    }

    @Test
    public void testFindById() {
        int id = barbotContainers.get(0).getId();

        (Mockito.doReturn(barbotContainers.get(0)).when(mockTemplate))
                .get(BarbotContainer.class, id);

        BarbotContainer result = barbotContainerDao.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    public void testFindByBarbotAndNumber() {
        Barbot barbot = barbots.get(0);
        int number = barbotContainers.get(0).getNumber();

        (Mockito.doReturn(barbotContainers).when(mockTemplate))
                .find("FROM BarbotContainer WHERE barbot = ? and number = ?", barbot, number);

        BarbotContainer result = barbotContainerDao.findByBarbotAndNumber(barbot, number);

        assertThat(result).isNotNull();
        assertThat(result.getBarbot()).isEqualTo(barbot);
        assertThat(result.getNumber()).isEqualTo(number);
    }

    @Test
    public void testUpdate() {
        Mockito.when(mockTemplate.save(barbotContainers.get(0))).thenReturn((long)1);

        barbotContainerDao.update(barbotContainers.get(0));

        Mockito.verify(mockTemplate).update(barbotContainers.get(0));
    }

    private void setUpTestData() {
        // Barbots
        barbots = testDataHelper.createBarbotList(listSize);

        // Ingredients
        ingredients = testDataHelper.createIngredientList(listSize);

        // BarbotContainers
        barbotContainers = testDataHelper.createBarbotContainerList(listSize,
                ingredients, barbots);

        // Set BarbotContainers to first Barbot
        barbots.get(0).setBarbotContainers(new HashSet<>(barbotContainers));
    }
}
