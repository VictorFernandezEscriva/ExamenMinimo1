package Entity;

import java.util.List;
import java.util.*;

public class ObjectClass {

    // Atributos

    String objectId;
    String objectName;
    String description;
    double coins;

    //Constructores

    public ObjectClass(){} // Constructor vacio

    public ObjectClass(String objectId, String objectName, String description, double coins) {
        this.objectId = objectId;
        this.objectName = objectName;
        this.description = description;
        this.coins = coins;
    }

    //Getter and Setters

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }
}
