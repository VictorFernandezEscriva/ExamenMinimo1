package Entity;
import java.util.*;


public class User {

    // Atributos

    String userId;
    String userName;
    String userSurname;
    String date;
    Credentials credentials;
    double money;
    List<ObjectClass> objectsUser;

    //Constructores

    public User(){}   // Constructor vacio

    public User(String userId, String userName, String userSurname, String date, Credentials credentials) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.date = date;
        this.credentials = credentials;
        this.money = 50;
        this.objectsUser = new LinkedList<>();
    }


    //Getter and Setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public List<ObjectClass> getObjectsUser() {
        return objectsUser;
    }

    public void setObjectsUser(List<ObjectClass> objectsUser) {
        this.objectsUser = objectsUser;
    }
}
