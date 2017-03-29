package barbot.database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Naveen on 3/27/17.
 */
@Entity
@Table(name = "user", schema = "barbotdb")
public class User extends BaseEntity {

    @Basic
    @Column(name = "uid")
    private String uid;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "remember_token")
    private String rememberToken;

//    private Collection<DrinkOrder> drinkOrdersById;

    public User() {

    }

    public User(String uid, String name, String email, String password) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (uid != null ? !uid.equals(user.uid) : user.uid != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (rememberToken != null ? !rememberToken.equals(user.rememberToken) : user.rememberToken != null)
            return false;
        if (createdAt != null ? !createdAt.equals(user.createdAt) : user.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(user.updatedAt) : user.updatedAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(user.deletedAt) : user.deletedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (rememberToken != null ? rememberToken.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        return result;
    }

//    @OneToMany(mappedBy = "userByUserId")
//    public Collection<DrinkOrder> getDrinkOrdersById() {
//        return drinkOrdersById;
//    }
//
//    public void setDrinkOrdersById(Collection<DrinkOrder> drinkOrdersById) {
//        this.drinkOrdersById = drinkOrdersById;
//    }
}
