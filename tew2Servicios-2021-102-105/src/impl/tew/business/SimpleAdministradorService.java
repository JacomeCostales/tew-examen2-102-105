package impl.tew.business;



import java.util.List;

import com.tew.business.AdministradorService;
import com.tew.infrastructure.Factories;
import com.tew.persistence.AdministradorDao;
import com.tew.persistence.exception.NotPersistedException;

public class SimpleAdministradorService implements AdministradorService {

	@Override
	public void borrarUsarios(List<String> a) throws NotPersistedException {
		AdministradorDao dao = Factories.persistence.createAdministradorDao();
		try {
			dao.borrarUsarios(a);
		}
		catch (NotPersistedException ex) {
			throw new NotPersistedException("Alumno no existe ", ex);
		}
		
	}

	@Override
	public void reiniciarBD() throws Exception{
		AdministradorDao dao = Factories.persistence.createAdministradorDao();
		try {
			dao.reiniciarBD();
		}
		catch (Exception ex) {
			throw new Exception(ex);
		}
		
	}

	
}
