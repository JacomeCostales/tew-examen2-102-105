package com.tew.business.resteasy;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.tew.business.AdministradorService;
import com.tew.persistence.exception.NotPersistedException;


@Path("/AdministradorServicesRs")
public interface AdministradorServicesRs extends AdministradorService
{
	
	@DELETE
	@Path("{a}/{N}/{T}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void borrarUsarios(@PathParam("a") List<String> a, @PathParam("N") String N, @PathParam("T") String T)  throws NotPersistedException;
	
	@POST
	@Path("{N}/{T}")
	void reiniciarBD(@PathParam("N") String N, @PathParam("T") String T) throws Exception;
}
