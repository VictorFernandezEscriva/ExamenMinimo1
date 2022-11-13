import Entity.V0.Actividad;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Manager.*;
import Entity.*;

import java.util.List;

public class PartidaManagerImplTest {

    final static Logger logger = Logger.getLogger(PartidaManagerImpl.class);
    PartidaManager pm;

    @Before
    public void setUp() {
        this.pm = new PartidaManagerImpl();
        this.pm.addUser("2001","Victor");
        this.pm.addUser("2002","Lidia");
        this.pm.addUser("2003","Jose");

        this.pm.addJuego("3001", "Juegoderol", 3);
        this.pm.addJuego("3002", "Juegodetiros", 2);
        this.pm.addJuego("3003", "Juegodeestrategia", 4);

        this.pm.inicioPartida("2002", "3001", "12/11/2022");
    }

    @After
    public void tearDown() {
        this.pm = null;
    }

    @Test
    public void addUser(){
        logger.info("Comprobamos que hay 3 usuarios añadidos");
        Assert.assertEquals(3,pm.numUsers());
        logger.info("Añado nuevo usuario");
        this.pm.addUser("2004","Marc");
        logger.info("Comprobamos que hay 4 usuarios añadidos");
        Assert.assertEquals(4,pm.numUsers());
    }

    @Test
    public void addJuego(){
        logger.info("Comprobamos que hay 3 juego añadidos");
        Assert.assertEquals(3,pm.numJuegos());
        logger.info("Añado nuevo juego");
        this.pm.addJuego("3004", "Juegoderol", 10);
        logger.info("Comprobamos que hay 4 juegos añadidos");
        Assert.assertEquals(4,pm.numJuegos());
    }

    @Test
    public void inicioPartida(){
        logger.info("Le añadimos una partida a un usuario");
        int aux = this.pm.inicioPartida("2001", "3001", "12/11/2022");
        logger.info("Comprobamos que se ha añadido correctamente");
        Assert.assertEquals("3001",this.pm.partidasDeUser("2001").get(this.pm.partidasDeUser("2001").size()-1).getJuegoId());
        logger.info("Comprobamos que pasa si añadimos una partida habiendo ya una activa");
        Assert.assertEquals(1,this.pm.inicioPartida("2001", "3002", "12/11/2022"));
        logger.info("Devuelve un 1");
    }

    @Test
    public void nivelActual(){
        logger.info("Comprobamos que sale 0 cuando no existe el usuario");
        Assert.assertEquals(0,this.pm.nivelActual("2004"));
        logger.info("Le añadimos una partida a un usuario");
        this.pm.inicioPartida("2001", "3001", "12/11/2022");
        logger.info("Comprobamos que el nivel de esta partida sea 1");
        Assert.assertEquals(1,this.pm.nivelActual("2001"));
        this.pm.finalizarPartida("2001");
        logger.info("Comprobamos que sale 1000 cuando el usuario no tiene partidas activas");
        Assert.assertEquals(1000,this.pm.nivelActual("2001"));
    }

    @Test
    public void puntacionActual(){
        logger.info("Le añadimos una partida a un usuario");
        this.pm.inicioPartida("2001", "3001", "12/11/2022");
        logger.info("Comprobamos que el nivel de esta partida sea 1");
        Assert.assertEquals(50,this.pm.puntuacionActual("2001"));
        logger.info("Si no exite el usuario devuelve 0");
        Assert.assertEquals(0,this.pm.puntuacionActual("2007"));
        logger.info("Si la partida esta finalizada devuelve 1");
        this.pm.finalizarPartida("2001");
        Assert.assertEquals(1,this.pm.puntuacionActual("2001"));
    }

    @Test
    public void pasarNivel(){
        logger.info("Le añadimos una partida a un usuario");
        this.pm.inicioPartida("2001", "3001", "12/11/2022");
        logger.info("Pasamos a nivel 2");
        this.pm.pasarNivel("2001",20,"13/11/2022");
        Assert.assertEquals(70,this.pm.puntuacionActual("2001"));
        Assert.assertEquals(2,this.pm.nivelActual("2001"));
        logger.info("Si no exite el usuario devuelve 0");
        Assert.assertEquals(0,this.pm.nivelActual("2007"));
        logger.info("Si la partida esta finalizada devuelve 1000");
        this.pm.finalizarPartida("2001");
        Assert.assertEquals(1000,this.pm.nivelActual("2001"));
    }

    @Test
    public void finalizarPartidas(){
        logger.info("Finalizamos la partida de un usuario");
        this.pm.finalizarPartida("2002");
        logger.info("Si la partida esta finalizada podremos iniciar otra partida con ese usuario");
        Assert.assertEquals(0,this.pm.inicioPartida("2002", "3002", "12/11/2022"));
    }

    @Test
    public void partidasDeUser(){
        logger.info("Lista de partidas de un usuario");
        List<Partida>p = this.pm.partidasDeUser("2002");
        Assert.assertEquals(1,p.size());
        logger.info("Terminamos la partida");
        this.pm.finalizarPartida("2002");
        logger.info("Añadimos otra partida");
        this.pm.inicioPartida("2002", "3002", "12/11/2022");
        p = this.pm.partidasDeUser("2002");
        Assert.assertEquals(2,p.size());
    }

    @Test
    public void actividadUserPartida(){
        logger.info("Actividad de un usuario");
        List<Actividad>a = this.pm.actividadUserPartida("2002", "3001");
        Assert.assertEquals(1,a.size());
        logger.info("Subimos de nivel");
        this.pm.pasarNivel("2002",30,"13/11/2022");
        logger.info("Añadimos una nueva actividad");
        a = this.pm.actividadUserPartida("2002", "3001");
        Assert.assertEquals(2,a.size());
    }
}