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
 * Created by Naveen on 4/16/17.
 */
@RunWith(SpringRunner.class)
@JsonTest
public class IngredientJsonTest extends BaseJsonTest {

    @Autowired
    private JacksonTester<Ingredient> jacksonTester;

    String ingredientSummaryJson;

    Ingredient ingredientSummary;

    @Before
    public void setUp() {
        ingredientSummaryJson = "{ \"name\": \"Vodka\"," +
                "  \"ingredient_id\": \"ingredient_238932\" }";

        ingredientSummary = new Ingredient("ingredient_238932", "Vodka");
    }

    @Test
    public void testSummarySerialize() throws Exception {

        // Set View = Summary
        useView(View.Summary.class, jacksonTester);

        // Write Recipe Object to Json
        assertThat(this.jacksonTester.write(ingredientSummary)).isEqualToJson(ingredientSummaryJson);

        // Check name
        assertThat(this.jacksonTester.write(ingredientSummary)).hasJsonPathStringValue("@.name");
        assertThat(this.jacksonTester.write(ingredientSummary)).extractingJsonPathStringValue("@.name")
                .isEqualTo("Vodka");
    }

    @Test
    public void testSummaryDeserialize() throws Exception {

        // Set View = Summary
        useView(View.Summary.class, jacksonTester);

        // Parse Json and compare with Recipe Object
        assertThat(this.jacksonTester.parse(ingredientSummaryJson))
                .isEqualTo(ingredientSummary);

        // Check Name
        assertThat(this.jacksonTester.parseObject(ingredientSummaryJson).getName()).isEqualTo("Vodka");
    }
}
