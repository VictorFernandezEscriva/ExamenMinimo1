package Manager;

import Entity.*;
import java.util.*;

import Entity.V0.Actividad;
import org.apache.log4j.Logger;

public class PartidaManagerImpl implements PartidaManager {

    // Atributos


    List<Juego> juegos;
    HashMap<String,User> users;

   private static PartidaManager instance; // Creamos la interfaz de ObjectsManager
    final static Logger logger = Logger.getLogger(PartidaManagerImpl.class);

    // Funciones de inicialización
    public PartidaManagerImpl(){ // Inicializamos los vectores de la clase ObjectsManagerImpl
        this.users = new HashMap<>();
        this.juegos = new ArrayList<>();
    }

    public static PartidaManager getInstance(){ // Si no existe creamos una implementación (fachada) para la interfaz
        if (instance==null) instance = new PartidaManagerImpl();
        return instance;
    }

    //Funciones para service

    @Override
    public int size() {
        int ret = this.users.size();
        logger.info("size " + ret);
        return ret;
    }

    //FUNCTIONS

    @Override
    public void addUser(String userId, String userName) {
        User user = new User(userId,userName);
        this.users.put(userId,user);
    }
    @Override
    public void addJuego(String juegoId, String juegoDescription, int nivelMaximo) {
        Juego p = new Juego(juegoId,juegoDescription, nivelMaximo);
        this.juegos.add(p);
    }

    @Override
    public void inicioPartida(String userId, String  juegoId, String fecha) {
        Actividad a = new Actividad(1,50, fecha);
        Partida p = new Partida(userId,juegoId, a);
        this.users.get(userId).addPartida(p);
    }

    // 0 no existe usuario, 1000 Ninguna partida activa, x nivel actual
    @Override
    public int nivelActual(String userId) {
        User aux = this.users.get(userId);
        if(!this.users.containsKey(userId)){
            return 0;
        }
        else if(!aux.getPartidasList().get(aux.getPartidasList().size() - 1).isEstadoPartida()){
            return 1000;
        }
        else{
            return aux.getPartidasList().get(aux.getPartidasList().size() - 1).getActividad().get(aux.getPartidasList().get(aux.getPartidasList().size() - 1).getActividad().size()-1).getNivel();
        }
    }
    // 0 no existe usuario, 1000 Ninguna partida activa, x puntuacion actual
    @Override
    public int puntuacionActual(String userId){
        User aux = this.users.get(userId);
        if(!this.users.containsKey(userId)){
            return 0;
        }
        else if(!aux.getPartidasList().get(aux.getPartidasList().size() - 1).isEstadoPartida()){
            return 1000;
        }
        else{
            return aux.getPartidasList().get(aux.getPartidasList().size() - 1).getActividad().get(aux.getPartidasList().get(aux.getPartidasList().size() - 1).getActividad().size()-1).getPuntos();
        }
    }

    // 0 no existe el usuario,
    @Override
    public int pasarNivel(String userId, int puntos, String fecha){

        User aux = this.users.get(userId);
        int res = 1;
        if(!this.users.containsKey(userId)){
            res = 0;
        } else if (!aux.getPartidasList().get(aux.getPartidasList().size() - 1).isEstadoPartida()) {
            res = 1000;
        }
        else{
            int i = 0;
            boolean encontrado = false;
            String aux2=aux.getPartidasList().get(aux.getPartidasList().size() - 1).getJuegoId();
            while(i < this.juegos.size() && !encontrado){
                if(Objects.equals(aux2, juegos.get(i).getJuegoId())){
                    encontrado=true;
                }
                else{
                    i++;
                }
            }
            if(juegos.get(i).getNivelMaximo() == (aux.getPartidasList().get(aux.getPartidasList().size() - 1).getActividad().get(aux.getPartidasList().get(aux.getPartidasList().size() - 1).getActividad().size()-1).getNivel()-1)){
                int p = puntos + 100;
                Actividad a = new Actividad(juegos.get(i).getNivelMaximo(),aux.getPartidasList().get(aux.getPartidasList().size() - 1).getActividad().get(aux.getPartidasList().get(aux.getPartidasList().size() - 1).getActividad().size()-1).getNivel() + p, fecha);
                aux.getPartidasList().get(aux.getPartidasList().size() - 1).getActividad().add(a);
                finalizarPartida(userId);
            }
            else{
                Actividad a = new Actividad(aux.getPartidasList().get(aux.getPartidasList().size() - 1).getActividad().get(aux.getPartidasList().get(aux.getPartidasList().size() - 1).getActividad().size()-1).getNivel()+1,aux.getPartidasList().get(aux.getPartidasList().size() - 1).getActividad().get(aux.getPartidasList().get(aux.getPartidasList().size() - 1).getActividad().size()-1).getNivel() + puntos, fecha);
                aux.getPartidasList().get(aux.getPartidasList().size() - 1).getActividad().add(a);
            }
        }
        return res;

    }
    @Override
    public void finalizarPartida(String userId){
        User aux = this.users.get(userId);
        aux.getPartidasList().get(aux.getPartidasList().size() - 1).setEstadoPartida(false);
    }

    /*
    @Override
    public User usuariosOrdenadosPorPuntuacion(String userId){

        List<User> aux = new ArrayList<>(this.users.values());
        aux.sort((User p1,User p2)->{

            int aux1 = String.CASE_INSENSITIVE_ORDER.compare(p1., p2.getUserSurname());
            if(aux1==0) {
                aux1 = String.CASE_INSENSITIVE_ORDER.compare(p1.getUserName(), p2.getUserName());
            }
            return aux1;
        });
        return aux;
    }
     */
    @Override
    public List<Partida> partidasDeUser(String userId){
        return users.get(userId).getPartidasList();
    }

    @Override
    public List<Actividad> actividadUserPartida(String userId,String juegoId){

        int i = 0;
        User a = this.users.get(userId);
        boolean encontrado = false;
        while(i < a.getPartidasList().size() && !encontrado){
            if(Objects.equals(a.getPartidasList().get(i).getJuegoId(), juegoId)){
                encontrado=true;
            }
            else{
                i++;
            }
        }
        return a.getPartidasList().get(i).getActividad();
    }
}