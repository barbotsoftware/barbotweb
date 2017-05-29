package barbot.database.model;

import static barbot.utils.AssertAnnotations.assertField;
import static barbot.utils.AssertAnnotations.assertMethod;
import static barbot.utils.AssertAnnotations.assertType;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.junit.Test;

/**
 * Created by Naveen on 5/29/17.
 */
public class BarbotPumpTests extends EntityTests {
    @Test
    public void testTypeAnnotations() {
        assertType(BarbotPump.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(BarbotPump.class, "barbotIoDevice", ManyToOne.class, JoinColumn.class);
        assertField(BarbotPump.class, "barbotContainer", ManyToOne.class, JoinColumn.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(BarbotPump.class, "getBarbotIoDevice");
        assertMethod(BarbotPump.class, "getBarbotContainer");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(BarbotPump.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(BarbotPump.class);

        assertThat(t.name()).isEqualTo("barbot_pump");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testBarbotIoDevice() {
        ManyToOne mto = createManyToOne(BarbotPump.class, "barbotIoDevice");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(BarbotPump.class, "barbotIoDevice");

        assertThat(jc.name()).isEqualTo("barbot_io_device_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();
    }

    @Test
    public void testBarbotContainer() {
        ManyToOne mto = createManyToOne(BarbotPump.class, "barbotContainer");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(BarbotPump.class, "barbotContainer");

        assertThat(jc.name()).isEqualTo("barbot_container_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
    }
}
