package impl.tew.business.resteasy;
import com.tew.business.resteasy.AlmacenServidor;
import com.tew.business.resteasy.LoginServicesRs;
import com.tew.model.User;
import com.tew.model.Usuario;

import impl.tew.business.SimpleLoginService;

public class LoginServicesRsImpl implements LoginServicesRs
{

	@Override
	public User verify(String name, String password) 
	{
		User usuario = new SimpleLoginService().verify(name, password);
		if(usuario != null)AlmacenServidor.getAlmacen().getUsuariosLogged().add(usuario);
		return usuario;
		
	}

	@Override
	public boolean validLogin(String name, String password, String N, String T) 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			return new SimpleLoginService().validLogin(name, password);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
			return false;
		}
	}
	@Override
	public void registry(Usuario usuarioRegistrar) 
	{
		new SimpleLoginService().registry(usuarioRegistrar);
	}

	@Override
	public Usuario compruebaExiste(String emailRegistrado, String N, String T) 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			
			return new SimpleLoginService().compruebaExiste(emailRegistrado);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
			return null;
		}
	}

	@Override
	public void logout(Usuario usuario, String N, String T) 
	{
		if(usuario != null && AlmacenServidor.getAlmacen().autentica(N,T))
		{
			System.out.println("Exito al intentar hacer logout");
			User u = new User(usuario.getEmail(), usuario.getRol());
			AlmacenServidor.getAlmacen().getUsuariosLogged().remove(u);
		}
		else
		{
			System.out.println("Error al intentar hacer logout");
		}
	}

	@Override
	public Usuario compruebaExiste(String emailRegistrado) 
	{
		return null;
	}


}
