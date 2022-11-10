package Entity;

import Entity.V0.Actividad;

import java.util.LinkedList;
import java.util.List;

public class Partida {

    // Atributos

    String userId;
    String juegoId;
    boolean estadoPartida;
    List<Actividad> actividad;

    // Constructor

    public Partida(){}

    public Partida(String userId, String juegoId, Actividad a) {
        this.userId = userId;
        this.juegoId = juegoId;
        this.estadoPartida = true;
        this.actividad = new LinkedList<>();
        this.actividad.add(a);
    }

    // Getters y setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(String juegoId) {
        this.juegoId = juegoId;
    }

    public boolean isEstadoPartida() {
        return estadoPartida;
    }

    public void setEstadoPartida(boolean estadoPartida) {
        this.estadoPartida = estadoPartida;
    }

    public List<Actividad> getActividad() {
        return actividad;
    }

    public void setActividad(List<Actividad> actividad) {
        this.actividad = actividad;
    }

}
