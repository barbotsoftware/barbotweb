package barbot.database.model;

import static org.assertj.core.api.Assertions.assertThat;

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
public class RecipeJsonTest {

    @Autowired
    private JacksonTester<Recipe> json;

    String recipeJson = "{ \"name\": \"Cuba Libre\"," +
                        "  \"id\": \"recipe_8a4d7a\"," +
                        "  \"img\": \"http:\\/\\/192.168.1.41\\/barbot\\/public\\/images\\/term.jpg\" }";

    @Test
    public void testSerialize() throws Exception {
        Recipe recipe = new Recipe("recipe_8a4d7a",
                                 "Cuba Libre",
                              "http://192.168.1.41/barbot/public/images/term.jpg");
        assertThat(this.json.write(recipe)).isEqualToJson(recipeJson);

        assertThat(this.json.write(recipe)).hasJsonPathStringValue("@.name");
        assertThat(this.json.write(recipe)).extractingJsonPathStringValue("@.name")
                .isEqualTo("Cuba Libre");
    }

    @Test
    public void testDeserialize() throws Exception {
        assertThat(this.json.parse(recipeJson))
                .isEqualTo(new Recipe("recipe_8a4d7a", "Cuba Libre", "http://192.168.1.41/barbot/public/images/term.jpg"));
        assertThat(this.json.parseObject(recipeJson).getName()).isEqualTo("Cuba Libre");
    }
}
