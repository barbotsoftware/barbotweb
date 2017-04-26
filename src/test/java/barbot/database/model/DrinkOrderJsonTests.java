package barbot.database.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Naveen on 4/25/17.
 */
@RunWith(SpringRunner.class)
@JsonTest
public class DrinkOrderJsonTests extends BaseJsonTests {

    @Autowired
    private JacksonTester<DrinkOrder> jacksonTester;

    DrinkOrder drinkOrder;

    @Before
    public void setUp() {
        drinkOrder = new DrinkOrder("drinkorder_5e0f58");
    }

    @Test
    public void testSerialize() throws Exception {
        // Set View = Id
        useView(View.Id.class, jacksonTester);

        // Compare DrinkOrder Object to Json
        assertThat(this.jacksonTester.write(drinkOrder)).isEqualToJson("drinkorderid.json");

        // Check Id
        assertThat(this.jacksonTester.write(drinkOrder)).hasJsonPathStringValue("@.drink_order_id");
        assertThat(this.jacksonTester.write(drinkOrder)).extractingJsonPathStringValue("@.drink_order_id")
                .isEqualTo("drinkorder_5e0f58");
    }
}
