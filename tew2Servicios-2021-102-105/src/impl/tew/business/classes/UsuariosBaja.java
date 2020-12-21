package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.persistence.UsuarioDao;
import com.tew.persistence.exception.NotPersistedException;

public class UsuariosBaja {

	public void delete(String email) throws EntityNotFoundException {
		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		try {
			dao.delete(email);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Usuario no eliminado " + email, ex);
		}
	}
}
