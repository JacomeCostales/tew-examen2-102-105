package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Publicacion;
import com.tew.persistence.PublicacionDao;

public class PublicacionesBuscar {

	public Publicacion find(int id) throws EntityNotFoundException {
		PublicacionDao dao = Factories.persistence.createPublicacionDao();
		Publicacion a = dao.findById(id);
		if ( a == null) {
			throw new EntityNotFoundException("No se ha encontrado la Publicacion");
		}
		
		return a;
	}

}
