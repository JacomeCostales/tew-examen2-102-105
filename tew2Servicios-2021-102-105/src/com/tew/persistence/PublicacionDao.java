package com.tew.persistence;

import java.util.List;

import com.tew.model.Publicacion;
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
public interface PublicacionDao {

	List<Publicacion> getPublicacionesPropias(String email,String orderCol);
	List<Publicacion> getPublicacionesAmigos(String email);
	void save(Publicacion a) throws AlreadyPersistedException;
	void update(Publicacion a) throws NotPersistedException;
	void delete(int id) throws NotPersistedException;
	Publicacion findById(int id);

}