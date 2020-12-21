//Registro de clases punto final.
//Es una forma de registro estático de clases punto final (servicios rest)
package com.tew.business.resteasy;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.tew.model.User;

import impl.tew.business.resteasy.*;

@SuppressWarnings("unchecked")
public class Application extends javax.ws.rs.core.Application 
{
	private Set<Class<?>> classes = new HashSet<Class<?>>();
	
	
	
	public Application() 
	{
		classes.add(AmigosServicesRsImpl.class);
		classes.add(UsuariosServicesRsImpl.class);
		classes.add(AdministradorServicesRsImpl.class);
		classes.add(PublicacionesServicesRsImpl.class);
		classes.add(LoginServicesRsImpl.class);
	}
	
	@Override
	public Set<Class<?>> getClasses() 
	{
		return classes;
	}
	
	@Override
	public Set<Object> getSingletons() 
	{
		return Collections.EMPTY_SET;
	}
	
	
	
}
