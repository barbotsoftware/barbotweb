package barbot.database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Naveen on 3/26/17.
 */
@Entity
@Table(name = "recipe_ingredient", schema = "barbot", catalog = "")
public class RecipeIngredient {
    private int id;
    private int recipeId;
    private int ingredientId;
    private Recipes recipesByRecipeId;
    private Ingredients ingredientsByIngredientId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "recipe_id")
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Basic
    @Column(name = "ingredient_id")
    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeIngredient that = (RecipeIngredient) o;

        if (id != that.id) return false;
        if (recipeId != that.recipeId) return false;
        if (ingredientId != that.ingredientId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + recipeId;
        result = 31 * result + ingredientId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "id", nullable = false)
    public Recipes getRecipesByRecipeId() {
        return recipesByRecipeId;
    }

    public void setRecipesByRecipeId(Recipes recipesByRecipeId) {
        this.recipesByRecipeId = recipesByRecipeId;
    }

    @ManyToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id", nullable = false)
    public Ingredients getIngredientsByIngredientId() {
        return ingredientsByIngredientId;
    }

    public void setIngredientsByIngredientId(Ingredients ingredientsByIngredientId) {
        this.ingredientsByIngredientId = ingredientsByIngredientId;
    }
}
