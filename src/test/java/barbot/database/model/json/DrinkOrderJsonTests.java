package barbot.database.model.json;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import barbot.config.TestDatabaseConfig;
import barbot.database.model.DrinkOrder;
import barbot.database.model.View;

/**
 * Created by Naveen on 4/25/17.
 */
@RunWith(SpringRunner.class)
@JsonTest
@ContextConfiguration(classes = TestDatabaseConfig.class)
public class DrinkOrderJsonTests extends BaseJsonTests {

    private JacksonTester<DrinkOrder> jacksonTester;

    String drinkOrderRequestJson;
    String drinkOrderResponseJson;

    DrinkOrder drinkOrderRequest;
    DrinkOrder drinkOrderResponse;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        drinkOrderRequestJson = "";

        drinkOrderResponseJson = "{\n" +
                "  \"drink_order_id\":\"drinkorder_5e0f58\"\n" +
                "}";

        drinkOrderRequest = new DrinkOrder();

        drinkOrderResponse = new DrinkOrder("drinkorder_5e0f58");
    }

    @Test
    public void testSerializeRequest() throws Exception {
        // TODO: finish request tests
    }

    @Test
    public void testDeserializeRequest() throws Exception {

    }

    @Test
    public void testSerializeResponse() throws Exception {
        // Set View = Response
        useView(View.Response.class, jacksonTester);

        JsonContent<DrinkOrder> result = this.jacksonTester.write(drinkOrderResponse);

        // Compare DrinkOrder Object to Json
        assertThat(result).isEqualToJson("drinkorderresponse.json");

        // Check Id
        assertThat(result).hasJsonPathStringValue("@.drink_order_id");
        assertThat(result).extractingJsonPathStringValue("@.drink_order_id")
                .isEqualTo("drinkorder_5e0f58");
    }

    @Test
    public void testDeserializeResponse() throws Exception {
        // Set View = Response
        useView(View.Response.class, jacksonTester);

        assertThat(this.jacksonTester.parse(drinkOrderResponseJson))
                .isEqualTo(drinkOrderResponse);

        // Check Id
        assertThat(this.jacksonTester.parseObject(drinkOrderResponseJson).getUid()).isEqualTo("drinkorder_5e0f58");
    }
}
