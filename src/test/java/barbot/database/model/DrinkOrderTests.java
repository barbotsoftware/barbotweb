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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Naveen on 5/29/17.
 */
public class DrinkOrderTests extends EntityTests {

    @Test
    public void testTypeAnnotations() {
        assertType(DrinkOrder.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(DrinkOrder.class, "uid", Column.class, JsonProperty.class, JsonView.class);
        assertField(DrinkOrder.class, "user", ManyToOne.class, JoinColumn.class);
        assertField(DrinkOrder.class, "recipe", ManyToOne.class, JoinColumn.class, JsonView.class);
        assertField(DrinkOrder.class, "barbot", ManyToOne.class, JoinColumn.class, JsonView.class);
        assertField(DrinkOrder.class, "ice", Column.class, JsonView.class);
        assertField(DrinkOrder.class, "garnish", Column.class, JsonView.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(DrinkOrder.class, "getUid");
        assertMethod(DrinkOrder.class, "getUser");
        assertMethod(DrinkOrder.class, "getRecipe");
        assertMethod(DrinkOrder.class, "getBarbot");
        assertMethod(DrinkOrder.class, "getIce");
        assertMethod(DrinkOrder.class, "getGarnish");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(DrinkOrder.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(DrinkOrder.class);

        assertThat(t.name()).isEqualTo("drink_order");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testUid() {
        Column c = createColumn(DrinkOrder.class, "uid");

        assertThat(c.name()).isEqualTo("uid");

        JsonProperty jp = createJsonProperty(DrinkOrder.class, "uid");

        assertThat(jp.value()).isEqualTo("drink_order_id");

        JsonView jv = createJsonView(DrinkOrder.class, "uid");

        Class<?>[] array = { View.Response.class };

        assertThat(jv.value()).isEqualTo(array);
    }

    @Test
    public void testUser() {
        ManyToOne mto = createManyToOne(DrinkOrder.class, "user");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(DrinkOrder.class, "user");

        assertThat(jc.name()).isEqualTo("user_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();
    }

    @Test
    public void testRecipe() {
        ManyToOne mto = createManyToOne(DrinkOrder.class, "recipe");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(DrinkOrder.class, "recipe");

        assertThat(jc.name()).isEqualTo("recipe_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();

        JsonView jv = createJsonView(DrinkOrder.class, "recipe");

        Class<?>[] array = { View.Summary.class };

        assertThat(jv.value()).isEqualTo(array);
    }

    @Test
    public void testBarbot() {
        ManyToOne mto = createManyToOne(DrinkOrder.class, "barbot");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(DrinkOrder.class, "barbot");

        assertThat(jc.name()).isEqualTo("barbot_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();

        JsonView jv = createJsonView(DrinkOrder.class, "barbot");

        Class<?>[] array = { View.Summary.class };

        assertThat(jv.value()).isEqualTo(array);
    }

    @Test
    public void testIce() {
        Column c = createColumn(DrinkOrder.class, "ice");

        assertThat(c.name()).isEqualTo("ice");
        assertThat(c.nullable()).isFalse();

        JsonView jv = createJsonView(DrinkOrder.class, "ice");

        Class<?>[] array = { View.Summary.class };

        assertThat(jv.value()).isEqualTo(array);
    }

    @Test
    public void testGarnish() {
        Column c = createColumn(DrinkOrder.class, "garnish");

        assertThat(c.name()).isEqualTo("garnish");
        assertThat(c.nullable()).isFalse();

        JsonView jv = createJsonView(DrinkOrder.class, "garnish");

        Class<?>[] array = { View.Summary.class };

        assertThat(jv.value()).isEqualTo(array);
    }
}
