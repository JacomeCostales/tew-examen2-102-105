package com.tew.business.resteasy;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.tew.business.LoginService;
import com.tew.model.User;
import com.tew.model.Usuario;

@Path("/LoginServicesRs")
public interface LoginServicesRs extends LoginService 
{

	@GET
	@Path("verify/{name}/{password}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	User verify(@PathParam("name") String name, @PathParam("password") String password);
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void registry(Usuario usuarioRegistrar);
	
	@GET
	@Path("compruebaExiste/{emailRegistrado}/{N}/{T}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Usuario compruebaExiste(@PathParam("emailRegistrado") String emailRegistrado, @PathParam("N") String N, @PathParam("T") String T);

	@GET
	@Path("validLogin/{name}/{password}/{N}/{T}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	boolean validLogin(@PathParam("name") String name, @PathParam("password") String password, @PathParam("N") String N, @PathParam("T") String T);
	
	@DELETE
	@Path("logout/{N}/{T}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void logout(Usuario usuario, @PathParam("N") String N, @PathParam("T") String T);
}
