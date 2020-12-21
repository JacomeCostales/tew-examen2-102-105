package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Amigos;
import com.tew.persistence.AmigosDao;
import com.tew.persistence.exception.NotPersistedException;

public class AmigosListaBaja {

	public void delete(Amigos amigos) throws EntityNotFoundException {
		AmigosDao dao = Factories.persistence.createAmigosDao();
		try {
			dao.delete(amigos);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Amigos no eliminado " + amigos, ex);
		}
	}
}
