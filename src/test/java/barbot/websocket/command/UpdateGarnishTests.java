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
import barbot.database.model.Garnish;
import barbot.database.service.BarbotService;
import barbot.database.service.GarnishService;

/**
 * Created by Naveen on 1/7/18.
 */
public class UpdateGarnishTests extends CommandTests {

    @Mock
    private GarnishService garnishService;

    @Mock
    private BarbotService barbotService;

    private Barbot barbot;

    private String barbotId;

    private Garnish garnish;

    private BarbotGarnish barbotGarnish;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testExecute() {
        (Mockito.doReturn(barbot)).when(barbotService).findByUid(barbotId);

        (Mockito.doReturn(barbotGarnish)).when(garnishService).findByBarbotAndOptionNumber(barbot, barbotGarnish.getOptionNumber());

        HashMap result = (HashMap) command.execute();

        Mockito.verify(garnishService).update(barbotGarnish);

        BarbotGarnish bg = (BarbotGarnish) result.get("garnish");

        assertThat(bg.getBarbot()).isEqualTo(barbot);
        assertThat(bg.getGarnish()).isEqualTo(garnish);
        assertThat(bg.getOptionNumber()).isEqualTo(barbotGarnish.getOptionNumber());
        assertThat(bg.getQuantity()).isEqualTo(barbotGarnish.getQuantity());
    }

    @Test
    public void testValidate() {
        (Mockito.doReturn(true)).when(fieldValidator).validate((HashMap)msg.get("data"), fieldsToValidate);

        boolean result = command.validate();

        assertThat(result).isTrue();
    }

    private void setUpTestData() {
        barbotId = "barbot_123456";
        barbot = new Barbot(barbotId);
        garnish = new Garnish("garnish123456", "Garnish123456");
        barbotGarnish = new BarbotGarnish(barbot, garnish, 1, 1);

        HashMap updatedGarnish = new HashMap<>();
        updatedGarnish.put("option_number", barbotGarnish.getOptionNumber());
        updatedGarnish.put("quantity", barbotGarnish.getQuantity());

        HashMap data = new HashMap<>();
        data.put("barbot_id", barbotId);
        data.put("garnish", updatedGarnish);

        msg.put("data", data);
        fieldsToValidate.put("barbot_id", "required|exists:barbot");
        command = new UpdateGarnish(garnishService, barbotService, fieldValidator, helperMethods, msg);
    }
}
