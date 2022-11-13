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
            this.om.addUser("2001","Victor");
            this.om.addUser("2002","Lidia");
            this.om.addUser("2003","Jose");
            this.om.inicioPartida("2002", "3001", "12/11/2022");
        }
    }

    @POST
    @ApiOperation(value = "create a new user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
    })
    @Path("/newUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {
        this.om.addUser(user.getUserId(), user.getUserName());
        return Response.status(201).build();
    }

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

    // 0 No hay partida activa 1 hay ya una partida activa 2 no existe el juego o el usuario
    @POST
    @ApiOperation(value = "iniciar una partida", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Ya tiene una partida activa"),
            @ApiResponse(code = 405, message = "No existe el juego o el usuario")
    })
    @Path("/newGame")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newGame(Partida partida) {
        int aux=this.om.inicioPartida(partida.getUserId(), partida.getJuegoId(), partida.getActividad().get(0).getDate());
        if(aux==1){
            return Response.status(404).build();
        } else if (aux==2) {
            return Response.status(405).build();
        } else{
            return Response.status(201).build();
        }

    }

    // 0 no existe usuario, 1000 Ninguna partida activa, x nivel actual
    /*
    @GET
    @ApiOperation(value = "Nivel Actual", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Integer, responseContainer="List"),
    })
    @Path("/NivelActual")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPartida() {

        int a = this.om.nivelActual();

        GenericEntity<Integer> entity = new GenericEntity<Integer>() {};

        return Response.status(201).entity(entity).build();
    }



    // 0 no existe usuario, 1000 Ninguna partida activa, x puntuacion actual
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
    */

    // 0 no existe el usuario, 1000 partida inactiva, 1 bien
    @PUT
    @ApiOperation(value = "pasar de nivel", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "No existe el usuario"),
            @ApiResponse(code = 405, message = "Usuario sin partidas activas")
    })
    @Path("/pasarNivel/{userId}/{puntos}/{fecha}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pasarNivel(@PathParam("userId") String userId, @PathParam("puntos") int puntos, @PathParam("fecha") String fecha) {

        int comp = this.om.pasarNivel(userId,puntos,fecha);
        if (comp == 1) return Response.status(201).build();
        else if (comp == 0) {
            return Response.status(404).build();
        }
        else{
            return Response.status(405).build();
        }
    }

    // 0 si no existe la id de usuario, 1 si OK, 2 si no tiene ninguna partida activa
    @PUT
    @ApiOperation(value = "finalizar la partida", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "No existe el usuario"),
            @ApiResponse(code = 405, message = "Usuario sin partidas activas")
    })
    @Path("/pasarNivel/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response finalizarPartida(@PathParam("userId") String userId) {

        int comp = this.om.finalizarPartida(userId);
        if (comp == 1) return Response.status(201).build();
        else if (comp == 0) {
            return Response.status(404).build();
        }
        else{
            return Response.status(405).build();
        }
    }


    @GET
    @ApiOperation(value = "Partidas en las que ha participado el usuario X", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Partida.class, responseContainer="List"),
    })
    @Path("/partidasUsuario/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response partidasDeUser(@PathParam("userId") String userId) {
        List<Partida> part = this.om.partidasDeUser(userId);
        GenericEntity<List<Partida>> entity = new GenericEntity<List<Partida>>(part) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "La actividad de un usuario en una partida", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Partida.class, responseContainer="List"),
    })
    @Path("/actividadUsuario/{userId}/{juegoId}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actividadUserPartida(@PathParam("userId") String userId, @PathParam("juegoId") String juegoId) {
        List<Actividad> part = this.om.actividadUserPartida(userId,juegoId);
        GenericEntity<List<Actividad>> entity = new GenericEntity<List<Actividad>>(part) {};
        return Response.status(201).entity(entity).build();
    }
}


