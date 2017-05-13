package barbot.database.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import barbot.utils.Constants;

/**
 * Created by Naveen on 3/27/17.
 */
@Entity
@Table(name = Constants.TABLE_MIGRATION, schema = Constants.DB_SCHEMA)
public class Migration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "migration", nullable = false)
    private String migration;

    @Column(name = "version", nullable = false)
    private String version;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "run_at", nullable = false)
    private Timestamp runAt;

    public Migration() {

    }

    public Migration(String migration, String version) {
        this.migration = migration;
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMigration() {
        return migration;
    }

    public void setMigration(String migration) {
        this.migration = migration;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getRunAt() {
        return runAt;
    }

    public void setRunAt(Timestamp runAt) {
        this.runAt = runAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Migration migration1 = (Migration) o;

        if (id != null ? !id.equals(migration1.id) : migration1.id != null) return false;
        if (migration != null ? !migration.equals(migration1.migration) : migration1.migration != null) return false;
        if (version != null ? !version.equals(migration1.version) : migration1.version != null) return false;
        if (createdAt != null ? !createdAt.equals(migration1.createdAt) : migration1.createdAt != null) return false;
        if (runAt != null ? !runAt.equals(migration1.runAt) : migration1.runAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (migration != null ? migration.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (runAt != null ? runAt.hashCode() : 0);
        return result;
    }
}
