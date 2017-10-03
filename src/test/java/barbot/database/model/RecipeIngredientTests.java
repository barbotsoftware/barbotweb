package barbot.database.model;

import static barbot.utils.AssertAnnotations.assertField;
import static barbot.utils.AssertAnnotations.assertMethod;
import static barbot.utils.AssertAnnotations.assertType;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Naveen on 5/29/17.
 */
public class RecipeIngredientTests extends EntityTests {

    @Test
    public void testTypeAnnotations() {
        assertType(RecipeIngredient.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(RecipeIngredient.class, "recipe", ManyToOne.class, JoinColumn.class, JsonView.class,
                JsonBackReference.class);
        assertField(RecipeIngredient.class, "ingredient", ManyToOne.class, JoinColumn.class, JsonProperty.class,
                JsonView.class);
        assertField(RecipeIngredient.class, "amount", Column.class, JsonView.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(RecipeIngredient.class, "getRecipe");
        assertMethod(RecipeIngredient.class, "getIngredient");
        assertMethod(RecipeIngredient.class, "getAmount");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(RecipeIngredient.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(RecipeIngredient.class);

        assertThat(t.name()).isEqualTo("recipe_ingredient");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testRecipe() {
        ManyToOne mto = createManyToOne(RecipeIngredient.class, "recipe");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(RecipeIngredient.class, "recipe");

        assertThat(jc.name()).isEqualTo("recipe_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();

        JsonView jv = createJsonView(RecipeIngredient.class, "recipe");

        assertThat(jv.value()).contains(View.Detail.class);

        JsonBackReference jbr = createJsonBackReference(RecipeIngredient.class, "recipe");

        assertThat(jbr).isNotNull();
    }

    @Test
    public void testIngredient() {
        ManyToOne mto = createManyToOne(RecipeIngredient.class, "ingredient");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(RecipeIngredient.class, "ingredient");

        assertThat(jc.name()).isEqualTo("ingredient_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();

        JsonProperty jp = createJsonProperty(RecipeIngredient.class, "ingredient");

        assertThat(jp.value()).isEqualTo("ingredient");

        JsonView jv = createJsonView(RecipeIngredient.class, "ingredient");

        assertThat(jv.value()).contains(View.Summary.class);
    }

    @Test
    public void testAmount() {
        Column c = createColumn(RecipeIngredient.class, "amount");

        assertThat(c.name()).isEqualTo("amount");

        JsonView jv = createJsonView(RecipeIngredient.class, "amount");

        assertThat(jv.value()).contains(View.Summary.class);
    }
}
