package impl.tew.business.classes;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.infrastructure.Factories;
import com.tew.model.Amigos;
import com.tew.persistence.AmigosDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class AmigosListaAlta 
{

	public void save(Amigos amigos) throws EntityAlreadyExistsException 
	{
		AmigosDao dao = Factories.persistence.createAmigosDao();
		try {
			dao.save(amigos);
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("Amigos ya existen " + amigos, ex);
		}
	}
	
	

}
