package barbot.database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Naveen on 3/27/17.
 */
@Entity
@Table(name = "barbot_container", schema = "barbotdb")
public class BarbotContainer extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "barbot_id", referencedColumnName = "id", nullable = false)
    private Barbot barbot;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id", nullable = false)
    private Ingredient ingredient;

    @Basic
    @Column(name = "number")
    private Integer number;

    @Basic
    @Column(name = "current_volume")
    private Integer currentVolume;

    @Basic
    @Column(name = "max_volume")
    private Integer maxVolume;

//    private Collection<BarbotPump> barbotPumpsById;

    public BarbotContainer() {

    }

    public BarbotContainer(Integer number, Integer maxVolume) {
        this.number = number;
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

//
//    @OneToMany(mappedBy = "barbotContainerByBarbotContainerId")
//    public Collection<BarbotPump> getBarbotPumpsById() {
//        return barbotPumpsById;
//    }
//
//    public void setBarbotPumpsById(Collection<BarbotPump> barbotPumpsById) {
//        this.barbotPumpsById = barbotPumpsById;
//    }
}
