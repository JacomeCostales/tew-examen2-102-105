package impl.tew.business.resteasy;
import com.tew.business.resteasy.AlmacenServidor;
import com.tew.business.resteasy.AmigosServicesRs;
import java.util.List;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Amigos;

import impl.tew.business.classes.AmigosListaAlta;
import impl.tew.business.classes.AmigosListaBaja;
import impl.tew.business.classes.AmigosListaBuscar;
import impl.tew.business.classes.AmigosListaListado;
import impl.tew.business.classes.AmigosListaUpdate;

public class AmigosServicesRsImpl implements AmigosServicesRs
{

	@Override
	public List<Amigos> getAmigosLista(String N, String T)
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			try 
			{
				return new AmigosListaListado().getAmigosLista();
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
	public Amigos find(String a, String b,String N, String T) throws EntityNotFoundException 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			Amigos amigos = new Amigos(a,b,true);
			System.out.println(a + " " + b);
			return new AmigosListaBuscar().find(amigos);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
			return null;
		}
	}

	@Override
	public void deleteAmigos(String a, String b,String N, String T) throws EntityNotFoundException
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			Amigos amigos = new Amigos(a,b,true);
			new AmigosListaBaja().delete(amigos);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
		}
	}

	@Override
	public void saveAmigos(Amigos amigos,String N, String T) throws EntityAlreadyExistsException 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			new AmigosListaAlta().save(amigos);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
		}
	}

	@Override
	public void updateAmigos(Amigos amigos,String N, String T) throws EntityNotFoundException 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			new AmigosListaUpdate().update(amigos);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
		}
	}

	@Override
	public List<Amigos> getListadoPeticiones(String email,String N, String T) 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			return new AmigosListaListado().getAmigosListaPeticiones(email);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
			return null;
		}
	}

	@Override
	public Amigos find(Amigos a) throws EntityNotFoundException 
	{
		return null;
	}

	@Override
	public void deleteAmigos(Amigos amigos) throws EntityNotFoundException 
	{

	}

	@Override
	public List<Amigos> getAmigosLista() throws Exception 
	{
		return null;
	}

	@Override
	public void saveAmigos(Amigos amigos) throws EntityAlreadyExistsException 
	{

	}

	@Override
	public void updateAmigos(Amigos amigos) throws EntityNotFoundException 
	{

	}

	@Override
	public List<Amigos> getListadoPeticiones(String email) 
	{
		return null;
	}

}
