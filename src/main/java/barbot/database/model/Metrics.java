package barbot.database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by naveen on 3/13/17.
 */
@Entity
public class Metrics {
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

        Metrics metrics = (Metrics) o;

        if (barbotId != metrics.barbotId) return false;
        if (ouncesPoured != metrics.ouncesPoured) return false;
        if (users != metrics.users) return false;
        if (drinksPoured != metrics.drinksPoured) return false;

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
