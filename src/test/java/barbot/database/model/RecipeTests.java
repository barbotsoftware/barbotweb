package barbot.database.model;

import static barbot.utils.AssertAnnotations.assertField;
import static barbot.utils.AssertAnnotations.assertMethod;
import static barbot.utils.AssertAnnotations.assertType;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Naveen on 5/29/17.
 */
public class RecipeTests extends EntityTests {

    @Test
    public void testTypeAnnotations() {
        assertType(Recipe.class, Entity.class, Table.class);
    }

    @Test
    public void testFieldAnnotations() {
        assertField(Recipe.class, "uid", Column.class, JsonProperty.class, JsonView.class);
        assertField(Recipe.class, "name", Column.class, JsonView.class);
        assertField(Recipe.class, "createdBy", ManyToOne.class, JoinColumn.class);
        assertField(Recipe.class, "custom", Column.class);
        assertField(Recipe.class, "imageUrl", Column.class, JsonProperty.class, JsonView.class);
        assertField(Recipe.class, "recipeIngredients", OneToMany.class, JsonView.class, JsonProperty.class,
                JsonManagedReference.class);
        assertField(Recipe.class, "ingredients", ManyToMany.class, JoinTable.class, JsonView.class,
                JsonProperty.class);
        assertField(Recipe.class, "categories", ManyToMany.class, JoinTable.class, JsonView.class,
                JsonProperty.class);
    }

    @Test
    public void testMethodAnnotations() {
        assertMethod(Recipe.class, "getUid");
        assertMethod(Recipe.class, "getName");
        assertMethod(Recipe.class, "getCreatedBy");
        assertMethod(Recipe.class, "getCustom");
        assertMethod(Recipe.class, "getImageUrl");
        assertMethod(Recipe.class, "getRecipeIngredients");
        assertMethod(Recipe.class, "getIngredients");
        assertMethod(Recipe.class, "getCategories");
    }

    @Test
    public void testEntity() {
        Entity a = createEntity(Recipe.class);

        assertThat(a).isNotNull();
    }

    @Test
    public void testTable() {
        Table t = createTable(Recipe.class);

        assertThat(t.name()).isEqualTo("recipe");
        assertThat(t.schema()).isEqualTo("barbotdb");
    }

    @Test
    public void testUid() {
        Column c = createColumn(Recipe.class, "uid");

        assertThat(c.name()).isEqualTo("uid");
        assertThat(c.nullable()).isFalse();

        JsonProperty jp = createJsonProperty(Recipe.class, "uid");

        assertThat(jp.value()).isEqualTo("recipe_id");

        JsonView jv = createJsonView(Recipe.class, "uid");

        assertThat(jv.value()).contains(View.Id.class);
    }

    @Test
    public void testName() {
        Column c = createColumn(Recipe.class, "name");

        assertThat(c.name()).isEqualTo("name");
        assertThat(c.nullable()).isFalse();

        JsonView jv = createJsonView(Recipe.class, "name");

        assertThat(jv.value()).contains(View.Summary.class);
    }

    @Test
    public void testCreatedBy() {
        ManyToOne mto = createManyToOne(Recipe.class, "createdBy");

        assertThat(mto).isNotNull();

        JoinColumn jc = createJoinColumn(Recipe.class, "createdBy");

        assertThat(jc.name()).isEqualTo("created_by");
        assertThat(jc.referencedColumnName()).isEqualTo("id");
        assertThat(jc.nullable()).isFalse();
    }

    @Test
    public void testCustom() {
        Column c = createColumn(Recipe.class, "custom");

        assertThat(c.name()).isEqualTo("custom");
        assertThat(c.nullable()).isFalse();
    }

    @Test
    public void testImageUrl() {
        Column c = createColumn(Recipe.class, "imageUrl");

        assertThat(c.name()).isEqualTo("image_url");
        assertThat(c.nullable()).isFalse();

        JsonProperty jp = createJsonProperty(Recipe.class, "imageUrl");

        assertThat(jp.value()).isEqualTo("img");

        JsonView jv = createJsonView(Recipe.class, "imageUrl");

        assertThat(jv.value()).contains(View.Summary.class);
    }

    @Test
    public void testRecipeIngredients() {
        OneToMany otm = createOneToMany(Recipe.class, "recipeIngredients");

        assertThat(otm.mappedBy()).isEqualTo("recipe");
        assertThat(otm.fetch()).isEqualTo(FetchType.EAGER);
        assertThat(otm.cascade()).contains(CascadeType.ALL);

        JsonProperty jp = createJsonProperty(Recipe.class, "recipeIngredients");

        assertThat(jp.value()).isEqualTo("ingredients");

        JsonView jv = createJsonView(Recipe.class, "recipeIngredients");

        assertThat(jv.value()).contains(View.Summary.class);

        JsonManagedReference jmr = createJsonManagedReference(Recipe.class, "recipeIngredients");

        assertThat(jmr).isNotNull();
    }

    @Test
    public void testIngredients() {
        ManyToMany mtm = createManyToMany(Recipe.class, "ingredients");

        assertThat(mtm.fetch()).isEqualTo(FetchType.LAZY);
        assertThat(mtm.cascade()).contains(CascadeType.ALL);
        assertThat(mtm.targetEntity()).isEqualTo(Ingredient.class);

        JoinTable jt = createJoinTable(Recipe.class, "ingredients");

        JoinColumn joinColumn = createJoinColumn(RecipeIngredient.class, "recipe");
        JoinColumn inverseJoinColumn = createJoinColumn(RecipeIngredient.class, "ingredient");

        assertThat(jt.name()).isEqualTo("recipe_ingredient");
        assertThat(jt.schema()).isEqualTo("barbotdb");
        assertThat(jt.joinColumns()).contains(joinColumn);
        assertThat(jt.inverseJoinColumns()).contains(inverseJoinColumn);

        JsonView jv = createJsonView(Recipe.class, "ingredients");

        assertThat(jv.value()).contains(View.Detail.class);

        JsonProperty jp = createJsonProperty(Recipe.class, "ingredients");

        assertThat(jp.value()).isEqualTo("ingredient_list");
    }

    @Test
    public void testCategories() {
        ManyToMany mtm = createManyToMany(Recipe.class, "categories");

        assertThat(mtm.fetch()).isEqualTo(FetchType.LAZY);
        assertThat(mtm.cascade()).contains(CascadeType.ALL);
        assertThat(mtm.targetEntity()).isEqualTo(Category.class);

        JoinTable jt = createJoinTable(Recipe.class, "categories");

        JoinColumn joinColumn = createJoinColumn(RecipeCategory.class, "recipe");
        JoinColumn inverseJoinColumn = createJoinColumn(RecipeCategory.class, "category");

        assertThat(jt.name()).isEqualTo("recipe_category");
        assertThat(jt.schema()).isEqualTo("barbotdb");
        assertThat(jt.joinColumns()).contains(joinColumn);
        assertThat(jt.inverseJoinColumns()).contains(inverseJoinColumn);

        JsonView jv = createJsonView(Recipe.class, "categories");

        assertThat(jv.value()).contains(View.Detail.class);

        JsonProperty jp = createJsonProperty(Recipe.class, "categories");

        assertThat(jp.value()).isEqualTo("category_list");
    }
}
