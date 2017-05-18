package barbot.database.model;

import java.util.HashSet;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import barbot.utils.Constants;

/**
 * Created by Naveen on 3/27/17.
 */
@Entity
@Table(name = Constants.TABLE_RECIPE, schema = Constants.DB_SCHEMA)
public class Recipe extends BaseEntity {

    @Column(name = "uid", nullable = false)
    @JsonProperty("recipe_id")
    @JsonView(View.Id.class)
    private String uid;

    @Column(name = "name", nullable = false)
    @JsonView(View.Summary.class)
    private String name;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false)
    private User createdBy;

    @Column(name = "custom", nullable = false)
    private Boolean custom;

    @Column(name = "image_url", nullable = false)
    @JsonProperty("img")
    @JsonView(View.Summary.class)
    private String imageUrl;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER)
    @JsonView(View.Summary.class)
    @JsonProperty("ingredients")
    @JsonManagedReference
    private Set<RecipeIngredient> recipeIngredients = new HashSet<RecipeIngredient>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Ingredient.class)
    @JoinTable(name = Constants.TABLE_RECIPE_INGREDIENT, schema = Constants.DB_SCHEMA,
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    @JsonView(View.Detail.class)
    @JsonProperty("ingredient_list")
    private Set<Ingredient> ingredients;

    public Recipe() {

    }

    public Recipe(String uid) {
        this(uid, null, null, false, null, null);
    }

    public Recipe(String uid, String name, String imageUrl) {
        this(uid, name, null, false, imageUrl, null);
    }

    public Recipe(String uid, String name, String imageUrl, Set<Ingredient> ingredients) {
        this(uid, name, null, false, imageUrl, ingredients);
    }

    public Recipe(String uid, String name, User createdBy, boolean custom, String imageUrl, Set<Ingredient> ingredients) {
        this.uid = uid;
        this.name = name;
        this.createdBy = createdBy;
        this.custom = custom;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public boolean getCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        this.recipeIngredients.add(recipeIngredient);
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (id != null ? !id.equals(recipe.id) : recipe.id != null) return false;
        if (uid != null ? !uid.equals(recipe.uid) : recipe.uid != null) return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        if (createdBy != null ? !createdBy.equals(recipe.createdBy) : recipe.createdBy != null) return false;
        if (custom != null ? !custom.equals(recipe.custom) : recipe.custom != null) return false;
        if (imageUrl != null ? !imageUrl.equals(recipe.imageUrl) : recipe.imageUrl != null) return false;
        if (createdAt != null ? !createdAt.equals(recipe.createdAt) : recipe.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(recipe.updatedAt) : recipe.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(recipe.deletedAt) : recipe.deletedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (custom != null ? custom.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        return result;
    }
}
