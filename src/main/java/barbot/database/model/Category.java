package barbot.database.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import barbot.utils.Constants;

/**
 * Created by Alex on 9/10/2017.
 */
@Entity
@Table(name = Constants.TABLE_CATEGORY, schema = Constants.DB_SCHEMA)
public class Category extends BaseEntity {

    @Column(name = "uid", nullable = false)
    @JsonProperty("category_id")
    @JsonView(View.Id.class)
    private String uid;

    @ManyToOne
    @JoinColumn(name = "parent_category_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Category parentCategory;

    @Column(name = "name", nullable = false)
    @JsonView(View.Summary.class)
    private String name;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.EAGER)
    @JsonProperty("sub_categories")
    @JsonView(View.Summary.class)
    @JsonManagedReference
    @JsonBackReference
    private Set<Category> categories;

    @ManyToMany(mappedBy = "categories", targetEntity = Recipe.class)
    @JsonView(View.Detail.class)
    private Set<Recipe> recipes;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != null ? !id.equals(category.id) : category.id != null) return false;
        if (uid != null ? !uid.equals(category.uid) : category.uid != null) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        if (createdAt != null ? !createdAt.equals(category.createdAt) : category.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(category.updatedAt) : category.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(category.deletedAt) : category.deletedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        return result;
    }
}
