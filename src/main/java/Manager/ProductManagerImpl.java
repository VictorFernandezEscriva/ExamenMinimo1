package Manager;
import Clases.Order;
import Clases.Product;
import Clases.User;
import java.util.*;
import org.apache.log4j.Logger;

public class ProductManagerImpl implements ProductManager{

    // Atributos
    List<Product> products;
    HashMap<String,User> users;
    Queue<Order> orders;

   private static ProductManager instance; // Creamos la interfaz de ProductManager
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    // Funciones de inicialización
    public ProductManagerImpl(){ // Inicializamos los vectores de la clase ProductManagerImpl
        this.products = new ArrayList<>();
        this.users = new HashMap<>();
        this.orders = new LinkedList<>();
    }

    public static ProductManager getInstance(){ // Si no existe creamos una implementación (fachada) para la interfaz
        if (instance==null) instance = new ProductManagerImpl();
        return instance;
    }

    //Funciones para service

    public int size() {
        int ret = this.products.size();
        logger.info("size " + ret);
        return ret;
    }

    public List<Product> findAll(){
        return this.products;
    }

    // Funciones
    @Override
    public List<Product> productsByPrice() {
        this.products.sort((Product p1,Product p2)->Double.compare(p1.getPrice(),p2.getPrice()));
        return this.products;
    }

    @Override
    public List<Product> productsBySales() {
        this.products.sort((Product p1,Product p2)->(p1.getNumSales() - p2.getNumSales()));
        return this.products;
    }
    @Override
    public void addProduct(String productId, String name, double price) {
        Product p = new Product(productId, name, price);
        this.products.add(p);
    }
    @Override
    public Product getProduct(String productId) {
        // for(Product product : products){
        //     if( product.getProductId() == productId){

        //   }
        //}
        Product auxiliar = new Product();
        for (int i = 0; i < products.size(); i++){
            if(Objects.equals(products.get(i).getProductId(), productId)){
                auxiliar = this.products.get(i);
            }
        }
        return auxiliar;
    }
    @Override
    public int numProducts() {
        return this.products.size();
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
            for (int j = 0; j < this.products.size(); j++){
                if(Objects.equals(this.products.get(j).getProductId(), a.getElements().get(i).getProduct())){
                    this.products.get(j).setNumSales(this.products.get(j).getNumSales() + a.getElements().get(i).getQuantity());
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
        for(int i=0; i < this.products.size(); i++){
            if(Objects.equals(this.products.get(i).getProductId(), b001)){
                aux = this.products.get(i).getNumSales();
            }
        }
        return aux;
    }
    @Override
    public void addUser(String s, String name, String surname) {
        User a = new User(s, name, surname);
        this.users.put(s,a);
    }
    @Override
    public int numUsers() {
        return this.users.size();
    }
}
