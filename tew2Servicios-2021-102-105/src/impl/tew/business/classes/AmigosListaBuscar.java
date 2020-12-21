package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Amigos;
import com.tew.persistence.AmigosDao;

public class AmigosListaBuscar {

	public Amigos find(Amigos amigos) throws EntityNotFoundException {
		AmigosDao dao = Factories.persistence.createAmigosDao();
		Amigos a = dao.find(amigos);
		if ( a == null) {
			throw new EntityNotFoundException("No se ha encontrado la pareja de amigos");
		}
		
		return a;
	}

}
