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
public class BarbotIoDeviceTypeTests extends EntityTests {

    @Test
    public void testTypeAnnotations() {
        assertType(BarbotIoDeviceType.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(BarbotIoDeviceType.class, "name", Column.class);
        assertField(BarbotIoDeviceType.class, "barbotIoDevices", OneToMany.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(BarbotIoDeviceType.class, "getName");
        assertMethod(BarbotIoDeviceType.class, "getBarbotIoDevices");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(BarbotIoDeviceType.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(BarbotIoDeviceType.class);

        assertThat(t.name()).isEqualTo("barbot_io_device_type");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testName() {
        Column c = createColumn(BarbotIoDeviceType.class, "name");

        assertThat(c.name()).isEqualTo("name");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testBarbotIoDevices() {
        OneToMany otm = createOneToMany(BarbotIoDeviceType.class, "barbotIoDevices");

        assertThat(otm.mappedBy()).isEqualTo("barbotIoDeviceType");
    }
}
