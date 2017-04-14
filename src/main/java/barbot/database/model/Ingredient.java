package barbot.database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import barbot.utils.Constants;

/**
 * Created by Naveen on 3/27/17.
 */
@Entity
@Table(name = Constants.TABLE_INGREDIENT, schema = Constants.DB_SCHEMA)
public class Ingredient extends BaseEntity {

    @Basic
    @Column(name = "uid")
    private String uid;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "abv")
    private Integer abv;

    @ManyToOne(optional = false)
    private Recipe recipe;

//    private Collection<BarbotContainer> barbotContainersById;
//    private Collection<RecipeIngredient> recipeIngredientsById;

    public Ingredient() {

    }

    public Ingredient(String uid, String name) {
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

    public Integer getAbv() {
        return abv;
    }

    public void setAbv(Integer abv) {
        this.abv = abv;
    }

    public Recipe getRecipe() { return recipe; }

    public void setRecipe(Recipe recipe) { this.recipe = recipe; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (abv != null ? !abv.equals(that.abv) : that.abv != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (abv != null ? abv.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "ingredientByIngredientId")
//    public Collection<BarbotContainer> getBarbotContainersById() {
//        return barbotContainersById;
//    }
//
//    public void setBarbotContainersById(Collection<BarbotContainer> barbotContainersById) {
//        this.barbotContainersById = barbotContainersById;
//    }
//
//    @OneToMany(mappedBy = "ingredientByIngredientId")
//    public Collection<RecipeIngredient> getRecipeIngredientsById() {
//        return recipeIngredientsById;
//    }
//
//    public void setRecipeIngredientsById(Collection<RecipeIngredient> recipeIngredientsById) {
//        this.recipeIngredientsById = recipeIngredientsById;
//    }
}
