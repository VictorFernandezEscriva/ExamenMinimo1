package Services;

import Entity.V0.Credentials;
import Manager.*;
import Entity.*;
import Main.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Api(value = "/Object", description = "Endpoint to Object Service")
@Path("/Object")

public class ObjectService {

    private ObjectManager om;

    public ObjectService() {
        this.om = ObjectManagerImpl.getInstance();
        if (om.size()==0) {
            this.om.addObject("B001", "Cocacola", "bebida refrescante", 1);
            this.om.addObject("C002", "Nestea", "Para combatir la calor", 1.5);
            this.om.addObject("A002", "Donut", "Para merendar",100);
            this.om.addObject("A003", "Galletas", "Para desayunar",1.25);
            this.om.addUser("Aida", "Fernandez", "13/06/2001", "email1", "Victor");
            this.om.addUser("Esteban", "Fernandez", "3/12/1969", "email2", "Jose");
            this.om.addUser("Lidia",  "Escriva", "30/12/1970", "email3", "Lidia");
        }
    }

    //Primer punto
    @POST
    @ApiOperation(value = "create a new user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Email repeated")
    })
    @Path("/newUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {
        int aux = this.om.addUser(user.getUserName(),user.getUserSurname(),user.getDate(),user.getCredentials().getEmail(),user.getCredentials().getPassword());
        if (aux == 0)  return Response.status(201).build();
        else{
            return Response.status(404).build();
        }

    }

    // Segundo Punto
    @GET
    @ApiOperation(value = "get all users sort by Surname", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/usersSortBySurname")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersSortBySurname() {

        List<User> users = this.om.usersByAlphabet();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};

        return Response.status(201).entity(entity).build();
    }

    // Tercer Punto
    @POST
    @ApiOperation(value = "Login de usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Login incorrecto")
    })
    @Path("/loginUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(Credentials credentials) {
        int aux = this.om.loginUser(credentials.getEmail(), credentials.getPassword());
        if (aux == 1)  return Response.status(404).build();
        else{
            return Response.status(201).build();
        }
    }

    // Cuarto Punto
    @POST
    @ApiOperation(value = "create a new object", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/newObject")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newObject(ObjectClass obj) {
        this.om.addObject(obj.getObjectId(), obj.getObjectName(), obj.getDescription(), obj.getCoins());
        return Response.status(201).build();
    }

    // Quinto Punto
    @GET
    @ApiOperation(value = "get all objects sort by descendent price", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ObjectClass.class, responseContainer="List"),
    })
    @Path("/objectsSortByDescendentPrice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjectsSortByDescendentPrice() {
        List<ObjectClass> Objs = this.om.objectByDescendentPrice();
        GenericEntity<List<ObjectClass>> entity = new GenericEntity<List<ObjectClass>>(Objs) {};
        return Response.status(201).entity(entity).build();
    }

    /*
    @DELETE
    @ApiOperation(value = "delete a Object", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Object not found")
    })
    @Path("/{id}")
    public Response deleteObject(@PathParam("id") String id) {
        Object t = this.om.getObject(id);
        if (t == null) return Response.status(404).build();
        else this.om.deleteObject(id);
        return Response.status(201).build();
    }
    */

    // Sexto Punto
    @PUT
    @ApiOperation(value = "comprar un objecto por el usuario x", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "No existe usuario"),
            @ApiResponse(code = 405, message = "No hay saldo suficiente")
    })
    @Path("/comprarObjecto/{Id}/{objId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response processOrder(@PathParam("Id") String userId, @PathParam("objId") String objectId) {

        int comp = this.om.compraObjectos(userId, objectId);
        if (comp == 0) return Response.status(201).build();
        else if (comp == 1) {
            return Response.status(404).build();
        }
        else{
            return Response.status(405).build();
        }
    }


    // Septimo Punto
    @GET
    @ApiOperation(value = "get all objects bought by an user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ObjectClass.class, responseContainer="List"),
    })
    @Path("/objectsBoughtByUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjectsBoughtByUse(@PathParam("id") String userId) {
        List<ObjectClass> Objs = this.om.compraUser(userId);
        GenericEntity<List<ObjectClass>> entity = new GenericEntity<List<ObjectClass>>(Objs) {};
        return Response.status(201).entity(entity).build();
    }

}
