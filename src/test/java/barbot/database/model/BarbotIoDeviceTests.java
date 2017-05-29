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

/**
 * Created by Naveen on 5/28/17.
 */
public class BarbotIoDeviceTests extends EntityTests {

    @Test
    public void testTypeAnnotations() {
        assertType(BarbotIoDevice.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(BarbotIoDevice.class, "barbot", ManyToOne.class, JoinColumn.class);
        assertField(BarbotIoDevice.class, "barbotIoDeviceType", ManyToOne.class, JoinColumn.class);
        assertField(BarbotIoDevice.class, "gpioPort", Column.class);
        assertField(BarbotIoDevice.class, "name", Column.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(BarbotIoDevice.class, "getBarbot");
        assertMethod(BarbotIoDevice.class, "getBarbotIoDeviceType");
        assertMethod(BarbotIoDevice.class, "getGpioPort");
        assertMethod(BarbotIoDevice.class, "getName");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(BarbotIoDevice.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(BarbotIoDevice.class);

        assertThat(t.name()).isEqualTo("barbot_io_device");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testBarbot() {
        ManyToOne mto = createManyToOne(BarbotIoDevice.class, "barbot");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(BarbotIoDevice.class, "barbot");

        assertThat(jc.name()).isEqualTo("barbot_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();
    }

    @Test
    public void testBarbotIoDeviceType() {
        ManyToOne mto = createManyToOne(BarbotIoDevice.class, "barbotIoDeviceType");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(BarbotIoDevice.class, "barbotIoDeviceType");

        assertThat(jc.name()).isEqualTo("barbot_io_device_type_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();
    }

    @Test
    public void testGpioPort() {
        Column c = createColumn(BarbotIoDevice.class, "gpioPort");

        assertThat(c.name()).isEqualTo("gpio_port");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testName() {
        Column c = createColumn(BarbotIoDevice.class, "name");

        assertThat(c.name()).isEqualTo("name");
    }
}
