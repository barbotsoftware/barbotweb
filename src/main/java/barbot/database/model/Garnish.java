package barbot.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import barbot.utils.Constants;

/**
 * Created by Naveen on 12/30/17.
 */
@Entity
@Table(name = Constants.TABLE_GARNISH, schema = Constants.DB_SCHEMA)
public class Garnish extends BaseEntity {

    @Column(name = "uid", nullable = false)
    @JsonProperty("garnish_id")
    @JsonView(View.Id.class)
    private String uid;

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    @JsonView(View.Summary.class)
    private String name;

    public Garnish() {

    }

    public Garnish(String uid, String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Garnish garnish = (Garnish) o;

        if (id != null ? !id.equals(garnish.id) : garnish.id != null) return false;
        if (uid != null ? !uid.equals(garnish.uid) : garnish.uid != null) return false;
        if (name != null ? !name.equals(garnish.name) : garnish.name != null) return false;
        if (createdAt != null ? !createdAt.equals(garnish.createdAt) : garnish.createdAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(garnish.deletedAt) : garnish.deletedAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(garnish.updatedAt) : garnish.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
