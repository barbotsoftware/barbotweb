package barbot.database.model;

import static barbot.utils.AssertAnnotations.assertField;
import static barbot.utils.AssertAnnotations.assertMethod;
import static barbot.utils.AssertAnnotations.assertType;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Naveen on 5/29/17.
 */
public class IngredientTests extends EntityTests {

    @Test
    public void testTypeAnnotations() {
        assertType(Ingredient.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(Ingredient.class, "uid", Column.class, JsonProperty.class, JsonView.class);
        assertField(Ingredient.class, "name", Column.class, JsonView.class);
        assertField(Ingredient.class, "abv", Column.class);
        assertField(Ingredient.class, "recipeIngredients", OneToMany.class);
        assertField(Ingredient.class, "recipes", ManyToMany.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(Ingredient.class, "getUid");
        assertMethod(Ingredient.class, "getName");
        assertMethod(Ingredient.class, "getAbv");
        assertMethod(Ingredient.class, "getRecipeIngredients");
        assertMethod(Ingredient.class, "getRecipes");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(Ingredient.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(Ingredient.class);

        assertThat(t.name()).isEqualTo("ingredient");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testUid() {
        Column c = createColumn(Ingredient.class, "uid");

        assertThat(c.name()).isEqualTo("uid");
        assertThat(c.nullable()).isFalse();

        JsonProperty jp = createJsonProperty(Ingredient.class, "uid");

        assertThat(jp.value()).isEqualTo("ingredient_id");

        JsonView jv = createJsonView(Ingredient.class, "uid");

        Class<?>[] array = { View.Id.class };

        assertThat(jv.value()).isEqualTo(array);
    }

    @Test
    public void testName() {
        Column c = createColumn(Ingredient.class, "name");

        assertThat(c.name()).isEqualTo("name");
        assertThat(c.nullable()).isFalse();

        JsonView jv = createJsonView(Ingredient.class, "name");

        Class<?>[] array = { View.Summary.class };

        assertThat(jv.value()).isEqualTo(array);
    }

    @Test
    public void testAbv() {
        Column c = createColumn(Ingredient.class, "abv");

        assertThat(c.name()).isEqualTo("abv");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testRecipeIngredients() {
        OneToMany otm = createOneToMany(Ingredient.class, "recipeIngredients");

        assertThat(otm.mappedBy()).isEqualTo("recipe");
    }

    @Test
    public void testRecipes() {
        ManyToMany mtm = createManyToMany(Ingredient.class, "recipes");

        assertThat(mtm.fetch()).isEqualTo(FetchType.LAZY);
        assertThat(mtm.mappedBy()).isEqualTo("ingredients");
        assertThat(mtm.targetEntity()).isEqualTo(Recipe.class);
    }
}
