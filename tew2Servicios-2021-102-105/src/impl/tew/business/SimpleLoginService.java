package impl.tew.business;

import impl.tew.business.classes.*;


import java.util.List;

import com.tew.business.LoginService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.User;
import com.tew.model.Usuario;

/**
 * Clase de implementación (una de las posibles) del interfaz de la fachada de
 * servicios
 * 
 * @author Enrique
 * 
 */
public class SimpleLoginService implements LoginService {
	
	public User verify(String login, String password) 
	{
		if (! validLogin(login, password)) return null;
		String rol = new UsuariosListado().getRol(login);
		return new User(login, rol);
	}
	
	public boolean validLogin(String login, String password) 
	{
		List<Usuario> U = new UsuariosListado().getInicioSesion(login, password);
		if(U.isEmpty())
		{
			return false;
		}
		Usuario usuario = U.get(0);
		return usuario.getEmail().equals(login) && usuario.getPasswd().equals(password);
	}

	@Override
	public void registry(Usuario usuarioRegistrar) 
	{
		try {
			new UsuariosAlta().save(usuarioRegistrar);
		} catch (EntityAlreadyExistsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Usuario compruebaExiste(String emailRegistrado) 
	{
		Usuario usu = null;
		
		try {
			usu = new UsuariosBuscar().find(emailRegistrado);
			System.out.println("Encontrado"+usu.getEmail());
		} catch (EntityNotFoundException e) {
			
			return usu;
		}
		
		return usu;
		
	}

}