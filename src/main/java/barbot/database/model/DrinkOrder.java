package barbot.database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Naveen on 3/27/17.
 */
@Entity
@Table(name = "drink_order", schema = "barbotdb")
public class DrinkOrder extends BaseEntity {

    @Basic
    @Column(name = "uid")
    private String uid;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "id", nullable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "barbot_id", referencedColumnName = "id", nullable = false)
    private Barbot barbot;

    @Basic
    @Column(name = "ice")
    private Integer ice;

    @Basic
    @Column(name = "garnish")
    private Integer garnish;

    public DrinkOrder() {

    }

    public DrinkOrder(String uid, User user, Recipe recipe, Barbot barbot) {
        this.uid = uid;
        this.user = user;
        this.recipe = recipe;
        this.barbot = barbot;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getIce() {
        return ice;
    }

    public void setIce(Integer ice) {
        this.ice = ice;
    }

    public Integer getGarnish() {
        return garnish;
    }

    public void setGarnish(Integer garnish) {
        this.garnish = garnish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrinkOrder that = (DrinkOrder) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (ice != null ? !ice.equals(that.ice) : that.ice != null) return false;
        if (garnish != null ? !garnish.equals(that.garnish) : that.garnish != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (ice != null ? ice.hashCode() : 0);
        result = 31 * result + (garnish != null ? garnish.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        return result;
    }
}
