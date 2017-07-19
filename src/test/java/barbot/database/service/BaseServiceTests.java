package barbot.database.service;

import org.junit.Before;
import org.junit.Ignore;
import org.mockito.MockitoAnnotations;

import barbot.utils.TestDataHelper;

/**
 * Created by Naveen on 5/27/17.
 */
@Ignore
public abstract class BaseServiceTests {

    protected TestDataHelper testDataHelper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testDataHelper = new TestDataHelper();
    }
}
