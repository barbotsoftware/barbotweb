package barbot.database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import barbot.utils.Constants;

/**
 * Created by Naveen on 3/27/17.
 */
@Entity
@Table(name = Constants.TABLE_BARBOT_IO_DEVICE_TYPE, schema = Constants.DB_SCHEMA)
public class BarbotIoDeviceType extends BaseEntity {

    @Basic
    @Column(name = "name")
    private String name;
//    private Collection<BarbotIoDevice> barbotIoDevicesById;

    public BarbotIoDeviceType() {

    }

    public BarbotIoDeviceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BarbotIoDeviceType that = (BarbotIoDeviceType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "barbotIoDeviceTypeByBarbotIoDeviceTypeId")
//    public Collection<BarbotIoDevice> getBarbotIoDevicesById() {
//        return barbotIoDevicesById;
//    }
//
//    public void setBarbotIoDevicesById(Collection<BarbotIoDevice> barbotIoDevicesById) {
//        this.barbotIoDevicesById = barbotIoDevicesById;
//    }
}
