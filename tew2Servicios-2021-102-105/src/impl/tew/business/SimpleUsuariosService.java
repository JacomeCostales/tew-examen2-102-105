package impl.tew.business;

import impl.tew.business.classes.*;


import java.util.List;

import com.tew.business.*;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Usuario;

/**
 * Clase de implementación (una de las posibles) del interfaz de la fachada de
 * servicios
 * 
 * @author Enrique
 * 
 */
public class SimpleUsuariosService implements UsuariosService {

	@Override
	public List<Usuario> getUsuarios() throws Exception{
		return new UsuariosListado().getUsuarios();
	}

	@Override
	public void saveUsuario(Usuario alumno) throws EntityAlreadyExistsException {
		new UsuariosAlta().save(alumno);
	}

	@Override
	public void updateUsuario(Usuario alumno) throws EntityNotFoundException {
		new UsuariosUpdate().update(alumno);
	}

	@Override
	public void deleteUsuario(String email) throws EntityNotFoundException {
		new UsuariosBaja().delete(email);
	}

	@Override
	public Usuario findByEmail(String email) throws EntityNotFoundException {
		return new UsuariosBuscar().find(email);
	}


	@Override
	public List<Usuario> getListadoPeticiones(String email) {
		return new UsuariosListado().getListadoPeticiones(email);
	}

	@Override
	public List<Usuario> listadoEnvios(String email) {
		return new UsuariosListado().getListadoEnvios(email);
	}

	@Override
	public List<Usuario> getUsuariosFiltrados(String textoFiltro, String textoFiltro2) throws Exception {
		 return new UsuariosListado().getUsuariosFiltrados(textoFiltro, textoFiltro2);
	}
}
