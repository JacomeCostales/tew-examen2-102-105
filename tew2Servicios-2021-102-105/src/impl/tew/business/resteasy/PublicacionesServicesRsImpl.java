package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.AlmacenServidor;
import com.tew.business.resteasy.PublicacionesServicesRs;
import com.tew.model.Publicacion;

import impl.tew.business.classes.*;

public class PublicacionesServicesRsImpl implements PublicacionesServicesRs{

	
	public List<Publicacion> getPublicacionesPropias(String email, String order,String N, String T) throws Exception 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			try {
				return new PublicacionesPropiasListado().getPublicacionesPropias(email,order);
			} catch (Exception e) {

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
	public List<Publicacion> getPublicacionesAmigos(String email,String N, String T) throws Exception 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			try {
				return new PublicacionesAmigosListado().getPublicacionesAmigos(email);
			} catch (Exception e) {

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
	public Publicacion findById(int id,String N, String T) throws EntityNotFoundException 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			return new PublicacionesBuscar().find(id);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
			return null;
		}
	}

	@Override
	public void savePublicacion(Publicacion publicacion,String N, String T) throws EntityAlreadyExistsException 
	{
		System.out.println(publicacion.getTexto());
		
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			new PublicacionesAlta().save(publicacion);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
		}

	}

	@Override
	public void updatePublicacion(Publicacion publicacion,String N, String T) throws EntityNotFoundException 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			new PublicacionesUpdate().update(publicacion);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
		}

	}

	@Override
	public void deletePublicacion(int id,String N, String T) throws EntityNotFoundException 
	{
		if(AlmacenServidor.getAlmacen().autentica(N,T))
		{
			new PublicacionesBaja().delete(id);
		}
		else
		{
			System.out.println("Este usuario no se ha autenticado");
		}

	}

	@Override
	public List<Publicacion> getPublicacionesPropias(String email, String orderCol) throws Exception 
	{
		return null;
	}

	@Override
	public List<Publicacion> getPublicacionesAmigos(String email) throws Exception 
	{
		return null;
	}

	@Override
	public Publicacion findById(int id) throws EntityNotFoundException 
	{
		return null;
	}

	@Override
	public void savePublicacion(Publicacion publicacion) throws EntityAlreadyExistsException 
	{
	}

	@Override
	public void updatePublicacion(Publicacion publicacion) throws EntityNotFoundException 
	{

	}

	@Override
	public void deletePublicacion(int id) throws EntityNotFoundException
	{
	}

}
