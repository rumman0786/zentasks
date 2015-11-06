package models;
import javax.persistence.*;
import com.avaje.ebean.Model;
import com.avaje.ebean.Ebean;

@Entity
public class User extends Model {

    @Id
    public String email;
    public String name;
    public String password;
    
    public User(String email, String name, String password) {
      this.email = email;
      this.name = name;
      this.password = password;
    }

    public static Finder<String,User> find = new Finder<String,User>(
        User.class
    ); 
}
