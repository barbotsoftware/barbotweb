package barbot.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by naveen on 3/12/17.
 */
@Entity
@Table(name = "metrics", schema = "barbot", catalog = "")
public class MetricsEntity {
    private int barbotId;
    private int ouncesPoured;
    private int users;
    private int drinksPoured;

    @Basic
    @Column(name = "barbot_id")
    public int getBarbotId() {
        return barbotId;
    }

    public void setBarbotId(int barbotId) {
        this.barbotId = barbotId;
    }

    @Basic
    @Column(name = "ounces_poured")
    public int getOuncesPoured() {
        return ouncesPoured;
    }

    public void setOuncesPoured(int ouncesPoured) {
        this.ouncesPoured = ouncesPoured;
    }

    @Basic
    @Column(name = "users")
    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    @Basic
    @Column(name = "drinks_poured")
    public int getDrinksPoured() {
        return drinksPoured;
    }

    public void setDrinksPoured(int drinksPoured) {
        this.drinksPoured = drinksPoured;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetricsEntity that = (MetricsEntity) o;

        if (barbotId != that.barbotId) return false;
        if (ouncesPoured != that.ouncesPoured) return false;
        if (users != that.users) return false;
        if (drinksPoured != that.drinksPoured) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = barbotId;
        result = 31 * result + ouncesPoured;
        result = 31 * result + users;
        result = 31 * result + drinksPoured;
        return result;
    }
}
