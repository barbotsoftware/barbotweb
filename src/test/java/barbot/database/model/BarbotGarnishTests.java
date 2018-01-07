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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Created by Naveen on 1/7/18.
 */
public class BarbotGarnishTests extends EntityTests {

    @Test
    public void testTypeAnnotations() {
        assertType(BarbotGarnish.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(BarbotGarnish.class, "barbot", ManyToOne.class, JoinColumn.class, JsonBackReference.class);
        assertField(BarbotGarnish.class, "garnish", ManyToOne.class, JoinColumn.class, JsonProperty.class,
                JsonView.class, JsonIdentityInfo.class, JsonIdentityReference.class);
        assertField(BarbotGarnish.class, "optionNumber", Column.class, JsonProperty.class, JsonView.class);
        assertField(BarbotGarnish.class, "quantity", Column.class, JsonView.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(BarbotGarnish.class, "getBarbot");
        assertMethod(BarbotGarnish.class, "getGarnish");
        assertMethod(BarbotGarnish.class, "getOptionNumber");
        assertMethod(BarbotGarnish.class, "getQuantity");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(BarbotGarnish.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(BarbotGarnish.class);

        assertThat(t.name()).isEqualTo("barbot_garnish");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testBarbot() {
        ManyToOne mto = createManyToOne(BarbotGarnish.class, "barbot");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(BarbotGarnish.class, "barbot");

        assertThat(jc.name()).isEqualTo("barbot_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();

        JsonBackReference jbr = createJsonBackReference(BarbotGarnish.class, "barbot");

        assertThat(jbr).isNotNull();
    }

    @Test
    public void testGarnish() {
        ManyToOne mto = createManyToOne(BarbotGarnish.class, "garnish");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(BarbotGarnish.class, "garnish");

        assertThat(jc.name()).isEqualTo("garnish_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();

        JsonIdentityInfo jii = createJsonIdentityInfo(BarbotGarnish.class, "garnish");

        assertThat(jii.generator()).isEqualTo(ObjectIdGenerators.PropertyGenerator.class);
        assertThat(jii.property()).isEqualTo("name");

        JsonIdentityReference jir = createJsonIdentityReference(BarbotGarnish.class, "garnish");

        assertThat(jir.alwaysAsId()).isTrue();

        JsonProperty jp = createJsonProperty(BarbotGarnish.class, "garnish");

        assertThat(jp.value()).isEqualTo("name");

        JsonView jv = createJsonView(BarbotGarnish.class, "garnish");

        assertThat(jv.value()).contains(View.Summary.class);
    }

    @Test
    public void testOptionNumber() {
        Column c = createColumn(BarbotGarnish.class, "optionNumber");

        assertThat(c.name()).isEqualTo("option_number");

        JsonProperty jp = createJsonProperty(BarbotGarnish.class, "optionNumber");

        assertThat(jp.value()).isEqualTo("option_number");

        JsonView jv = createJsonView(BarbotGarnish.class, "optionNumber");

        assertThat(jv.value()).contains(View.Summary.class);
    }

    @Test
    public void testQuantity() {
        Column c = createColumn(BarbotGarnish.class, "quantity");

        assertThat(c.name()).isEqualTo("quantity");

        JsonView jv = createJsonView(BarbotGarnish.class, "quantity");

        assertThat(jv.value()).contains(View.Summary.class);
    }
}
