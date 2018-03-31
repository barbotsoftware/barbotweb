package barbot.database.model;

import static barbot.utils.AssertAnnotations.assertField;
import static barbot.utils.AssertAnnotations.assertMethod;
import static barbot.utils.AssertAnnotations.assertType;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Naveen on 5/21/17.
 */
public class BarbotTests extends EntityTests {

    @Test
    public void testTypeAnnotations() {
        assertType(Barbot.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(Barbot.class, "uid", Column.class, JsonProperty.class, JsonView.class);
        assertField(Barbot.class, "name", Column.class);
        assertField(Barbot.class, "status", Column.class);
        assertField(Barbot.class, "password", Column.class, JsonIgnore.class);
        assertField(Barbot.class, "barbotContainers", OneToMany.class);
        assertField(Barbot.class, "drinkOrders", OneToMany.class);
        assertField(Barbot.class, "garnishes", OneToMany.class, JsonView.class, JsonProperty.class,
                JsonManagedReference.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(Barbot.class, "getUid");
        assertMethod(Barbot.class, "getName");
        assertMethod(Barbot.class, "getStatus");
        assertMethod(Barbot.class, "getPassword");
        assertMethod(Barbot.class, "getBarbotContainers");
        assertMethod(Barbot.class, "getDrinkOrders");
        assertMethod(Barbot.class, "getGarnishes");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(Barbot.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(Barbot.class);

        assertThat(t.name()).isEqualTo("barbot");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testUid() {
        Column c = createColumn(Barbot.class, "uid");

        assertThat(c.name()).isEqualTo("uid");
        assertThat(c.nullable()).isFalse();

        JsonProperty jp = createJsonProperty(Barbot.class, "uid");

        assertThat(jp.value()).isEqualTo("barbot_id");

        JsonView jv = createJsonView(Barbot.class, "uid");

        assertThat(jv.value()).contains(View.Request.class);
    }

    @Test
    public void testName() {
        Column c = createColumn(Barbot.class, "name");

        assertThat(c.name()).isEqualTo("name");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testStatus() {
        Column c = createColumn(Barbot.class, "status");

        assertThat(c.name()).isEqualTo("status");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testPassword() {
        Column c = createColumn(Barbot.class, "password");

        assertThat(c.name()).isEqualTo("password");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testBarbotContainers() {
        OneToMany otm = createOneToMany(Barbot.class, "barbotContainers");

        assertThat(otm.mappedBy()).isEqualTo("barbot");
    }

    @Test
    public void testDrinkOrders() {
        OneToMany otm = createOneToMany(Barbot.class, "drinkOrders");

        assertThat(otm.mappedBy()).isEqualTo("barbot");
    }

    @Test
    public void testGarnishes() {
        OneToMany otm = createOneToMany(Barbot.class, "garnishes");

        assertThat(otm.mappedBy()).isEqualTo("barbot");
        assertThat(otm.fetch()).isEqualTo(FetchType.EAGER);
        assertThat(otm.cascade()).contains(CascadeType.ALL);

        JsonView jv = createJsonView(Barbot.class, "garnishes");

        assertThat(jv.value()).contains(View.Summary.class);

        JsonProperty jp = createJsonProperty(Barbot.class, "garnishes");

        assertThat(jp.value()).isEqualTo("garnishes");

        JsonManagedReference jmr = createJsonManagedReference(Barbot.class, "garnishes");

        assertThat(jmr).isNotNull();
    }
}
