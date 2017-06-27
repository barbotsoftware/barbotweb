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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Naveen on 5/28/17.
 */
public class BarbotContainerTests extends EntityTests {

    @Test
    public void testTypeAnnotations() {
        assertType(BarbotContainer.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(BarbotContainer.class, "barbot", ManyToOne.class, JoinColumn.class);
        assertField(BarbotContainer.class, "ingredient", ManyToOne.class, JoinColumn.class,
                JsonIdentityInfo.class, JsonIdentityReference.class, JsonProperty.class, JsonView.class);
        assertField(BarbotContainer.class, "number", Column.class, JsonView.class);
        assertField(BarbotContainer.class, "currentVolume", Column.class, JsonProperty.class, JsonView.class);
        assertField(BarbotContainer.class, "maxVolume", Column.class, JsonProperty.class, JsonView.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(BarbotContainer.class, "getBarbot");
        assertMethod(BarbotContainer.class, "getIngredient");
        assertMethod(BarbotContainer.class, "getNumber");
        assertMethod(BarbotContainer.class, "getCurrentVolume");
        assertMethod(BarbotContainer.class, "getMaxVolume");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(BarbotContainer.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(BarbotContainer.class);

        assertThat(t.name()).isEqualTo("barbot_container");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testBarbot() {
        ManyToOne mto = createManyToOne(BarbotContainer.class, "barbot");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(BarbotContainer.class, "barbot");

        assertThat(jc.name()).isEqualTo("barbot_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();
    }

    @Test
    public void testIngredient() {
        ManyToOne mto = createManyToOne(BarbotContainer.class, "ingredient");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(BarbotContainer.class, "ingredient");

        assertThat(jc.name()).isEqualTo("ingredient_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();
    }

    @Test
    public void testNumber() {
        Column c = createColumn(BarbotContainer.class, "number");

        assertThat(c.name()).isEqualTo("number");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testCurrentVolume() {
        Column c = createColumn(BarbotContainer.class, "currentVolume");

        assertThat(c.name()).isEqualTo("current_volume");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testMaxVolume() {
        Column c = createColumn(BarbotContainer.class, "maxVolume");

        assertThat(c.name()).isEqualTo("max_volume");
        assertThat(c.nullable()).isFalse();
    }
}
