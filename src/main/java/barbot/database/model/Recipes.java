package barbot.database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by naveen on 3/13/17.
 */
@Entity
public class Recipes {
    private int id;
    private String name;
    private String uid;
    private int custom;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private String imageUrl;
    private int createdBy;
    private Collection<DrinkOrders> drinkOrderssById;
    private Collection<RecipeIngredient> recipeIngredientsById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "custom")
    public int getCustom() {
        return custom;
    }

    public void setCustom(int custom) {
        this.custom = custom;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "deleted_at")
    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Basic
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "created_by")
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipes recipes = (Recipes) o;

        if (id != recipes.id) return false;
        if (custom != recipes.custom) return false;
        if (createdBy != recipes.createdBy) return false;
        if (name != null ? !name.equals(recipes.name) : recipes.name != null) return false;
        if (uid != null ? !uid.equals(recipes.uid) : recipes.uid != null) return false;
        if (createdAt != null ? !createdAt.equals(recipes.createdAt) : recipes.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(recipes.updatedAt) : recipes.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(recipes.deletedAt) : recipes.deletedAt != null) return false;
        if (imageUrl != null ? !imageUrl.equals(recipes.imageUrl) : recipes.imageUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + custom;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + createdBy;
        return result;
    }

    @OneToMany(mappedBy = "recipesByRecipeId")
    public Collection<DrinkOrders> getDrinkOrderssById() {
        return drinkOrderssById;
    }

    public void setDrinkOrderssById(Collection<DrinkOrders> drinkOrderssById) {
        this.drinkOrderssById = drinkOrderssById;
    }

    @OneToMany(mappedBy = "recipesByRecipeId")
    public Collection<RecipeIngredient> getRecipeIngredientsById() {
        return recipeIngredientsById;
    }

    public void setRecipeIngredientsById(Collection<RecipeIngredient> recipeIngredientsById) {
        this.recipeIngredientsById = recipeIngredientsById;
    }
}
