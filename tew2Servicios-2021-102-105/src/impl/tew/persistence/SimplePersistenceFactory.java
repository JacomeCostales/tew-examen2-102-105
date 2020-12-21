package impl.tew.persistence;


import com.tew.persistence.AdministradorDao;
import com.tew.persistence.AlumnoDao;
import com.tew.persistence.AmigosDao;
import com.tew.persistence.PersistenceFactory;
import com.tew.persistence.PublicacionDao;
import com.tew.persistence.UsuarioDao;

public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public AlumnoDao createAlumnoDao() {
		return new AlumnoJdbcDao();
	}

	@Override
	public UsuarioDao createUsuarioDao() {
		return new UsuarioJdbcDao();
	}
	
	@Override
	public PublicacionDao createPublicacionDao() {
		return new PublicacionJdbcDao();
	}
	
	@Override
	public AmigosDao createAmigosDao() {
		return new AmigosJdbcDao();
	}

	@Override
	public AdministradorDao createAdministradorDao() {
		
		return new AdministradorJdbcDao();
	}

}
