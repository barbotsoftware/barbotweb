package barbot.database.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.dao.BarbotContainerDao;
import barbot.database.model.Barbot;
import barbot.database.model.BarbotContainer;

/**
 * Created by Naveen on 6/27/17.
 */
public class BarbotContainerServiceTests extends BaseServiceTests {

    @InjectMocks
    private BarbotContainerService barbotContainerService = new BarbotContainerServiceImpl();

    @Mock
    private BarbotContainerDao barbotContainerDao;

    private Barbot barbot;

    private BarbotContainer barbotContainer;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testFindById() {
        int id = barbotContainer.getId();

        (Mockito.doReturn(barbotContainer)).when(barbotContainerDao).findById(id);

        BarbotContainer result = barbotContainerService.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    public void testFindByBarbotAndNumber() {
        int number = barbotContainer.getNumber();

        (Mockito.doReturn(barbotContainer).when(barbotContainerDao)).findByBarbotAndNumber(barbot, number);

        BarbotContainer result = barbotContainerService.findByBarbotAndNumber(barbot, number);

        assertThat(result).isNotNull();
        assertThat(result.getBarbot()).isEqualTo(barbot);
        assertThat(result.getNumber()).isEqualTo(number);
    }

    @Test
    public void testUpdate() {
        barbotContainerService.update(barbotContainer);

        Mockito.verify(barbotContainerDao).update(barbotContainer);
    }

    private void setUpTestData() {

        // Barbot
        barbot = new Barbot("barbot_123456");
        barbot.setId(1);

        // BarbotContainers
        barbotContainer = new BarbotContainer(barbot, null, 1,
                new BigDecimal(48.0),
                new BigDecimal(48.0));
        barbotContainer.setId(1);
    }
}
