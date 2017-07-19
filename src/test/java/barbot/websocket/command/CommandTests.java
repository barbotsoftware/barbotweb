package barbot.websocket.command;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Ignore;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;
import barbot.utils.TestDataHelper;

/**
 * Created by Naveen on 5/27/17.
 */
@Ignore
public abstract class CommandTests {

    protected Command command;

    protected TestDataHelper testDataHelper;

    @Mock
    protected FieldValidator fieldValidator;

    protected HelperMethods helperMethods;

    protected HashMap msg;

    protected HashMap fieldsToValidate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testDataHelper = new TestDataHelper();
        helperMethods = new HelperMethods();
        msg = new HashMap<>();
        fieldsToValidate = new HashMap();
    }
}
