package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Usuario;
import com.tew.persistence.UsuarioDao;

public class UsuariosBuscar {

	public Usuario find(String email) throws EntityNotFoundException {
		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		Usuario a = dao.findByEmail(email);
		if ( a == null) {
			throw new EntityNotFoundException("No se ha encontrado el usuario");
		}
		
		return a;
	}

}
