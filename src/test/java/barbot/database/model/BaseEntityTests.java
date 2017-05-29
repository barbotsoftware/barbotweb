package barbot.database.model;

import static barbot.utils.AssertAnnotations.assertField;
import static barbot.utils.AssertAnnotations.assertMethod;
import static barbot.utils.AssertAnnotations.assertType;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.junit.Test;

/**
 * Created by Naveen on 5/28/17.
 */
public class BaseEntityTests extends EntityTests {

    @Test
    public void testTypeAnnotations() {
        assertType(BaseEntity.class, MappedSuperclass.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(BaseEntity.class, "id", Id.class, GeneratedValue.class, Column.class);
        assertField(BaseEntity.class, "createdAt", Column.class);
        assertField(BaseEntity.class, "updatedAt", Column.class);
        assertField(BaseEntity.class, "deletedAt", Column.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(BaseEntity.class, "getId");
        assertMethod(BaseEntity.class, "getCreatedAt");
        assertMethod(BaseEntity.class, "getUpdatedAt");
        assertMethod(BaseEntity.class, "getDeletedAt");
    }

    @Test
    public void testId() {
        GeneratedValue a = createGeneratedValue(BaseEntity.class, "id");

        assertThat(a.generator()).isEqualTo("");
        assertThat(a.strategy()).isEqualTo(GenerationType.AUTO);

        Column c = createColumn(BaseEntity.class, "id");

        assertThat(c.name()).isEqualTo("id");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testCreatedAt() {
        Column c = createColumn(BaseEntity.class, "createdAt");

        assertThat(c.name()).isEqualTo("created_at");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testUpdatedAt() {
        Column c = createColumn(BaseEntity.class, "updatedAt");

        assertThat(c.name()).isEqualTo("updated_at");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testDeletedAt() {
        Column c = createColumn(BaseEntity.class, "deletedAt");

        assertThat(c.name()).isEqualTo("deleted_at");
    }


}
