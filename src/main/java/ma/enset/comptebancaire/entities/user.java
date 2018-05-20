package ma.enset.comptebancaire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class user
{
    @Id
    private String  username;
    private String  password;
    private Boolean active;

    public user() {}
    public user(String username, String password, Boolean active) {
        super();
        this.username = username;
        this.password = password;
        this.active = active;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }


}
