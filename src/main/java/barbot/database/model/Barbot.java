package barbot.database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Naveen on 3/27/17.
 */
@Entity
@Table(name = "barbot", schema = "barbotdb")
public class Barbot extends BaseEntity {

    @Basic
    @Column(name = "uid")
    private String uid;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "status")
    private String status;

//    private Collection<BarbotContainer> barbotContainersById;
//    private Collection<BarbotIoDevice> barbotIoDevicesById;
//    private Collection<DrinkOrder> drinkOrdersById;

    public Barbot() {

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

//    @OneToMany(mappedBy = "barbotByBarbotId")
//    public Collection<BarbotContainer> getBarbotContainersById() {
//        return barbotContainersById;
//    }
//
//    public void setBarbotContainersById(Collection<BarbotContainer> barbotContainersById) {
//        this.barbotContainersById = barbotContainersById;
//    }
//
//    @OneToMany(mappedBy = "barbotByBarbotId")
//    public Collection<BarbotIoDevice> getBarbotIoDevicesById() {
//        return barbotIoDevicesById;
//    }
//
//    public void setBarbotIoDevicesById(Collection<BarbotIoDevice> barbotIoDevicesById) {
//        this.barbotIoDevicesById = barbotIoDevicesById;
//    }
//
//    @OneToMany(mappedBy = "barbotByBarbotId")
//    public Collection<DrinkOrder> getDrinkOrdersById() {
//        return drinkOrdersById;
//    }
//
//    public void setDrinkOrdersById(Collection<DrinkOrder> drinkOrdersById) {
//        this.drinkOrdersById = drinkOrdersById;
//    }
}
