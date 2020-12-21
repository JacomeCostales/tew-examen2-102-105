package impl.tew.business;

import impl.tew.business.classes.*;


import java.util.List;

import com.tew.business.*;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Publicacion;

/**
 * Clase de implementación (una de las posibles) del interfaz de la fachada de
 * servicios
 * 
 * @author Enrique
 * 
 */
public class SimplePublicacionesService implements PublicacionesService {

	@Override
	public List<Publicacion> getPublicacionesPropias(String email,String orderCol) throws Exception{
		return new PublicacionesPropiasListado().getPublicacionesPropias(email,orderCol);
	}
	@Override
	public List<Publicacion> getPublicacionesAmigos(String email) throws Exception{
		return new PublicacionesAmigosListado().getPublicacionesAmigos(email);
	}

	@Override
	public void savePublicacion(Publicacion publicacion) throws EntityAlreadyExistsException {
		new PublicacionesAlta().save(publicacion);
	}

	@Override
	public void updatePublicacion(Publicacion publicacion) throws EntityNotFoundException {
		new PublicacionesUpdate().update(publicacion);
	}

	@Override
	public void deletePublicacion(int id) throws EntityNotFoundException {
		new PublicacionesBaja().delete(id);
	}

	@Override
	public Publicacion findById(int id) throws EntityNotFoundException {
		return new PublicacionesBuscar().find(id);
	}
}
