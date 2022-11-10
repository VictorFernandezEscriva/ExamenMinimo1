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
    }

    @After
    public void tearDown() {
        this.pm = null;
    }

    @Test
    public void addUser(){
        logger.info("Añado un usuario");
        this.pm.addUser("2004","Marc");
        Assert.assertEquals(2004,pm);
    }

    //Punto 3
    @Test
    public void addJuego(){
        logger.info("Añado un juego");
        Assert.assertEquals(0, .addJuego("3001", "Juegoderol", 3));
    }

    // Punto 4
    @Test
    public void testAddObject() {
        Assert.assertEquals(4, this.pm.numObject());

        this.pm.addObject("22", "Monster", "Bebida con cafeina", 3);

        Assert.assertEquals(5, this.pm.numObject());

    }

    // Punto 6
    @Test
    public void processComprasTest() {
        Assert.assertEquals(3, this.pm.numUsers());
        Assert.assertEquals(4, this.pm.numObject());


        Assert.assertEquals(0, this.pm.compraObjectos("0","B001"));
        Assert.assertEquals(1, this.pm.compraObjectos("10","B001"));
        Assert.assertEquals(2, this.pm.compraObjectos("1","A002"));

    }

    // Punto 7
    @Test
    public void listOfBoughtObjects(){

        Assert.assertEquals(0, this.pm.compraObjectos("0","B001"));

        List<Juego> ax = this.pm.compraUser("0");

        Assert.assertEquals("B001", ax.get(0).getObjectId());

    }

    // Punto 5
    @Test
    public void objectsSortByPrice() {
        List<Juego> obj = this.pm.objectByDescendentPrice();

        Assert.assertEquals("A002", obj.get(0).getObjectId());
        Assert.assertEquals(100, obj.get(0).getCoins(), 0);

        Assert.assertEquals("C002", obj.get(1).getObjectId());
        Assert.assertEquals(1.5, obj.get(1).getCoins(), 0);

        Assert.assertEquals("A003", obj.get(2).getObjectId());
        Assert.assertEquals(1.25, obj.get(2).getCoins(), 0);

        Assert.assertEquals("B001", obj.get(3).getObjectId());
        Assert.assertEquals(1, obj.get(3).getCoins(), 0);






    }

    // Punto 2
    @Test
    public void usersSortBySurname() {

        List<User> u = this.pm.usersByAlphabet();

        Assert.assertEquals("Lidia", u.get(0).getUserName());

        Assert.assertEquals("Aida", u.get(1).getUserName());

        Assert.assertEquals("Esteban", u.get(2).getUserName());

    }

}