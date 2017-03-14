package barbot.database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by naveen on 3/13/17.
 */
@Entity
public class Migrations {
    private String migration;
    private int batch;

    @Basic
    @Column(name = "migration")
    public String getMigration() {
        return migration;
    }

    public void setMigration(String migration) {
        this.migration = migration;
    }

    @Basic
    @Column(name = "batch")
    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Migrations that = (Migrations) o;

        if (batch != that.batch) return false;
        if (migration != null ? !migration.equals(that.migration) : that.migration != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = migration != null ? migration.hashCode() : 0;
        result = 31 * result + batch;
        return result;
    }
}
