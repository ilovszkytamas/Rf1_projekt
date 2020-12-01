package Telekocsi.Model;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Car> cars;

    private String username;
    private String password;
    private String email;
    private String realname;
    private boolean is_admin;

    public User(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public boolean Is_Admin() {
        return is_admin;
    }

    public void setAdmin(boolean admin) {
        this.is_admin = is_admin;
    }
}
