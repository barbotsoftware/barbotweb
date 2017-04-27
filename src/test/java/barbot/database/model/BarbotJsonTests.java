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
 * Created by Naveen on 4/26/17.
 */
@RunWith(SpringRunner.class)
@JsonTest
public class BarbotJsonTests extends BaseJsonTests {

    @Autowired
    private JacksonTester<Barbot> jacksonTester;

    String barbotRequestJson;

    Barbot barbotRequest;

    @Before
    public void setUp() {
        barbotRequestJson = "{\n" +
                "  \"barbot_id\":\"barbot_5r145d\"\n" +
                "}";

        barbotRequest = new Barbot("barbot_5r145d");
    }

    @Test
    public void testSerializeRequest() throws Exception {
        // Set View = Request
        useView(View.Request.class, jacksonTester);

        // Compare Barbot Object to Json
        assertThat(this.jacksonTester.write(barbotRequest)).isEqualToJson("barbotrequest.json");

        // Check Id
        assertThat(this.jacksonTester.write(barbotRequest)).hasJsonPathStringValue("@.barbot_id");
        assertThat(this.jacksonTester.write(barbotRequest)).extractingJsonPathStringValue("@.barbot_id")
                .isEqualTo("barbot_5r145d");
    }

    @Test
    public void testDeserializeRequest() throws Exception {
        // Set View = Request
        useView(View.Request.class, jacksonTester);

        assertThat(this.jacksonTester.parse(barbotRequestJson))
                .isEqualTo(barbotRequest);

        // Check Id
        assertThat(this.jacksonTester.parseObject(barbotRequestJson).getUid()).isEqualTo("barbot_5r145d");
    }
}
