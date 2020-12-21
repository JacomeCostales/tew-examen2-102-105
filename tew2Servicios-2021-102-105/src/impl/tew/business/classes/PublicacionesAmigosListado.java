package impl.tew.business.classes;

import java.util.List;

import com.tew.infrastructure.Factories;
import com.tew.model.Publicacion;
import com.tew.persistence.PublicacionDao;


public class PublicacionesAmigosListado {

	public List<Publicacion> getPublicacionesAmigos(String email) throws Exception {
		
		PublicacionDao dao = Factories.persistence.createPublicacionDao();
		return  dao.getPublicacionesAmigos(email);

	}
}
