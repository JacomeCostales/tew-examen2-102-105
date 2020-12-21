package com.tew.business.resteasy;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.tew.business.AmigosListaService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Amigos;

@Path("/AmigosServicesRs")
public interface AmigosServicesRs extends AmigosListaService
{
	 @GET
	 @Path("getAmigosLista/{N}/{T}")
	 @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 public List<Amigos> getAmigosLista(@PathParam("N") String N, @PathParam("T") String T) throws Exception; 
	 
	 @GET
	 @Path("find/{a}/{b}/{N}/{T}")
	 @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 Amigos find(@PathParam("a") String a, @PathParam("b") String b, @PathParam("N") String N, @PathParam("T") String T) throws EntityNotFoundException;
	 
	 
	 @DELETE
	 @Path("deleteAmigos/{a}/{b}/{N}/{T}")
	 void deleteAmigos(@PathParam("a") String a, @PathParam("b") String b, @PathParam("N") String N, @PathParam("T") String T) throws EntityNotFoundException;
	 
	 @PUT
	 @Path("saveAmigos/{N}/{T}")
	 @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 void saveAmigos(Amigos amigos, @PathParam("N") String N, @PathParam("T") String T) throws EntityAlreadyExistsException;
	 
	 @POST
	 @Path("updateAmigos/{N}/{T}")
	 @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 void updateAmigos(Amigos amigos, @PathParam("N") String N, @PathParam("T") String T) throws EntityNotFoundException;

	 @GET
	 @Path("getListadoPeticiones/{email}/{N}/{T}")
	 @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 public List<Amigos> getListadoPeticiones(@PathParam("email")String email ,@PathParam("N") String N, @PathParam("T") String T);
	 
}
