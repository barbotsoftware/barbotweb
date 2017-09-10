package barbot.database.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Alex on 9/10/2017.
 */
@Entity
@Table(name = "category")
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
}
