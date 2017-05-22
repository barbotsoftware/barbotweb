package barbot.database.model.json;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

import barbot.database.model.Ingredient;
import barbot.database.model.View;

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

        JsonContent<Ingredient> result = this.jacksonTester.write(ingredientId);

        // Compare Ingredient Object to Json
        assertThat(result).isEqualToJson("ingredientid.json");

        // Check Id
        assertThat(result).hasJsonPathStringValue("@.ingredient_id");
        assertThat(result).extractingJsonPathStringValue("@.ingredient_id")
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

        JsonContent<Ingredient> result = this.jacksonTester.write(ingredientSummary);

        // Compare Ingredient Object to Json
        assertThat(result).isEqualToJson("ingredientsummary.json");

        // Check Id
        assertThat(result).hasJsonPathStringValue("@.ingredient_id");
        assertThat(result).extractingJsonPathStringValue("@.ingredient_id")
                .isEqualTo("ingredient_238932");

        // Check Name
        assertThat(result).hasJsonPathStringValue("@.name");
        assertThat(result).extractingJsonPathStringValue("@.name")
                .isEqualTo("Vodka");
    }

    @Test
    public void testDeserializeSummary() throws Exception {

        // Set View = Summary
        useView(View.Summary.class, jacksonTester);

        // Parse Json and compare with Ingredient Object
        assertThat(this.jacksonTester.parse(ingredientSummaryJson))
                .isEqualTo(ingredientSummary);

        Ingredient result = this.jacksonTester.parseObject(ingredientSummaryJson);

        // Check Id
        assertThat(result.getUid()).isEqualTo("ingredient_238932");

        // Check Name
        assertThat(result.getName()).isEqualTo("Vodka");
    }
}
