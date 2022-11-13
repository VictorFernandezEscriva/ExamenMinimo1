package Manager;

import Entity.*;
import Entity.V0.Actividad;

import java.util.*;


public interface PartidaManager {


    int size();

    void addUser(String userId, String userName);

    void addJuego(String juegoId, String juegoDescription, int nivelMaximo);

    int inicioPartida(String userId, String juegoId, String fecha);

    // 0 no existe usuario, 1000 Ninguna partida activa, x nivel actual
    int nivelActual(String userId);

    // 0 no existe usuario, 1000 Ninguna partida activa, x puntuacion actual
    int puntuacionActual(String userId);

    // 0 no existe el usuario,
    int pasarNivel(String userId, int puntos, String fecha);

    int finalizarPartida(String userId);

    List<Partida> partidasDeUser(String userId);

    List<Actividad> actividadUserPartida(String userId, String juegoId);

    int numUsers();

    int numJuegos();
}
