package impl.tew.business.classes;

import java.util.List;

import com.tew.infrastructure.Factories;
import com.tew.model.Publicacion;
import com.tew.persistence.PublicacionDao;


public class PublicacionesPropiasListado {

	public List<Publicacion> getPublicacionesPropias(String email,String orderCol) throws Exception {
		
		PublicacionDao dao = Factories.persistence.createPublicacionDao();
		return  dao.getPublicacionesPropias(email,orderCol);

	}
}
