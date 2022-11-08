package Entity;
import java.util.*;


public class Credentials {

    // Atributos

    String email;
    String password;

    //Constructores

    public Credentials() {} // Constructor vacio

    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //Getter and Setters

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
}
