package barbot.database.model;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import barbot.utils.Constants;

/**
 * Created by Naveen on 3/27/17.
 */
@Entity
@Table(name = Constants.TABLE_BARBOT, schema = Constants.DB_SCHEMA)
public class Barbot extends BaseEntity {

    @Column(name = "uid", nullable = false)
    @JsonProperty("barbot_id")
    @JsonView(View.Request.class)
    private String uid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "barbot")
    private Set<BarbotContainer> barbotContainers;

    @OneToMany(mappedBy = "barbot")
    private Set<BarbotIoDevice> barbotIoDevices;

    @OneToMany(mappedBy = "barbot")
    private Set<DrinkOrder> drinkOrders;

    public Barbot() {

    }

    public Barbot(String uid) {
        this(uid, null);
    }

    public Barbot(String uid, String name) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<BarbotContainer> getBarbotContainers() {
        return barbotContainers;
    }

    public void setBarbotContainers(Set<BarbotContainer> barbotContainers) {
        this.barbotContainers = barbotContainers;
    }

    public Set<BarbotIoDevice> getBarbotIoDevices() {
        return barbotIoDevices;
    }

    public void setBarbotIoDevices(Set<BarbotIoDevice> barbotIoDevices) {
        this.barbotIoDevices = barbotIoDevices;
    }

    public Set<DrinkOrder> getDrinkOrders() {
        return drinkOrders;
    }

    public void setDrinkOrders(Set<DrinkOrder> drinkOrders) {
        this.drinkOrders = drinkOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Barbot barbot = (Barbot) o;

        if (id != null ? !id.equals(barbot.id) : barbot.id != null) return false;
        if (uid != null ? !uid.equals(barbot.uid) : barbot.uid != null) return false;
        if (name != null ? !name.equals(barbot.name) : barbot.name != null) return false;
        if (createdAt != null ? !createdAt.equals(barbot.createdAt) : barbot.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(barbot.updatedAt) : barbot.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(barbot.deletedAt) : barbot.deletedAt != null) return false;
        if (status != null ? !status.equals(barbot.status) : barbot.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
