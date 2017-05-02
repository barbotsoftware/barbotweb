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

    String drinkOrderRequestJson;
    String drinkOrderResponseJson;

    DrinkOrder drinkOrderRequest;
    DrinkOrder drinkOrderResponse;

    @Before
    public void setUp() {
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

        // Compare DrinkOrder Object to Json
        assertThat(this.jacksonTester.write(drinkOrderResponse)).isEqualToJson("drinkorderresponse.json");

        // Check Id
        assertThat(this.jacksonTester.write(drinkOrderResponse)).hasJsonPathStringValue("@.drink_order_id");
        assertThat(this.jacksonTester.write(drinkOrderResponse)).extractingJsonPathStringValue("@.drink_order_id")
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
