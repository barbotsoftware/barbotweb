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
import barbot.database.model.DrinkOrder;

/**
 * Created by Naveen on 5/20/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = TestDatabaseConfig.class)
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

        setUpTestData();
    }

    @Test
    public void testFindByUid() {
        String uid = drinkOrders.get(0).getUid();

        (Mockito.doReturn(drinkOrders).when(mockTemplate))
                .find("FROM DrinkOrder WHERE uid = ?", uid);

        DrinkOrder result = drinkOrderDao.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    @Test
    public void testFindById() {
        int id = drinkOrders.get(0).getId();

        (Mockito.doReturn(drinkOrders.get(0)).when(mockTemplate))
                .get(DrinkOrder.class, id);

        DrinkOrder result = drinkOrderDao.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    public void testSave() {
        Mockito.when(mockTemplate.save(drinkOrders.get(0))).thenReturn((long)1);

        drinkOrderDao.save(drinkOrders.get(0));

        Mockito.verify(mockTemplate).save(drinkOrders.get(0));
    }

    private void setUpTestData() {
        drinkOrders = new ArrayList<>();

        for (int i = 1; i < drinkOrderListSize; i++) {
            DrinkOrder drinkOrder = new DrinkOrder();
            drinkOrder.setId(i);
            drinkOrder.setUid("drinkOrder_xxxxx" + i);
            drinkOrders.add(drinkOrder);
        }
    }
}
