package barbot.websocket.command;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import barbot.database.model.Barbot;
import barbot.database.service.BarbotService;

/**
 * Created by Naveen on 6/6/17.
 */
public class SetContainersForBarbotTests extends CommandTests {

    @Mock
    private BarbotService barbotService;

    private Barbot barbot;

    private String barbotId;

    private final int listSize = 9;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testExecute() {

    }

    @Test
    public void testValidate() {

    }

    private void setUpTestData() {
        barbotId = "barbot_123456";

        HashMap data = new HashMap<>();
        data.put("barbot_id", barbotId);

        msg.put("data", data);

        fieldsToValidate.put("barbot_id", "required|exists:barbot");

        barbot = new Barbot(barbotId);
    }
}
