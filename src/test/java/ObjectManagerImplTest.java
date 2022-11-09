import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Manager.*;
import Entity.*;
import Main.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

public class ObjectManagerImplTest {

    final static Logger logger = Logger.getLogger(ObjectManagerImpl.class);
    ObjectManager pm;

    @Before
    public void setUp() {
        this.pm = new ObjectManagerImpl();
        this.pm.addUser("Aida", "Fernandez", "13/06/2001", "email1", "Victor");
        this.pm.addUser("Esteban", "Fernandez", "3/12/1969", "email2", "Jose");
        this.pm.addUser("Lidia",  "Escriva", "30/12/1970", "email3", "Lidia");

        this.pm.addObject("B001", "Cocacola", "bebida refrescante", 1);
        this.pm.addObject("C002", "Nestea", "Para combatir la calor", 1.5);
        this.pm.addObject("A002", "Donut", "Para merendar",100);
        this.pm.addObject("A003", "Galletas", "Para desayunar",1.25);
    }

    @After
    public void tearDown() {
        this.pm = null;
    }

    //Punto 1
    @Test
    public void addUser(){
        logger.info("Añado mal un usuario");
        Assert.assertEquals(1,this.pm.addUser("Xaloc",  "Rodriguez", "30/12/1970", "email3", "Xaloc"));
        logger.info("Añado bien un usuario");
        Assert.assertEquals(0,this.pm.addUser("Xaloc",  "Rodriguez", "30/12/1970", "email4", "Xaloc"));
    }

    //Punto 3
    @Test
    public void loginUser(){
        logger.info("Entramos bien con un user");
        Assert.assertEquals(0, this.pm.loginUser("email1", "Victor"));
        logger.info("Entramos mal con un user");
        Assert.assertEquals(1, this.pm.loginUser("email2", "Victor"));

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

        List<ObjectClass> ax = this.pm.compraUser("0");

        Assert.assertEquals("B001", ax.get(0).getObjectId());

    }

    // Punto 5
    @Test
    public void objectsSortByPrice() {
        List<ObjectClass> obj = this.pm.objectByDescendentPrice();

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