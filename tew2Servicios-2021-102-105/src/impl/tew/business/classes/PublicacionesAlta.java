package impl.tew.business.classes;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.infrastructure.Factories;
import com.tew.model.Publicacion;
import com.tew.persistence.PublicacionDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class PublicacionesAlta {

	public void save(Publicacion publicacion) throws EntityAlreadyExistsException {
		PublicacionDao dao = Factories.persistence.createPublicacionDao();
		try {
			dao.save(publicacion);
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("Publicacion ya existe " + publicacion, ex);
		}
	}

}
