package Manager;

import Entity.*;
import java.util.*;
import org.apache.log4j.Logger;

public class ObjectManagerImpl implements ObjectManager {

    // Atributos

    int userId; // Para asignar una Id al Usuario
    List<ObjectClass> objects;
    HashMap<String,User> users;


   private static ObjectManager instance; // Creamos la interfaz de ObjectsManager
    final static Logger logger = Logger.getLogger(ObjectManagerImpl.class);

    // Funciones de inicialización
    public ObjectManagerImpl(){ // Inicializamos los vectores de la clase ObjectsManagerImpl
        this.objects = new ArrayList<>();
        this.users = new HashMap<>();
        this.userId = 0;
    }

    public static ObjectManager getInstance(){ // Si no existe creamos una implementación (fachada) para la interfaz
        if (instance==null) instance = new ObjectManagerImpl();
        return instance;
    }

    //Funciones para service

    public int size() {
        int ret = this.objects.size();
        logger.info("size " + ret);
        return ret;
    }

    public List<ObjectClass> findAll(){
        return this.objects;
    }

    // Funciones
    @Override
    public List<ObjectClass> objectsByPrice() {
        this.objects.sort((ObjectClass p1,ObjectClass p2)->Double.compare(p1.getCoins(),p2.getCoins()));
        return this.objects;
    }

    @Override
    public List<ObjectClass> objectssBySales() {
        this.objects.sort((Objects p1,Objects p2)->(p1.getNumSales() - p2.getNumSales()));
        return this.Objectss;
    }
    @Override
    public void addObjects(String ObjectsId, String name, double price) {
        Objects p = new Objects(ObjectsId, name, price);
        this.Objectss.add(p);
    }
    @Override
    public Objects getObjects(String ObjectsId) {
        // for(Objects Objects : Objectss){
        //     if( Objects.getObjectsId() == ObjectsId){

        //   }
        //}
        Objects auxiliar = new Objects();
        for (int i = 0; i < Objectss.size(); i++){
            if(Objects.equals(Objectss.get(i).getObjectsId(), ObjectsId)){
                auxiliar = this.Objectss.get(i);
            }
        }
        return auxiliar;
    }
    @Override
    public int numObjectss() {
        return this.Objectss.size();
    }
    @Override
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    @Override
    public Order processOrder() {
        Order a = this.orders.poll();  // Sacamos la primera orden de la cola
        this.users.get(a.getUserId()).getProcessedOrders().add(a); // Añadimos la orden a las ordenes procesadas del usuario
        for(int i = 0; i < a.getElements().size(); i++){
            for (int j = 0; j < this.Objectss.size(); j++){
                if(Objects.equals(this.Objectss.get(j).getObjectsId(), a.getElements().get(i).getObjects())){
                    this.Objectss.get(j).setNumSales(this.Objectss.get(j).getNumSales() + a.getElements().get(i).getQuantity());
                }
            }
        }
        return a;
    }

    @Override
    public List<Order> ordersByUser(String userId) {
        return this.users.get(userId).getProcessedOrders();
    }
    @Override
    public int numOrders() {
        return this.orders.size();
    }
    @Override
    public int numSales(String b001) {
        int aux = 0;
        for(int i=0; i < this.Objectss.size(); i++){
            if(Objects.equals(this.Objectss.get(i).getObjectsId(), b001)){
                aux = this.Objectss.get(i).getNumSales();
            }
        }
        return aux;
    }

    // FUNCIÓN DEL NUEVO EJERCICIO
    @Override
    public void addUser(String userName, String userSurname, String date, String email, String password) {
        Credentials c = new Credentials(email, password);
        User a = new User(Integer.toString(this.userId), userName, userSurname, date, c);
        //User b = new User(Integer.toString(this.users.size()), userName, userSurname, date, c, objectsUser);
        this.userId = this.userId + 1;
        this.users.put(Integer.toString(this.userId),a);
    }
    @Override
    public int numUsers() {
        return this.users.size();
    }
}
