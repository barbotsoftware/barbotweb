package barbot.database.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import barbot.utils.Constants;

/**
 * Created by alexh on 6/3/2017.
 */
@Entity
@Table(name = Constants.TABLE_EMAIL_CAPTURE, schema = Constants.DB_SCHEMA)
public class EmailCapture {
    private Integer id;
    private String email;
    private String name;
    private String city;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
