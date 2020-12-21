package com.tew.persistence;

import java.util.List;

import com.tew.model.Usuario;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

/**
 * Interfaz de la fachada a servicios de persistencia para la entidad Alumno.
 * 
 * En esta versi��n aparecen los otros m��todos b��sicos de un servicio 
 * de persistencia
 * 
 * @author alb
 *
 */
public interface UsuarioDao {

	List<Usuario> getUsuario();
	void save(Usuario a) throws AlreadyPersistedException;
	void update(Usuario a) throws NotPersistedException;
	void delete(String email) throws NotPersistedException;
	Usuario findByEmail(String email);
	List<Usuario> getUsuarioFiltrado(String textoFiltro, String textoFiltro2);
	List<Usuario> getListadoPeticiones(String email);
	List<Usuario> getListadoEnvios(String email);
	List<Usuario> getInicioSesion(String login, String password);
	String getRol(String login);

}