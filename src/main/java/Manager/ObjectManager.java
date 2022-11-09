package Manager;

import Entity.*;

import java.util.*;


public interface ObjectManager {

    //FUNCTIONS RELATED WITH OBJECTS
    public List<ObjectClass> objectByDescendentPrice();
    public void addObject(String objectId, String objectName, String description, double coins);
    public int numObject(); // No la pide

    // FUNCTIONS RELATED TO USERS
    /// Si es cero bien y si es 1 email ya utilizado por un user
    public int addUser(String userName, String userSurname, String date, String email, String password);

    public List<User> usersByAlphabet();

    // cero se puede, uno no existe el usuario, dos no dispone del saldo suficiente
    public int loginUser(String email, String password);
    /// cero se puede, uno no existe user, 2 no hay saldo suficiente
    public int compraObjectos(String userId, String objectId);

    public List<ObjectClass> compraUser(String userId);

    public int numUsers();



    // FUNCTIONS RELATED TO SERVICE
    public int size();
    public List<ObjectClass> findAll();




}
