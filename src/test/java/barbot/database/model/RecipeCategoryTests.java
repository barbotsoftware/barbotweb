package barbot.database.model;

import static barbot.utils.AssertAnnotations.assertField;
import static barbot.utils.AssertAnnotations.assertMethod;
import static barbot.utils.AssertAnnotations.assertType;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Naveen on 9/17/17.
 */
public class RecipeCategoryTests extends EntityTests {

    @Test
    public void testTypeAnnotations() {
        assertType(RecipeCategory.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(RecipeCategory.class, "recipe", ManyToOne.class, JoinColumn.class);
        assertField(RecipeCategory.class, "category", ManyToOne.class, JoinColumn.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(RecipeCategory.class, "getRecipe");
        assertMethod(RecipeCategory.class, "getCategory");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(RecipeCategory.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(RecipeCategory.class);

        assertThat(t.name()).isEqualTo("recipe_category");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testRecipe() {
        ManyToOne mto = createManyToOne(RecipeCategory.class, "recipe");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(RecipeCategory.class, "recipe");

        assertThat(jc.name()).isEqualTo("recipe_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();
    }

    @Test
    public void testCategory() {
        ManyToOne mto = createManyToOne(RecipeCategory.class, "category");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(RecipeCategory.class, "category");

        assertThat(jc.name()).isEqualTo("category_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();
    }
}
