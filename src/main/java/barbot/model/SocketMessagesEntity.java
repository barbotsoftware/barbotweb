package barbot.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Created by naveen on 3/12/17.
 */
@Entity
@Table(name = "socket_messages", schema = "barbot", catalog = "")
public class SocketMessagesEntity {
    private int id;
    private int barbotId;
    private byte[] message;
    private byte received;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private String uid;

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
    @Column(name = "message")
    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    @Basic
    @Column(name = "received")
    public byte getReceived() {
        return received;
    }

    public void setReceived(byte received) {
        this.received = received;
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

    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocketMessagesEntity that = (SocketMessagesEntity) o;

        if (id != that.id) return false;
        if (barbotId != that.barbotId) return false;
        if (received != that.received) return false;
        if (!Arrays.equals(message, that.message)) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + barbotId;
        result = 31 * result + Arrays.hashCode(message);
        result = 31 * result + (int) received;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        return result;
    }
}
