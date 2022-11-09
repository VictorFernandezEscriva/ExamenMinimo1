package Manager;

import Entity.*;
import java.util.*;

import Entity.V0.Credentials;
import org.apache.log4j.Logger;

public class ObjectManagerImpl implements ObjectManager {

    // Atributos

    List<ObjectClass> objects;
    HashMap<String,User> users;

   private static ObjectManager instance; // Creamos la interfaz de ObjectsManager
    final static Logger logger = Logger.getLogger(ObjectManagerImpl.class);

    // Funciones de inicialización
    public ObjectManagerImpl(){ // Inicializamos los vectores de la clase ObjectsManagerImpl
        this.objects = new LinkedList<>();
        this.users = new HashMap<>();
    }

    public static ObjectManager getInstance(){ // Si no existe creamos una implementación (fachada) para la interfaz
        if (instance==null) instance = new ObjectManagerImpl();
        return instance;
    }

    //Funciones para service

    @Override
    public int size() {
        int ret = this.objects.size();
        logger.info("size " + ret);
        return ret;
    }
    @Override
    public List<ObjectClass> findAll(){
        return this.objects;
    }


    //FUNCTIONS RELATED WITH OBJECTS
    @Override
    public List<ObjectClass> objectByDescendentPrice(){
        this.objects.sort((ObjectClass p2,ObjectClass p1)->Double.compare(p1.getCoins(),p2.getCoins()));
        return this.objects;
    }
    @Override
    public void addObject(String objectsId, String name, String descripcion, double price) {
        ObjectClass p = new ObjectClass(objectsId, name, descripcion, price);
        this.objects.add(p);
    }
    @Override
    public int numObject(){
        return this.objects.size();
    }

    // FUNCTIONS RELATED TO USERS
    @Override
    public int addUser(String userName, String userSurname, String date, String email, String password) {

        int aux=2;
        int i = 0;
        boolean encontrado = false;
        while(i < this.users.size() && !encontrado){
            if(Objects.equals(users.get(Integer.toString(i)).getCredentials().getEmail(), email)){
                encontrado=true;
                aux=1;
            }
            else{
                i++;
            }
        }
        if(!encontrado){
            Credentials c = new Credentials(email, password);
            User a = new User(Integer.toString(this.users.size()), userName, userSurname, date, c);
            this.users.put(Integer.toString(this.users.size()),a);
            aux=0;
        }
        return aux;
    }
    @Override
    public List<User> usersByAlphabet(){
        List<User> aux = new ArrayList<>(this.users.values());
        aux.sort((User p1,User p2)->{

            int aux1 = String.CASE_INSENSITIVE_ORDER.compare(p1.getUserSurname(), p2.getUserSurname());
            if(aux1==0) {
                aux1 = String.CASE_INSENSITIVE_ORDER.compare(p1.getUserName(), p2.getUserName());
            }
            return aux1;
        });
        return aux;
    }

    @Override
    // cero login correcto, uno login incorrecto
    public int loginUser(String email, String password){
        int buscador = 1;
        for(int i = 0; i< this.users.size(); i++){
            if(Objects.equals(this.users.get(Integer.toString(i)).getCredentials().getEmail(), email) && Objects.equals(this.users.get(Integer.toString(i)).getCredentials().getPassword(), password)) {
                buscador = 0;
            }
        }
        return buscador;
    }
    @Override
    public int compraObjectos(String userId, String objectId){

        if(!this.users.containsKey(userId)){
            return 1;
        }
        int posicionobj=0;
        for(int i=0; i<this.objects.size();i++){
            if(Objects.equals(this.objects.get(i).getObjectId(), objectId)){
                posicionobj = i;
            }
        }
        if (this.users.get(userId).getMoney()<this.objects.get(posicionobj).getCoins()) {
            return 2;
        }
        else{
            this.users.get(userId).addCompra(this.objects.get(posicionobj),this.objects.get(posicionobj).getCoins());
            return 0;
        }
    }
    @Override
    public List<ObjectClass> compraUser(String userId){
        return this.users.get(userId).getObjectsUser();
    }

    @Override
    public int numUsers(){
        return this.users.size();
    }
}