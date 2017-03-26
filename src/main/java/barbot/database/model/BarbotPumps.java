package barbot.database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by naveen on 3/13/17.
 */
@Entity
@Table(name = "barbot_pumps", schema = "barbot", catalog = "")
public class BarbotPumps {
    private int id;
    private int barbotIoDeviceId;
    private Integer barbotContainerId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private BarbotIoDevices barbotIoDevicesByBarbotIoDeviceId;
    private BarbotContainers barbotContainersByBarbotContainerId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "barbot_io_device_id")
    public int getBarbotIoDeviceId() {
        return barbotIoDeviceId;
    }

    public void setBarbotIoDeviceId(int barbotIoDeviceId) {
        this.barbotIoDeviceId = barbotIoDeviceId;
    }

    @Basic
    @Column(name = "barbot_container_id")
    public Integer getBarbotContainerId() {
        return barbotContainerId;
    }

    public void setBarbotContainerId(Integer barbotContainerId) {
        this.barbotContainerId = barbotContainerId;
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

        BarbotPumps that = (BarbotPumps) o;

        if (id != that.id) return false;
        if (barbotIoDeviceId != that.barbotIoDeviceId) return false;
        if (barbotContainerId != null ? !barbotContainerId.equals(that.barbotContainerId) : that.barbotContainerId != null)
            return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + barbotIoDeviceId;
        result = 31 * result + (barbotContainerId != null ? barbotContainerId.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "barbot_io_device_id", referencedColumnName = "id", nullable = false)
    public BarbotIoDevices getBarbotIoDevicesByBarbotIoDeviceId() {
        return barbotIoDevicesByBarbotIoDeviceId;
    }

    public void setBarbotIoDevicesByBarbotIoDeviceId(BarbotIoDevices barbotIoDevicesByBarbotIoDeviceId) {
        this.barbotIoDevicesByBarbotIoDeviceId = barbotIoDevicesByBarbotIoDeviceId;
    }

    @ManyToOne
    @JoinColumn(name = "barbot_container_id", referencedColumnName = "id")
    public BarbotContainers getBarbotContainersByBarbotContainerId() {
        return barbotContainersByBarbotContainerId;
    }

    public void setBarbotContainersByBarbotContainerId(BarbotContainers barbotContainersByBarbotContainerId) {
        this.barbotContainersByBarbotContainerId = barbotContainersByBarbotContainerId;
    }
}
