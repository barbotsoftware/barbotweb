package barbot.database.model.json;

import org.junit.Ignore;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.boot.test.json.JacksonTester;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Naveen on 4/16/17.
 */
@Ignore
public abstract class BaseJsonTests {

    public void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Possibly configure the mapper
        JacksonTester.initFields(this, objectMapper);
    }

    void useView(Class<?> view, JacksonTester jacksonTester) {
        ObjectMapper objectMapper = (ObjectMapper) Whitebox.getInternalState(jacksonTester,
                "objectMapper");
        objectMapper = objectMapper.setConfig(objectMapper.getSerializationConfig().withView(view));
        Whitebox.setInternalState(jacksonTester, "objectMapper", objectMapper);
    }
}
