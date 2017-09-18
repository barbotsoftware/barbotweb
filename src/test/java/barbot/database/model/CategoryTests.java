package barbot.database.model;

import static barbot.utils.AssertAnnotations.assertField;
import static barbot.utils.AssertAnnotations.assertMethod;
import static barbot.utils.AssertAnnotations.assertType;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class CategoryTests extends EntityTests {

    @Test
    public void testTypeAnnotations() {
        assertType(Category.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(Category.class, "uid", Column.class, JsonProperty.class, JsonView.class);
        assertField(Category.class, "parentCategory", ManyToOne.class, JoinColumn.class, JsonIgnore.class);
        assertField(Category.class, "name", Column.class, JsonView.class);
        assertField(Category.class, "categories", OneToMany.class, JsonProperty.class, JsonView.class,
                JsonManagedReference.class, JsonBackReference.class);
        assertField(Category.class, "recipes", ManyToMany.class, JsonView.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(Category.class, "getUid");
        assertMethod(Category.class, "getParentCategory");
        assertMethod(Category.class, "getName");
        assertMethod(Category.class, "getCategories");
        assertMethod(Category.class, "getRecipes");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(Category.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(Category.class);

        assertThat(t.name()).isEqualTo("category");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testUid() {
        Column c = createColumn(Category.class, "uid");

        assertThat(c.name()).isEqualTo("uid");

        JsonProperty jp = createJsonProperty(Category.class, "uid");

        assertThat(jp.value()).isEqualTo("category_id");

        JsonView jv = createJsonView(Category.class, "uid");

        assertThat(jv.value()).contains(View.Id.class);
    }

    @Test
    public void testParentCategory() {
        ManyToOne mto = createManyToOne(Category.class, "parentCategory");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(Category.class, "parentCategory");

        assertThat(jc.name()).isEqualTo("parent_category_id");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();

        JsonIgnore ji = createJsonIgnore(Category.class, "parentCategory");

        assertThat(ji).isNotNull();
    }

    @Test
    public void testName() {
        Column column = createColumn(Category.class, "name");

        assertThat(column.name()).isEqualTo("name");

        JsonView jsonView = createJsonView(Category.class, "name");

        assertThat(jsonView.value()).contains(View.Summary.class);
    }

    @Test
    public void testCategories() {
        OneToMany oneToMany = createOneToMany(Category.class, "categories");

        assertThat(oneToMany.mappedBy()).isEqualTo("parentCategory");
        assertThat(oneToMany.fetch()).isEqualTo(FetchType.EAGER);

        JsonProperty jsonProperty = createJsonProperty(Category.class, "categories");

        assertThat(jsonProperty.value()).isEqualTo("sub_categories");

        JsonView jsonView = createJsonView(Category.class, "name");

        assertThat(jsonView.value()).contains(View.Summary.class);

        JsonManagedReference jsonManagedReference = createJsonManagedReference(Category.class, "categories");

        assertThat(jsonManagedReference).isNotNull();

        JsonBackReference jsonBackReference = createJsonBackReference(Category.class, "categories");

        assertThat(jsonBackReference).isNotNull();
    }

    @Test
    public void testRecipes() {
        ManyToMany manyToMany = createManyToMany(Category.class, "recipes");

        assertThat(manyToMany.mappedBy()).isEqualTo("categories");
        assertThat(manyToMany.targetEntity()).isEqualTo(Recipe.class);

        JsonView jsonView = createJsonView(Category.class, "recipes");

        assertThat(jsonView.value()).contains(View.Detail.class);
    }
}
