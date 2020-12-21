package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.persistence.PublicacionDao;
import com.tew.persistence.exception.NotPersistedException;

public class PublicacionesBaja {

	public void delete(int id) throws EntityNotFoundException {
		PublicacionDao dao = Factories.persistence.createPublicacionDao();
		try {
			dao.delete(id);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Publicacion no eliminado " + id, ex);
		}
	}
}
