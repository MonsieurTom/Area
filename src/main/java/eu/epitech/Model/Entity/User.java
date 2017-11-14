package eu.epitech.Model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User implements IUser
{

    private int id;

    private String password;

    private String email;

    private Boolean banned;

    public User()
    {}

    public User(int id, String password, String email)
    {
        setId(id);
        setPassword(password);
        setEmail(email);
    }

    public void setId(int id) { this.id = id; }

    public int getId() {
        return id;
    }

    public void setPassword(String password) { this.password = password; }

    public String getPassword() { return (password); }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Boolean getBanned() {
        return banned;
    }
}
