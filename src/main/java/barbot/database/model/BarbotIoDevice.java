package barbot.database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Naveen on 3/27/17.
 */
@Entity
@Table(name = "barbot_io_device", schema = "barbotdb")
public class BarbotIoDevice extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "barbot_id", referencedColumnName = "id", nullable = false)
    private Barbot barbot;

    @ManyToOne
    @JoinColumn(name = "barbot_io_device_type_id", referencedColumnName = "id", nullable = false)
    private BarbotIoDeviceType barbotIoDeviceType;

    @Basic
    @Column(name = "gpio_port")
    private Integer gpioPort;

    @Basic
    @Column(name = "name")
    private String name;

    public BarbotIoDevice() {

    }

    public BarbotIoDevice(Integer gpioPort, String name) {
        this.gpioPort = gpioPort;
        this.name = name;
    }

    public Barbot getBarbot() { return barbot; }

    public void setBarbot(Barbot barbot) { this.barbot = barbot; }

    public BarbotIoDeviceType getBarbotIoDeviceType() { return barbotIoDeviceType; }

    public void setBarbotIoDeviceType(BarbotIoDeviceType barbotIoDeviceType) { this.barbotIoDeviceType = barbotIoDeviceType; }

    public Integer getGpioPort() {
        return gpioPort;
    }

    public void setGpioPort(Integer gpioPort) {
        this.gpioPort = gpioPort;
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

        BarbotIoDevice that = (BarbotIoDevice) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (gpioPort != null ? !gpioPort.equals(that.gpioPort) : that.gpioPort != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (gpioPort != null ? gpioPort.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        return result;
    }
}
