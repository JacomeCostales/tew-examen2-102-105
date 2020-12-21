package impl.tew.business.classes;

import java.util.List;

import com.tew.infrastructure.Factories;
import com.tew.model.Amigos;
import com.tew.persistence.AmigosDao;


public class AmigosListaListado {

	public List<Amigos> getAmigosLista() throws Exception {
		AmigosDao dao = Factories.persistence.createAmigosDao();
		return  dao.getAmigos();
		
	}

	public List<Amigos> getAmigosListaPeticiones(String email) 
	{
		AmigosDao dao = Factories.persistence.createAmigosDao();
		return  dao.getAmigosPeticiones(email);
	}
}
