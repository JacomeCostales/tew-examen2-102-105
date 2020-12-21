package impl.tew.business.classes;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.infrastructure.Factories;
import com.tew.model.Usuario;
import com.tew.persistence.UsuarioDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class UsuariosAlta {

	public void save(Usuario usuario) throws EntityAlreadyExistsException {
		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		try {
			dao.save(usuario);
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("Usuario ya existe " + usuario, ex);
		}
	}

}
