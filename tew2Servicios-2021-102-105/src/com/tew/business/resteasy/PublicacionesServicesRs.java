package com.tew.business.resteasy;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.tew.business.PublicacionesService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Publicacion;

@Path("/PublicacionesServicesRs")
public interface PublicacionesServicesRs extends PublicacionesService {

	@GET
	@Path("getPublicacionesPropias/{email}/{order}/{N}/{T}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Publicacion> getPublicacionesPropias(@PathParam("email") String email,@PathParam("order") String order, @PathParam("N") String N, @PathParam("T") String T) throws Exception;
	
	@GET
	@Path("getPublicacionesAmigos/{email}/{N}/{T}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Publicacion> getPublicacionesAmigos(@PathParam("email") String email, @PathParam("N") String N, @PathParam("T") String T) throws Exception;
	
	@GET
	@Path("findById/{id}/{N}/{T}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Publicacion findById(@PathParam("id") int id, @PathParam("N") String N, @PathParam("T") String T) throws EntityNotFoundException;
	
	@PUT
	@Path("savePublicacion/{N}/{T}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void savePublicacion(Publicacion publicacion, @PathParam("N") String N, @PathParam("T") String T) throws EntityAlreadyExistsException;
	
	@POST
	@Path("updatePublicacion/{N}/{T}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void updatePublicacion(Publicacion publicacion, @PathParam("N") String N, @PathParam("T") String T) throws EntityNotFoundException;
	
	@DELETE
	@Path("deletePublicacion/{N}/{T}")
	void deletePublicacion(int id, @PathParam("N") String N, @PathParam("T") String T) throws EntityNotFoundException;
}
