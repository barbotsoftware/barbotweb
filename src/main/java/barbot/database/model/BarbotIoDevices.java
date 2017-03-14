package barbot.database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by naveen on 3/13/17.
 */
@Entity
@Table(name = "barbot_io_devices", schema = "barbot", catalog = "")
public class BarbotIoDevices {
    private int id;
    private int barbotId;
    private int gpioPort;
    private int barbotIoDeviceTypeId;
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "barbot_id")
    public int getBarbotId() {
        return barbotId;
    }

    public void setBarbotId(int barbotId) {
        this.barbotId = barbotId;
    }

    @Basic
    @Column(name = "gpio_port")
    public int getGpioPort() {
        return gpioPort;
    }

    public void setGpioPort(int gpioPort) {
        this.gpioPort = gpioPort;
    }

    @Basic
    @Column(name = "barbot_io_device_type_id")
    public int getBarbotIoDeviceTypeId() {
        return barbotIoDeviceTypeId;
    }

    public void setBarbotIoDeviceTypeId(int barbotIoDeviceTypeId) {
        this.barbotIoDeviceTypeId = barbotIoDeviceTypeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BarbotIoDevices that = (BarbotIoDevices) o;

        if (id != that.id) return false;
        if (barbotId != that.barbotId) return false;
        if (gpioPort != that.gpioPort) return false;
        if (barbotIoDeviceTypeId != that.barbotIoDeviceTypeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + barbotId;
        result = 31 * result + gpioPort;
        result = 31 * result + barbotIoDeviceTypeId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        return result;
    }
}
