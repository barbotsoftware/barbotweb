package barbot.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import barbot.utils.Constants;

/**
 * Created by Naveen on 3/27/17.
 */
@Entity
@Table(name = Constants.TABLE_BARBOT_CONTAINER, schema = Constants.DB_SCHEMA)
public class BarbotContainer extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "barbot_id", referencedColumnName = "id", nullable = false)
    private Barbot barbot;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id", nullable = false)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="ingredient_id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("ingredient_id")
    @JsonView(View.Summary.class)
    private Ingredient ingredient;

    @Column(name = "number", nullable = false)
    @JsonView(View.Summary.class)
    private Integer number;

    @Column(name = "current_volume", nullable = false)
    @JsonProperty("current_volume")
    @JsonView(View.Summary.class)
    private Integer currentVolume;

    @Column(name = "max_volume", nullable = false)
    @JsonProperty("max_volume")
    @JsonView(View.Summary.class)
    private Integer maxVolume;

    public BarbotContainer() {

    }

    public BarbotContainer(Integer number, Integer maxVolume) {
        this(null, null, number, null, maxVolume);
    }

    public BarbotContainer(Barbot barbot, Ingredient ingredient, Integer number, Integer currentVolume, Integer maxVolume) {
        this.barbot = barbot;
        this.ingredient = ingredient;
        this.number = number;
        this.currentVolume = currentVolume;
        this.maxVolume = maxVolume;
    }

    public Barbot getBarbot() {
        return barbot;
    }

    public void setBarbot(Barbot barbot) {
        this.barbot = barbot;
    }

    public Ingredient getIngredient() { return ingredient; }

    public void setIngredient(Ingredient ingredient) { this.ingredient = ingredient; }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentVolume(Integer currentVolume) {
        this.currentVolume = currentVolume;
    }

    public Integer getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(Integer maxVolume) {
        this.maxVolume = maxVolume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BarbotContainer that = (BarbotContainer) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (currentVolume != null ? !currentVolume.equals(that.currentVolume) : that.currentVolume != null)
            return false;
        if (maxVolume != null ? !maxVolume.equals(that.maxVolume) : that.maxVolume != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (currentVolume != null ? currentVolume.hashCode() : 0);
        result = 31 * result + (maxVolume != null ? maxVolume.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        return result;
    }
}
