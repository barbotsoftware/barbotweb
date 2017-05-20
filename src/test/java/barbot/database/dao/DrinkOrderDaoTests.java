package barbot.database.dao;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.context.TestExecutionListeners;

import barbot.database.model.DrinkOrder;

/**
 * Created by Naveen on 5/20/17.
 */
public class DrinkOrderDaoTests extends BaseDaoTests {

    private DrinkOrderDao drinkOrderDao;

    private List<DrinkOrder> drinkOrders;

    private final int drinkOrderListSize = 10;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        drinkOrderDao = new DrinkOrderDao();
        drinkOrderDao.setHibernateTemplate(mockTemplate);

        drinkOrders = new ArrayList<>();

        for (int i = 1; i < drinkOrderListSize; i++) {
            DrinkOrder drinkOrder = new DrinkOrder();
            drinkOrder.setId(i);
            drinkOrder.setUid("drinkOrder_xxxxx" + i);
            drinkOrders.add(drinkOrder);
        }
    }

    @Test
    public void testFindByUid() {
        String uid = drinkOrders.get(0).getUid();

        (Mockito.doReturn(drinkOrders).when(mockTemplate))
                .find("FROM DrinkOrder WHERE uid = ?", uid);

        assertThat(drinkOrderDao.findByUid(uid).getUid()).isEqualTo(uid);
    }

    @Test
    public void testFindById() {
        int id = drinkOrders.get(0).getId();

        (Mockito.doReturn(drinkOrders.get(0)).when(mockTemplate))
                .get(DrinkOrder.class, id);

        assertThat(drinkOrderDao.findById(id).getId()).isEqualTo(id);
    }

    @Test
    public void testSave() {

    }
}
