package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Usuario;

/**
 * 
 * @author Jácome y Miguel
 *
 */
public interface UsuariosService {

	List<Usuario> getUsuarios() throws Exception;
	Usuario findByEmail(String email) throws EntityNotFoundException;
	void saveUsuario(Usuario usuario) throws EntityAlreadyExistsException;
	void updateUsuario(Usuario usuario) throws EntityNotFoundException;
	void deleteUsuario(String email) throws EntityNotFoundException;
	List<Usuario> getUsuariosFiltrados(String textoFiltro, String textoFiltro2) throws Exception;
	List<Usuario> getListadoPeticiones(String email);
	List<Usuario> listadoEnvios(String email);

}