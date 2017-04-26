package barbot.database.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Naveen on 4/15/17.
 */
@RunWith(SpringRunner.class)
@JsonTest
public class RecipeJsonTests extends BaseJsonTests {

    @Autowired
    private JacksonTester<Recipe> jacksonTester;

    String recipeSummaryJson;
    String recipeDetailJson;

    Recipe recipeSummary;
    Recipe recipeDetail;

    Set<Ingredient> ingredients;

    @Before
    public void setUp() {
        recipeSummaryJson = "{ \"name\": \"Cuba Libre\"," +
                "  \"id\": \"recipe_8a4d7a\"," +
                "  \"img\": \"http:\\/\\/192.168.1.41\\/barbot\\/public\\/images\\/cubalibre.png\" }";

        recipeDetailJson = "{" +
                "            \"name\": \"The Sour\"," +
                "            \"id\": \"recipe_9aa19a\"," +
                "            \"img\": \"http:\\/\\/farm8.staticflickr.com\\/7252\\/7594170156_46bf574865_o.jpg\"," +
                "            \"ingredients\": [{" +
                "                \"ingredient_id\": \"ingredient_123450\"," +
                "                \"name\": \"Ingredient0\"" +
                "            }, {" +
                "                \"ingredient_id\": \"ingredient_123451\"," +
                "                \"name\": \"Ingredient1\"" +
                "            }, {" +
                "                \"ingredient_id\": \"ingredient_123452\"," +
                "                \"name\": \"Ingredient2\"" +
                "            }, {" +
                "                \"ingredient_id\": \"ingredient_123453\"," +
                "                \"name\": \"Ingredient3\"" +
                "            }]" +
                "}";

        recipeSummary = new Recipe("recipe_8a4d7a",
                "Cuba Libre",
                "http://192.168.1.41/barbot/public/images/cubalibre.png");

        ingredients = new HashSet<Ingredient>();

        for (int i = 0; i < 4; i++) {
            Ingredient ingredient = new Ingredient("ingredient_12345" + i, "Ingredient" + i);

            ingredients.add(ingredient);
        }

        recipeDetail = new Recipe("recipe_9aa19a",
                "The Sour",
                "http://farm8.staticflickr.com/7252/7594170156_46bf574865_o.jpg",
                ingredients);
    }

    @Test
    public void testSerializeSummary() throws Exception {

        // Set View = Summary
        useView(View.Summary.class, jacksonTester);

        // Compare Recipe Object to Json
        assertThat(this.jacksonTester.write(recipeSummary)).isEqualToJson("recipesummary.json");

        // Check name
        assertThat(this.jacksonTester.write(recipeSummary)).hasJsonPathStringValue("@.name");
        assertThat(this.jacksonTester.write(recipeSummary)).extractingJsonPathStringValue("@.name")
                .isEqualTo("Cuba Libre");
    }

    @Test
    public void testDeserializeSummary() throws Exception {

        // Set View = Summary
        useView(View.Summary.class, jacksonTester);

        // Parse Json and compare with Recipe Object
        assertThat(this.jacksonTester.parse(recipeSummaryJson))
                .isEqualTo(recipeSummary);

        // Check Name
        assertThat(this.jacksonTester.parseObject(recipeSummaryJson).getName()).isEqualTo("Cuba Libre");
    }

    @Test
    public void testSerializeDetail() throws Exception {

        // Set View = Detail
        useView(View.Detail.class, jacksonTester);

        // Compare Recipe Object to Json
        assertThat(this.jacksonTester.write(recipeDetail)).isEqualToJson("recipedetail.json");

        // Check name
        assertThat(this.jacksonTester.write(recipeDetail)).hasJsonPathStringValue("@.name");
        assertThat(this.jacksonTester.write(recipeDetail)).extractingJsonPathStringValue("@.name")
                .isEqualTo("The Sour");
    }

    @Test
    public void testDeserializeDetail() throws Exception {
        // Set View = Detail
        useView(View.Detail.class, jacksonTester);

        // Parse Json and compare with Recipe Object
        assertThat(this.jacksonTester.parse(recipeDetailJson))
                .isEqualTo(recipeDetail);

        // Check Name
        assertThat(this.jacksonTester.parseObject(recipeDetailJson).getName()).isEqualTo("The Sour");
    }
}
