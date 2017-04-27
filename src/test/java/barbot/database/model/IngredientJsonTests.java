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
public class IngredientJsonTests extends BaseJsonTests {

    @Autowired
    private JacksonTester<Ingredient> jacksonTester;

    String ingredientIdJson;
    String ingredientSummaryJson;

    Ingredient ingredientId;
    Ingredient ingredientSummary;

    @Before
    public void setUp() {
        ingredientIdJson = "{\n" +
                "  \"ingredient_id\":\"ingredient_123456\"\n" +
                "}";

        ingredientSummaryJson = "{ \"name\": \"Vodka\"," +
                "  \"ingredient_id\": \"ingredient_238932\" }";

        ingredientId = new Ingredient("ingredient_123456");

        ingredientSummary = new Ingredient("ingredient_238932", "Vodka");
    }

    @Test
    public void testSerializeId() throws Exception {
        // Set View = Id
        useView(View.Id.class, jacksonTester);

        // Compare Ingredient Object to Json
        assertThat(this.jacksonTester.write(ingredientId)).isEqualToJson("ingredientid.json");

        // Check Id
        assertThat(this.jacksonTester.write(ingredientId)).hasJsonPathStringValue("@.ingredient_id");
        assertThat(this.jacksonTester.write(ingredientId)).extractingJsonPathStringValue("@.ingredient_id")
                .isEqualTo("ingredient_123456");
    }

    @Test
    public void testDeserializeId() throws Exception {
        // Set View = Id
        useView(View.Id.class, jacksonTester);

        assertThat(this.jacksonTester.parse(ingredientIdJson))
                .isEqualTo(ingredientId);

        // Check Id
        assertThat(this.jacksonTester.parseObject(ingredientIdJson).getUid()).isEqualTo("ingredient_123456");
    }

    @Test
    public void testSerializeSummary() throws Exception {

        // Set View = Summary
        useView(View.Summary.class, jacksonTester);

        // Compare Ingredient Object to Json
        assertThat(this.jacksonTester.write(ingredientSummary)).isEqualToJson("ingredientsummary.json");

        // Check name
        assertThat(this.jacksonTester.write(ingredientSummary)).hasJsonPathStringValue("@.name");
        assertThat(this.jacksonTester.write(ingredientSummary)).extractingJsonPathStringValue("@.name")
                .isEqualTo("Vodka");
    }

    @Test
    public void testDeserializeSummary() throws Exception {

        // Set View = Summary
        useView(View.Summary.class, jacksonTester);

        // Parse Json and compare with Ingredient Object
        assertThat(this.jacksonTester.parse(ingredientSummaryJson))
                .isEqualTo(ingredientSummary);

        // Check Name
        assertThat(this.jacksonTester.parseObject(ingredientSummaryJson).getName()).isEqualTo("Vodka");
    }
}
