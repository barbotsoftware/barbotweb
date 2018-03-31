package barbot.database.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.dao.BarbotGarnishDao;
import barbot.database.dao.GarnishDao;
import barbot.database.model.Barbot;
import barbot.database.model.BarbotGarnish;
import barbot.database.model.Garnish;

/**
 * Created by Naveen on 1/7/18.
 */
public class GarnishServiceTests extends BaseServiceTests {
    @InjectMocks
    private GarnishService garnishService = new GarnishServiceImpl();

    @Mock
    private GarnishDao garnishDao;

    @Mock
    private BarbotGarnishDao barbotGarnishDao;

    private Barbot barbot;

    private Garnish garnish;

    private BarbotGarnish barbotGarnish;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testFindByUid() {
        String uid = garnish.getUid();

        (Mockito.doReturn(garnish)).when(garnishDao).findByUid(uid);

        Garnish result = garnishService.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    @Test
    public void testFindByBarbotAndOptionNumber() {
        int optionNumber = barbotGarnish.getOptionNumber();

        (Mockito.doReturn(barbotGarnish)).when(barbotGarnishDao).findByBarbotAndOptionNumber(barbot, optionNumber);

        BarbotGarnish result = garnishService.findByBarbotAndOptionNumber(barbot, optionNumber);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(barbotGarnish);
    }

    @Test
    public void testUpdate() {
        garnishService.update(barbotGarnish);

        Mockito.verify(barbotGarnishDao).update(barbotGarnish);
    }

    private void setUpTestData() {
        barbot = new Barbot("barbot_123456", "barbot1");
        barbot.setId(1);
        garnish = new Garnish("garnish_123456", "Garnish123456");
        barbotGarnish = new BarbotGarnish(barbot, garnish, 1, 25);
    }
}
