package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Publicacion;

/**
 * 
 * @author Jácome y Miguel
 *
 */
public interface PublicacionesService {

	List<Publicacion> getPublicacionesPropias(String email,String orderCol) throws Exception;
	List<Publicacion> getPublicacionesAmigos(String email) throws Exception;
	Publicacion findById(int id) throws EntityNotFoundException;
	void savePublicacion(Publicacion publicacion) throws EntityAlreadyExistsException;
	void updatePublicacion(Publicacion publicacion) throws EntityNotFoundException;
	void deletePublicacion(int id) throws EntityNotFoundException;

}