package impl.tew.business.resteasy;
import java.util.HashSet;
import java.util.List;
import com.tew.business.resteasy.AdministradorServicesRs;
import com.tew.business.resteasy.AlmacenServidor;
import com.tew.model.User;
import com.tew.persistence.exception.NotPersistedException;
import impl.tew.persistence.AdministradorJdbcDao;

public class AdministradorServicesRsImpl implements AdministradorServicesRs{

	@Override

	public void borrarUsarios(List<String> a,String N, String T) throws NotPersistedException
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			HashSet<User> usuariosLogged = (HashSet<User>) AlmacenServidor.getAlmacen().getUsuariosLogged();
			boolean borrar = true;
			for(User u : usuariosLogged) 
			{
				if(u.getLogin().equals(a.get(0))){borrar=false;}
			}
			if(borrar)new AdministradorJdbcDao().borrarUsarios(a);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
		}
	}

	@Override
	public void reiniciarBD(String N, String T) throws Exception 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			new AdministradorJdbcDao().reiniciarBD();
			AlmacenServidor.getAlmacen().getUsuariosLogged().clear();
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
		}
	}

	@Override
	public void borrarUsarios(List<String> a) throws NotPersistedException 
	{

	}

	@Override
	public void reiniciarBD() throws Exception
	{

	}


}
