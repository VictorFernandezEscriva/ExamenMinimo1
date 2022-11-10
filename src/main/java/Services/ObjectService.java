package Services;

import Entity.V0.Actividad;
import Manager.*;
import Entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Api(value = "/Partidas", description = "Endpoint to Partida Service")
@Path("/Partidas")

public class PartidaService {

    private PartidaManager om;

    public PartidaService() {
        this.om = PartidaManagerImpl.getInstance();
        if (om.size()==0) {
            this.om.addJuego("3001", "Juegoderol", 3);
            this.om.addJuego("3002", "Juegodetiros", 2);
            this.om.addJuego("3003", "Juegodeestrategia", 4);
            this.om.addUser("Aida", "Fernandez", "13/06/2001", "email1", "Victor");
            this.om.addUser("Esteban", "Fernandez", "3/12/1969", "email2", "Jose");
            this.om.addUser("Lidia",  "Escriva", "30/12/1970", "email3", "Lidia");
        }
    }

    //Primer punto
    @POST
    @ApiOperation(value = "create a new juego", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
    })
    @Path("/newJuego")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newJuego(Juego juego) {
        this.om.addJuego(juego.getJuegoId(), juego.getJuegoDescription(), juego.getNivelMaximo());
        return Response.status(201).build();
    }

    // Segundo Punto
    @GET
    @ApiOperation(value = "iniciar Partida", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/Partida")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPartida() {

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
    public Response loginUser(Actividad credentials) {
        int aux = this.om.loginUser(credentials.getEmail(), credentials.getPassword());
        if (aux == 1)  return Response.status(404).build();
        else{
            return Response.status(201).build();
        }
    }

    // Cuarto Punto
    @POST
    @ApiOperation(value = "create a new Partida", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/newPartida")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPartida(Juego obj) {
        this.om.addPartida(obj.getPartidaId(), obj.getPartidaName(), obj.getDescription(), obj.getCoins());
        return Response.status(201).build();
    }

    // Quinto Punto
    @GET
    @ApiOperation(value = "get all Partidas sort by descendent price", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Juego.class, responseContainer="List"),
    })
    @Path("/PartidasSortByDescendentPrice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPartidasSortByDescendentPrice() {
        List<Juego> Objs = this.om.PartidaByDescendentPrice();
        GenericEntity<List<Juego>> entity = new GenericEntity<List<Juego>>(Objs) {};
        return Response.status(201).entity(entity).build();
    }

    /*
    @DELETE
    @ApiOperation(value = "delete a Partida", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Partida not found")
    })
    @Path("/{id}")
    public Response deletePartida(@PathParam("id") String id) {
        Partida t = this.om.getPartida(id);
        if (t == null) return Response.status(404).build();
        else this.om.deletePartida(id);
        return Response.status(201).build();
    }
    */

    // Sexto Punto
    @PUT
    @ApiOperation(value = "comprar un Partidao por el usuario x", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "No existe usuario"),
            @ApiResponse(code = 405, message = "No hay saldo suficiente")
    })
    @Path("/comprarPartidao/{Id}/{objId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response processOrder(@PathParam("Id") String userId, @PathParam("objId") String PartidaId) {

        int comp = this.om.compraPartidaos(userId, PartidaId);
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
    @ApiOperation(value = "get all Partidas bought by an user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Juego.class, responseContainer="List"),
    })
    @Path("/PartidasBoughtByUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPartidasBoughtByUse(@PathParam("id") String userId) {
        List<Juego> Objs = this.om.compraUser(userId);
        GenericEntity<List<Juego>> entity = new GenericEntity<List<Juego>>(Objs) {};
        return Response.status(201).entity(entity).build();
    }

}
