package barbot.database.model.json;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import barbot.config.TestDatabaseConfig;
import barbot.database.model.Ingredient;
import barbot.database.model.Recipe;
import barbot.database.model.RecipeIngredient;
import barbot.database.model.View;

/**
 * Created by Naveen on 4/15/17.
 */
@RunWith(SpringRunner.class)
@JsonTest
@ContextConfiguration(classes = TestDatabaseConfig.class)
public class RecipeJsonTests extends BaseJsonTests {

    private JacksonTester<Recipe> jacksonTester;

    String recipeIdJson;
    String recipeSummaryJson;
    String recipeDetailJson;

    Recipe recipeId;
    Recipe recipeSummary;
    Recipe recipeDetail;

    Set<RecipeIngredient> recipeIngredients;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testSerializeId() throws Exception {

        // Set View = Id
        useView(View.Id.class, jacksonTester);

        JsonContent<Recipe> result = this.jacksonTester.write(recipeId);

        // Compare Recipe Object to Json
        assertThat(result).isEqualToJson("recipeid.json");

        // Check Id
        assertThat(result).hasJsonPathStringValue("@.recipe_id");
        assertThat(result).extractingJsonPathStringValue("@.recipe_id")
                .isEqualTo("recipe_123456");
    }

    @Test
    public void testDeserializeId() throws Exception {

        // Set View = Id
        useView(View.Id.class, jacksonTester);

        // Parse Json and compare with Recipe Object
//        assertThat(this.jacksonTester.parse(recipeIdJson))
//                .isEqualTo(recipeId);

        // Check Id
        assertThat(this.jacksonTester.parseObject(recipeIdJson).getUid()).isEqualTo("recipe_123456");
    }

    @Test
    public void testSerializeSummary() throws Exception {

        // Set View = Summary
        useView(View.Summary.class, jacksonTester);

        JsonContent<Recipe> result = this.jacksonTester.write(recipeSummary);

        // Compare Recipe Object to Json
        assertThat(result).isEqualToJson("recipesummary.json");

        // Check Id
        assertThat(result).hasJsonPathStringValue("@.recipe_id");
        assertThat(result).extractingJsonPathStringValue("@.recipe_id")
                .isEqualTo("recipe_8a4d7a");

        // Check Name
        assertThat(result).hasJsonPathStringValue("@.name");
        assertThat(result).extractingJsonPathStringValue("@.name")
                .isEqualTo("Cuba Libre");

        // Check Img
        assertThat(result).hasJsonPathStringValue("@.img");
        assertThat(result).extractingJsonPathStringValue("@.img")
                .isEqualTo("http://192.168.1.41/barbot/public/images/cubalibre.png");
    }

    @Test
    public void testDeserializeSummary() throws Exception {

        // Set View = Summary
        useView(View.Summary.class, jacksonTester);

        // Parse Json and compare with Recipe Object
//        assertThat(this.jacksonTester.parse(recipeSummaryJson))
//                .isEqualTo(recipeSummary);

        Recipe result = this.jacksonTester.parseObject(recipeSummaryJson);

        // Check Id
        assertThat(result.getUid()).isEqualTo("recipe_8a4d7a");

        // Check Name
        assertThat(result.getName()).isEqualTo("Cuba Libre");

        // Check Img
        assertThat(result.getImageUrl()).isEqualTo("http://192.168.1.41/barbot/public/images/cubalibre.png");
    }

    @Test
    public void testSerializeDetail() throws Exception {

        // Set View = Detail
        useView(View.Detail.class, jacksonTester);

        JsonContent<Recipe> result = this.jacksonTester.write(recipeDetail);

        // Compare Recipe Object to Json
        //assertThat(result).isEqualToJson("recipedetail.json");

        // Check Id
        assertThat(result).hasJsonPathStringValue("@.recipe_id");
        assertThat(result).extractingJsonPathStringValue("@.recipe_id")
                .isEqualTo("recipe_9aa19a");

        // Check Name
        assertThat(result).hasJsonPathStringValue("@.name");
        assertThat(result).extractingJsonPathStringValue("@.name")
                .isEqualTo("The Sour");

        // Check Img
        assertThat(result).hasJsonPathStringValue("@.img");
        assertThat(result).extractingJsonPathStringValue("@.img")
                .isEqualTo("http://farm8.staticflickr.com/7252/7594170156_46bf574865_o.jpg");

        // Check Ingredients
        assertThat(result).hasJsonPathArrayValue("@.ingredients");
    }

    @Test
    public void testDeserializeDetail() throws Exception {

        // Set View = Detail
        useView(View.Detail.class, jacksonTester);

        // Parse Json and compare with Recipe Object
//        assertThat(this.jacksonTester.parse(recipeDetailJson))
//                .isEqualTo(recipeDetail);

        Recipe result = this.jacksonTester.parseObject(recipeDetailJson);

        // Check Id
        assertThat(result.getUid()).isEqualTo("recipe_9aa19a");

        // Check Name
        assertThat(result.getName()).isEqualTo("The Sour");

        // Check Img
        assertThat(result.getImageUrl()).isEqualTo("http://farm8.staticflickr.com/7252/7594170156_46bf574865_o.jpg");

        // Check Ingredients
        assertThat(result.getRecipeIngredients()).isEqualTo(recipeIngredients);
    }

    private void setUpTestData() {
        recipeIdJson = "{\n" +
                "  \"recipe_id\":\"recipe_123456\"\n" +
                "}";

        recipeSummaryJson = "{ \"name\": \"Cuba Libre\"," +
                "  \"recipe_id\": \"recipe_8a4d7a\"," +
                "  \"img\": \"http:\\/\\/192.168.1.41\\/barbot\\/public\\/images\\/cubalibre.png\" }";

        recipeDetailJson = "{" +
                "            \"name\": \"The Sour\"," +
                "            \"recipe_id\": \"recipe_9aa19a\"," +
                "            \"img\": \"http:\\/\\/farm8.staticflickr.com\\/7252\\/7594170156_46bf574865_o.jpg\"," +
                "            \"ingredients\": [{" +
                "                \"ingredient_id\": \"ingredient_123450\"," +
                "                \"amount\": 0.0" +
                "            }, {" +
                "                \"ingredient_id\": \"ingredient_123451\"," +
                "                \"amount\": 1.0" +
                "            }, {" +
                "                \"ingredient_id\": \"ingredient_123452\"," +
                "                \"amount\": 2.0" +
                "            }, {" +
                "                \"ingredient_id\": \"ingredient_123453\"," +
                "                \"amount\": 3.0" +
                "            }]" +
                "}";

        recipeId = new Recipe("recipe_123456");

        recipeSummary = new Recipe("recipe_8a4d7a",
                "Cuba Libre",
                "http://192.168.1.41/barbot/public/images/cubalibre.png");

        recipeDetail = new Recipe("recipe_9aa19a",
                "The Sour",
                "http://farm8.staticflickr.com/7252/7594170156_46bf574865_o.jpg");

        recipeIngredients = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            RecipeIngredient recipeIngredient = new RecipeIngredient(recipeDetail, new Ingredient("ingredient_12345" + i), new BigDecimal(i));

            recipeIngredients.add(recipeIngredient);
        }

        recipeDetail.setRecipeIngredients(recipeIngredients);
    }
}
