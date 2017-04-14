package barbot.database.model;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import barbot.utils.Constants;

/**
 * Created by Naveen on 3/27/17.
 */
@Entity
@Table(name = Constants.TABLE_RECIPE, schema = Constants.DB_SCHEMA)
public class Recipe extends BaseEntity {

    @Basic
    @Column(name = "uid")
    private String uid;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "created_by")
    private Integer createdBy;

    @Basic
    @Column(name = "custom")
    private Integer custom;

    @Basic
    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Ingredient> ingredients;

    public Recipe() {

    }

    public Recipe(String uid, String name) {
        this.uid = uid;
        this.name = name;
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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getCustom() {
        return custom;
    }

    public void setCustom(Integer custom) {
        this.custom = custom;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

//    @OneToMany(mappedBy = "recipeByRecipeId")
//    public Collection<DrinkOrder> getDrinkOrdersById() {
//        return drinkOrdersById;
//    }
//
//    public void setDrinkOrdersById(Collection<DrinkOrder> drinkOrdersById) {
//        this.drinkOrdersById = drinkOrdersById;
//    }
//
//    @OneToMany(mappedBy = "recipeByRecipeId")
//    public Collection<RecipeIngredient> getRecipeIngredientsById() {
//        return recipeIngredientsById;
//    }
//
//    public void setRecipeIngredientsById(Collection<RecipeIngredient> recipeIngredientsById) {
//        this.recipeIngredientsById = recipeIngredientsById;
//    }
}
