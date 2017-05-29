package barbot.database.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import barbot.utils.Constants;

/**
 * Created by Naveen on 3/27/17.
 */
@Entity
@Table(name = Constants.TABLE_BARBOT_PUMP, schema = Constants.DB_SCHEMA)
public class BarbotPump extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "barbot_io_device_id", referencedColumnName = "id", nullable = false)
    private BarbotIoDevice barbotIoDevice;

    @ManyToOne
    @JoinColumn(name = "barbot_container_id", referencedColumnName = "id")
    private BarbotContainer barbotContainer;

    public BarbotPump() {

    }

    public BarbotPump(BarbotIoDevice barbotIoDevice, BarbotContainer barbotContainer) {
        this.barbotIoDevice = barbotIoDevice;
        this.barbotContainer = barbotContainer;
    }

    public BarbotIoDevice getBarbotIoDevice() {
        return barbotIoDevice;
    }

    public void setBarbotIoDevice(BarbotIoDevice barbotIoDevice) {
        this.barbotIoDevice = barbotIoDevice;
    }

    public BarbotContainer getBarbotContainer() {
        return barbotContainer;
    }

    public void setBarbotContainer(BarbotContainer barbotContainer) {
        this.barbotContainer = barbotContainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BarbotPump that = (BarbotPump) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        return result;
    }
}
