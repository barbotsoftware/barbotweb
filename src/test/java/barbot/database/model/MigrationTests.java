package barbot.database.model;

import static barbot.utils.AssertAnnotations.assertField;
import static barbot.utils.AssertAnnotations.assertMethod;
import static barbot.utils.AssertAnnotations.assertType;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.junit.Test;

/**
 * Created by Naveen on 5/29/17.
 */
public class MigrationTests extends EntityTests {

    @Test
    public void testTypeAnnotations() {
        assertType(Migration.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(Migration.class, "id", Id.class, GeneratedValue.class, Column.class);
        assertField(Migration.class, "migration", Column.class);
        assertField(Migration.class, "version", Column.class);
        assertField(Migration.class, "createdAt", Column.class);
        assertField(Migration.class, "runAt", Column.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(Migration.class, "getId");
        assertMethod(Migration.class, "getMigration");
        assertMethod(Migration.class, "getVersion");
        assertMethod(Migration.class, "getCreatedAt");
        assertMethod(Migration.class, "getRunAt");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(Migration.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(Migration.class);

        assertThat(t.name()).isEqualTo("migration");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testId() {
        GeneratedValue a = createGeneratedValue(Migration.class, "id");

        assertThat(a.generator()).isEqualTo("");
        assertThat(a.strategy()).isEqualTo(GenerationType.AUTO);

        Column c = createColumn(Migration.class, "id");

        assertThat(c.name()).isEqualTo("id");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testMigration() {
        Column c = createColumn(Migration.class, "migration");

        assertThat(c.name()).isEqualTo("migration");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testVersion() {
        Column c = createColumn(Migration.class, "version");

        assertThat(c.name()).isEqualTo("version");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testCreatedAt() {
        Column c = createColumn(Migration.class, "createdAt");

        assertThat(c.name()).isEqualTo("created_at");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testRunAt() {
        Column c = createColumn(Migration.class, "runAt");

        assertThat(c.name()).isEqualTo("run_at");
        assertThat(c.nullable()).isFalse();
    }
}
