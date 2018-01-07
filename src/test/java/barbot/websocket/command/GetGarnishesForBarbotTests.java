package barbot.websocket.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.model.Barbot;
import barbot.database.model.BarbotGarnish;
import barbot.database.service.BarbotService;

/**
 * Created by Naveen on 1/7/18.
 */
public class GetGarnishesForBarbotTests extends CommandTests {

    @Mock
    private BarbotService barbotService;

    private String barbotId;

    private Barbot barbot;

    private List<BarbotGarnish> barbotGarnishes;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testExecute() {
        (Mockito.doReturn(barbot)).when(barbotService).findByUid(barbotId);

        (Mockito.doReturn(barbotGarnishes)).when(barbotService).getGarnishes(barbot);

        HashMap result = (HashMap) command.execute();

        List<BarbotGarnish> results = (List<BarbotGarnish>) result.get("garnishes");

        assertThat(results).isNotNull();
        assertThat(results).isEqualTo(barbotGarnishes);
    }

    @Test
    public void testValidate() {
        (Mockito.doReturn(true)).when(fieldValidator).validate((HashMap)msg.get("data"), fieldsToValidate);

        boolean result = command.validate();

        assertThat(result).isTrue();
    }

    private void setUpTestData() {
        barbotId = "barbot_123456";

        HashMap data = new HashMap<>();
        data.put("barbot_id", barbotId);

        msg.put("data", data);

        fieldsToValidate.put("barbot_id", "required|exists:barbot");

        barbot = new Barbot(barbotId);

        List<Barbot> barbots = new ArrayList<Barbot>();
        barbots.add(barbot);

        barbotGarnishes = testDataHelper.createBarbotGarnishList(listSize, barbots);

        command = new GetGarnishesForBarbot(barbotService, fieldValidator, helperMethods, msg);
    }
}
