package impl.tew.business.resteasy;
import java.util.List;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.AlmacenServidor;
import com.tew.business.resteasy.UsuariosServicesRs;
import com.tew.model.Usuario;
import impl.tew.business.classes.UsuariosAlta;
import impl.tew.business.classes.UsuariosBaja;
import impl.tew.business.classes.UsuariosBuscar;
import impl.tew.business.classes.UsuariosListado;
import impl.tew.business.classes.UsuariosUpdate;

public class UsuariosServicesRsImpl implements UsuariosServicesRs
{

	@Override
	public List<Usuario> getUsuarios( String N, String T) throws Exception 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			try 
			{
				
				return new UsuariosListado().getUsuarios();
			} 
		
			catch (Exception e) 
			{
					e.printStackTrace();
					return null;
			}
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
			return null;
		}
	}

	@Override
	public Usuario findByEmail(String email, String N, String T) throws EntityNotFoundException 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			return new UsuariosBuscar().find(email);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
			return null;
		}
	}

	@Override
	public void saveUsuario(Usuario usuario, String N, String T) throws EntityAlreadyExistsException 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			new UsuariosAlta().save(usuario);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
		}
	}

	@Override
	public void updateUsuario(Usuario usuario, String N, String T) throws EntityNotFoundException 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			new UsuariosUpdate().update(usuario);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
		}
	}

	@Override
	public void deleteUsuario(String email, String N, String T) throws EntityNotFoundException 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			new UsuariosBaja().delete(email);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
		}
	}

	//Aqui le pasamos primero el email del usuario en sesion y despues el texto a filtrar para mostrar los usuarios adecuados
	@Override
	public List<Usuario> getUsuariosFiltrados(String a, String b, String N, String T) throws Exception 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			return new UsuariosListado().getUsuariosFiltrados(a, b);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
			return null;
		}
	}

	//Muestra los usuarios a los que puede enviar invitacion el usuario con email pasado como parametro
	@Override
	public List<Usuario> getListadoPeticiones(String email, String N, String T) 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			System.out.println(email);
			return new UsuariosListado().getListadoPeticiones(email);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
			return null;
		}
	}

	@Override
	public List<Usuario> listadoEnvios(String email, String N, String T) 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			System.out.println(email);
			return new UsuariosListado().getListadoEnvios(email);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
			return null;
		}
	}

	@Override
	public List<Usuario> getUsuarios() throws Exception 
	{
		return null;
	}

	@Override
	public Usuario findByEmail(String email) throws EntityNotFoundException
	{
		return null;
	}

	@Override
	public void saveUsuario(Usuario usuario) throws EntityAlreadyExistsException 
	{
		
		
	}

	@Override
	public void updateUsuario(Usuario usuario) throws EntityNotFoundException
	{
		
	}

	@Override
	public void deleteUsuario(String email) throws EntityNotFoundException 
	{
		
	}

	@Override
	public List<Usuario> getUsuariosFiltrados(String textoFiltro, String textoFiltro2) throws Exception 
	{
		return null;
	}

	@Override
	public List<Usuario> getListadoPeticiones(String email) 
	{
		return null;
	}

	@Override
	public List<Usuario> listadoEnvios(String email) 
	{
		return null;
	}

}
