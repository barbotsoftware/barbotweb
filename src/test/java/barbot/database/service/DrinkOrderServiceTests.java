package barbot.database.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.dao.DrinkOrderDao;
import barbot.database.model.DrinkOrder;
import barbot.utils.HelperMethods;

/**
 * Created by Naveen on 5/27/17.
 */
public class DrinkOrderServiceTests extends BaseServiceTests {

    @InjectMocks
    private DrinkOrderService drinkOrderService = new DrinkOrderServiceImpl();

    @Mock
    private DrinkOrderDao drinkOrderDao;

    private DrinkOrder drinkOrder;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testCreate() {
        drinkOrderService.create(drinkOrder);

        Mockito.verify(drinkOrderDao).save(drinkOrder);
    }

    @Test
    public void testFindByUid() {
        String uid = drinkOrder.getUid();

        (Mockito.doReturn(drinkOrder)).when(drinkOrderDao).findByUid(uid);

        DrinkOrder result = drinkOrderService.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    private void setUpTestData() {
        drinkOrder = new DrinkOrder("drinkOrder_123456");
    }
}
