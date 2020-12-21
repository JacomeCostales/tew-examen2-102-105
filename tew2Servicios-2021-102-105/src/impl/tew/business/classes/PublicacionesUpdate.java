package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Publicacion;
import com.tew.persistence.PublicacionDao;
import com.tew.persistence.exception.NotPersistedException;

public class PublicacionesUpdate {

	public void update(Publicacion publicacion) throws EntityNotFoundException {
		PublicacionDao dao = Factories.persistence.createPublicacionDao();
		try {
			dao.update(publicacion);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Publicacion no eliminado " + publicacion, ex);
		}
	}

}
