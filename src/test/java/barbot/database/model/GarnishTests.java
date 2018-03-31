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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Naveen on 1/7/18.
 */
public class GarnishTests extends EntityTests {
    @Test
    public void testTypeAnnotations() {
        assertType(Garnish.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(Garnish.class, "uid", Column.class, JsonProperty.class, JsonView.class);
        assertField(Garnish.class, "name", Column.class, JsonProperty.class, JsonView.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(Garnish.class, "getUid");
        assertMethod(Garnish.class, "getName");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(Garnish.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(Garnish.class);

        assertThat(t.name()).isEqualTo("garnish");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testUid() {
        Column c = createColumn(Garnish.class, "uid");

        assertThat(c.name()).isEqualTo("uid");
        assertThat(c.nullable()).isFalse();

        JsonProperty jp = createJsonProperty(Garnish.class, "uid");

        assertThat(jp.value()).isEqualTo("garnish_id");

        JsonView jv = createJsonView(Garnish.class, "uid");

        assertThat(jv.value()).contains(View.Id.class);
    }

    @Test
    public void testName() {
        Column c = createColumn(Garnish.class, "name");

        assertThat(c.name()).isEqualTo("name");
        assertThat(c.nullable()).isFalse();

        JsonProperty jp = createJsonProperty(Garnish.class, "name");

        assertThat(jp.value()).isEqualTo("name");

        JsonView jv = createJsonView(Garnish.class, "name");

        assertThat(jv.value()).contains(View.Summary.class);
    }
}
