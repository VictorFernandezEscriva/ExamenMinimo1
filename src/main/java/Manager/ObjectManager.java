package Manager;

import Entity.*;

import java.util.*;


public interface ObjectManager {

    //FUNCTIONS RELATED WITH OBJECTS
    public List<ObjectClass> objectByDescendentPrice();
    public void addObject(String objectId, String objectName, String description, double coins);
    public int numObject(); // No la pide

    // FUNCTIONS RELATED TO USERS
    public void addUser(String userId, String userName, String userSurname, String date, String email, String password);
    public List<User> usersByAlphabet();

    // cero se puede, uno no existe el usuario, dos no dispone del saldo suficiente
    public int loginUser(String email, String password);

    public String compraObjecto(String userId, String objectId);

    public List<ObjectClass> compraUser(String userId);



    // FUNCTIONS RELATED TO SERVICE
    public int size();
    public List<ObjectClass> findAll();









}
