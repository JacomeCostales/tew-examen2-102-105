package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Usuario;
import com.tew.persistence.UsuarioDao;
import com.tew.persistence.exception.NotPersistedException;

public class UsuariosUpdate {

	public void update(Usuario usuario) throws EntityNotFoundException {
		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		try {
			dao.update(usuario);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Usuario no eliminado " + usuario, ex);
		}
	}

}
