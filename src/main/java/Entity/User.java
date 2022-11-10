package Entity;


import java.util.*;


public class User {

    // Atributos

    String userId;
    String userName;
    List<Partida> partidasList;

    //Constructores

    public User(){}   // Constructor vacio

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.partidasList = new ArrayList<>();
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

    public List<Partida> getPartidasList() {
        return partidasList;
    }

    public void setPartidasList(List<Partida> partidasList) {
        this.partidasList = partidasList;
    }

    //FUNCIONES
    public void addPartida(Partida p){
        this.partidasList.add(p);
    }
}
