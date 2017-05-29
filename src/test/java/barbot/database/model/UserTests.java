package barbot.database.model;

import static barbot.utils.AssertAnnotations.assertField;
import static barbot.utils.AssertAnnotations.assertMethod;
import static barbot.utils.AssertAnnotations.assertType;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.junit.Test;

/**
 * Created by Naveen on 5/29/17.
 */
public class UserTests extends EntityTests {
    @Test
    public void testTypeAnnotations() {
        assertType(User.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(User.class, "uid", Column.class);
        assertField(User.class, "name", Column.class);
        assertField(User.class, "email", Column.class);
        assertField(User.class, "password", Column.class);
        assertField(User.class, "rememberToken", Column.class);
        assertField(User.class, "drinkOrders", OneToMany.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(User.class, "getUid");
        assertMethod(User.class, "getName");
        assertMethod(User.class, "getEmail");
        assertMethod(User.class, "getPassword");
        assertMethod(User.class, "getRememberToken");
        assertMethod(User.class, "getDrinkOrders");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(User.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(User.class);

        assertThat(t.name()).isEqualTo("user");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testUid() {
        Column c = createColumn(User.class, "uid");

        assertThat(c.name()).isEqualTo("uid");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testName() {
        Column c = createColumn(User.class, "name");

        assertThat(c.name()).isEqualTo("name");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testEmail() {
        Column c = createColumn(User.class, "email");

        assertThat(c.name()).isEqualTo("email");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testPassword() {
        Column c = createColumn(User.class, "password");

        assertThat(c.name()).isEqualTo("password");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testRememberToken() {
        Column c = createColumn(User.class, "rememberToken");

        assertThat(c.name()).isEqualTo("remember_token");
    }

    @Test
    public void testDrinkOrders() {
        OneToMany otm = createOneToMany(User.class, "drinkOrders");

        assertThat(otm.mappedBy()).isEqualTo("user");
    }
}
