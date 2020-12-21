package com.tew.presentation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.*;
import com.tew.infrastructure.Factories;
import com.tew.model.Usuario;

@ManagedBean (name="usuarios")
@SessionScoped
public class BeanUsuarios implements Serializable{
	      private static final long serialVersionUID = 555570L;
	     
          private Usuario[] usuarios = null;
          
          private List<Usuario> users = null;
          
          @ManagedProperty(value="#{sesion}") 
          private BeanSesion sesion;
          public BeanSesion getSesion() {return sesion;}
		  public void setSesion(BeanSesion sesion) {this.sesion = sesion;}
		  
        
          @ManagedProperty(value="#{usuario}") 
          private BeanUsuario usuario;
          
          public BeanUsuario getUsuario() { return usuario; }
          public void setUsuario(BeanUsuario usuario) {this.usuario = usuario;}
          
		  public Usuario[] getUsuarios () {return(usuarios);}
	      public void setUsuarios(Usuario[] usuarios) {this.usuarios = usuarios;} 
	       
	      
	      public List<Usuario> getUsers() {
			return users;
		}
		public void setUsers(List<Usuario> users) {
			this.users = users;
		}
		public void iniciaUsuario(ActionEvent event) 
		{
			usuario.setNombre("POR_DEFECTO");
			usuario.setPasswd("POR_DEFECTO");
			usuario.setRol("POR_DEFECTO");
			usuario.setEmail("POR_DEFECTO");
		}
	       
	       public String listadoEnvios()
	       {
	    	   UsuariosService service;
				  try {
					service = Factories.services.createUsuariosService();
					usuarios = (Usuario [])service.listadoEnvios(sesion.getEmail()).toArray(new Usuario[0]);
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
	       }
	       
	       public String listadoPeticiones() {
		       UsuariosService service;    
				  try {
					service = Factories.services.createUsuariosService();
					usuarios = (Usuario [])service.getListadoPeticiones(sesion.getEmail()).toArray(new Usuario[0]);
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
				  
		 	  }
	       
	       public String filtrarUsuarios(String textoFiltro)
	       {
	    	   UsuariosService service;
				  try {
					service = Factories.services.createUsuariosService();
					usuarios = (Usuario [])service.getUsuariosFiltrados(sesion.getEmail(), textoFiltro).toArray(new Usuario[0]);
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
	       }
	       
	       public String listado() {
	    	  
		       UsuariosService service;
				  try {
					service = Factories.services.createUsuariosService();
					usuarios = (Usuario [])service.getUsuarios().toArray(new Usuario[0]);
					users.clear();
					for(Usuario u :usuarios) {if(!u.getRol().equals("admin")) {users.add(u);}}

					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
				  
		 	  }
	       public String baja(Usuario usuario) {
		       UsuariosService service;
				  try {
					service = Factories.services.createUsuariosService();
					service.deleteUsuario(usuario.getEmail());				 
					usuarios = (Usuario [])service.getUsuarios().toArray(new Usuario[0]);
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
				  
		 	  }
	       public String edit() {
	    	   UsuariosService service;
				  try {
					service = Factories.services.createUsuariosService();
					usuario = (BeanUsuario) service.findByEmail(usuario.getEmail());
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
				  
		 	  }
	       
	       public String salva() {
	    	   UsuariosService service;
				  try {
					  service = Factories.services.createUsuariosService();
		
					if (usuario.getEmail() == null) {
						service.saveUsuario(usuario);
					}
					else {
						service.updateUsuario(usuario); 
					} 
	
					usuarios = (Usuario [])service.getUsuarios().toArray(new Usuario[0]);
					return "exito";
					
				  } catch (Exception e) {
					  e.printStackTrace();
					return "error";
				  }
				  
		 	  }
	       
	     @PostConstruct
	     public void init() {    	  
	       System.out.println("BeanUsuarios - PostConstruct"); 
	       //Buscamos el alumno en la sesión. Esto es un patrón factoría claramente.
	       usuario = (BeanUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("usuario"));
	       //si no existe lo creamos e inicializamos
	       if (usuario == null) { 
	         System.out.println("BeanUsuarios - No existia");
	         usuario = new BeanUsuario();
	         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "usuario", usuario);
	       }
	       sesion = (BeanSesion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("sesion"));
	       if (sesion == null) { 
		         System.out.println("BeanSesion - No existia");
		         sesion = new BeanSesion();
		         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "sesion", sesion);
		       }
	       System.out.println("Sesion inyectada:" + sesion.getEmail());
	       
	       users = new ArrayList<Usuario>();
	     }
	     @PreDestroy
	     public void end()  {
	         System.out.println("BeanUsuarios - PreDestroy");
	     }

	}


	
