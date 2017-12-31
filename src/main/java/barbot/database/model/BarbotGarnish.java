package barbot.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import barbot.utils.Constants;

/**
 * Created by Naveen on 12/30/17.
 */
@Entity
@Table(name = Constants.TABLE_BARBOT_GARNISH, schema = Constants.DB_SCHEMA)
public class BarbotGarnish extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "barbot_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Barbot barbot;

    @ManyToOne
    @JoinColumn(name = "garnish_id", referencedColumnName = "id", nullable = false)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="name")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("name")
    @JsonView(View.Summary.class)
    private Garnish garnish;

    @Column(name = "option_number")
    @JsonProperty("option_number")
    @JsonView(View.Summary.class)
    private Integer optionNumber;

    @Column(name = "quantity")
    @JsonView(View.Summary.class)
    private Integer quantity;

    public BarbotGarnish() {

    }

    public BarbotGarnish(Barbot barbot, Garnish garnish, Integer optionNumber, Integer quantity) {
        this.barbot = barbot;
        this.garnish = garnish;
        this.optionNumber = optionNumber;
        this.quantity = quantity;
    }

    public Integer getOptionNumber() {
        return optionNumber;
    }

    public void setOptionNumber(Integer optionNumber) {
        this.optionNumber = optionNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BarbotGarnish that = (BarbotGarnish) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (optionNumber != null ? !optionNumber.equals(that.optionNumber) : that.optionNumber != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (optionNumber != null ? optionNumber.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
