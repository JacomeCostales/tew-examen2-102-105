package com.tew.business.resteasy;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.tew.business.UsuariosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Usuario;

@Path("/UsuariosServicesRs")
public interface UsuariosServicesRs extends UsuariosService
{
	@GET
	@Path("getUsuarios/{N}/{T}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Usuario> getUsuarios(@PathParam("N") String N, @PathParam("T") String T) throws Exception;
	
	@GET
	@Path("findByEmail/{email}/{N}/{T}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Usuario findByEmail(@PathParam("email") String email, @PathParam("N") String N, @PathParam("T") String T) throws EntityNotFoundException;
	
	@PUT
	@Path("saveUsuario/{N}/{T}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void saveUsuario(Usuario usuario, @PathParam("N") String N, @PathParam("T") String T) throws EntityAlreadyExistsException;
	
	@POST
	@Path("updateUsuario/{N}/{T}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void updateUsuario(Usuario usuario, @PathParam("N") String N, @PathParam("T") String T) throws EntityNotFoundException;
	
	@DELETE
	@Path("deleteUsuario/{email}/{N}/{T}")
	void deleteUsuario(@PathParam("email") String email, @PathParam("N") String N, @PathParam("T") String T) throws EntityNotFoundException;
	
	@GET
	@Path("getUsuariosFiltrados/{a}/{b}/{N}/{T}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Usuario> getUsuariosFiltrados(@PathParam("a") String a, @PathParam("b") String b, @PathParam("N") String N, @PathParam("T") String T) throws Exception;
	
	@GET
	@Path("getListadoPeticiones/{email}/{N}/{T}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Usuario> getListadoPeticiones(@PathParam("email") String email, @PathParam("N") String N, @PathParam("T") String T);
	
	@GET
	@Path("listadoEnvios/{email}/{N}/{T}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Usuario> listadoEnvios(@PathParam("email") String email, @PathParam("N") String N, @PathParam("T") String T);
}
