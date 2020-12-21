package com.tew.presentation;
import java.io.Serializable;
//import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.AmigosListaService;
import com.tew.infrastructure.Factories;
import com.tew.model.Amigos;
import com.tew.model.Usuario;

@ManagedBean
@SessionScoped
public class BeanAmigosLista implements Serializable{
	      private static final long serialVersionUID = 55586L;

          private Amigos[] amigosLista = null;
          public Amigos[] getAmigosLista () {return(amigosLista);}
	      public void setAmigosLista(Amigos[] amigosLista) {this.amigosLista = amigosLista;} 
	      
          //Inyecci�n de dependencia
          @ManagedProperty(value="#{amigos}") 
          private BeanAmigos amigos;
          public BeanAmigos getAmigos() { return amigos; }
          public void setAmigos(BeanAmigos amigos) {this.amigos = amigos;}
	      
	      @ManagedProperty(value="#{sesion}") 
          private BeanSesion sesion;
          public BeanSesion getSesion() {return sesion;}
		  public void setSesion(BeanSesion sesion) {this.sesion = sesion;}
		  
		  @ManagedProperty(value="#{usuarios}") 
          private BeanUsuarios usuarios;
          public BeanUsuarios getUsuarios() { return usuarios; }
          public void setUsuarios(BeanUsuarios usuarios) {this.usuarios = usuarios;}
		  
	      public void iniciaAmigos(ActionEvent event) {
			 //FacesContext facesContext = FacesContext.getCurrentInstance();
			 //ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
			 amigos.setEmail_usuario(null);
			 amigos.setEmail_amigo(null);
			 amigos.setAceptada(false);
			
			/* amigos.setEmail(bundle.getString("valorDefectoCorreo")); */     
		   }
	       
	      
	      public String listadoPeticiones() {
		       AmigosListaService service;    
				  try {
					service = Factories.services.createAmigosListaService();
					amigosLista = (Amigos [])service.getListadoPeticiones(sesion.getEmail()).toArray(new Amigos[0]);
					
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
				  
		 	  }
	      
	       public boolean compruebaPeticionAmistad(String email1, String email2)
	       {
	    	   boolean hayPeticionAmistad = false;
	    	   listado();
	    	   for(int i = 0; i < amigosLista.length; i++)
	    	   {
	    		   if((amigosLista[i].getEmail_usuario().equals(email1) &&  amigosLista[i].getEmail_amigo().equals(email2)) || (amigosLista[i].getEmail_usuario().equals(email2) &&  amigosLista[i].getEmail_amigo().equals(email1)) )
	    		   {
	    			     hayPeticionAmistad = true;
	    			     i = amigosLista.length;
	    		   }	   
	    	   }
	    	   return hayPeticionAmistad;
	       }
	       
	       public String aceptarAmigo(String email, Usuario usuario)
	       {
	    	   if(compruebaPeticionAmistad(email, usuario.getEmail()))
	    	   {
	    		   amigos.setEmail_usuario(usuario.getEmail());
		    	   amigos.setEmail_amigo(email);
		    	   amigos.setAceptada(true);
		    	   AmigosListaService service;
		    	   System.out.println(email);
					  try {
						service = Factories.services.createAmigosListaService();
						service.updateAmigos(amigos);					
						amigosLista = (Amigos [])service.getAmigosLista().toArray(new Amigos[0]);
						usuarios.listadoPeticiones();
						return "exito";
						
					  } catch (Exception e) {
						  e.printStackTrace();
						return "error";
					  }
	    	   }
	    	   
	    	   else
	    	   {
	    		   System.out.println("NO EXISTE ESA PETICION DE AMISTAD");
	    		   return "error";
	    	   }
	    	   
	       }
	       
	       public String agregarAmigo(String email, Usuario usuario)
	       {
	    	   
	    	   if(compruebaPeticionAmistad(email, usuario.getEmail()))
	    	   {
	    		   System.out.println("ESTA PETICION DE AMISTAD YA EXISTE, ESPERA A QUE SE ACEPTE");
	    		   return "exito";
	    	   }
	    	   
	    	   else
	    	   {
	    	
	    	   amigos.setEmail_usuario(email);
	    	   amigos.setEmail_amigo(usuario.getEmail());
	    	   amigos.setAceptada(false);
	    	   AmigosListaService service;
	    	   System.out.println(email);
				  try {
					  
					service = Factories.services.createAmigosListaService();
					service.saveAmigos(amigos);					
					amigosLista = (Amigos [])service.getAmigosLista().toArray(new Amigos[0]);
					usuarios.listadoEnvios();
					return "exito";
					
				  } catch (Exception e) {
					  e.printStackTrace();
					return "error";
				  }
	    	   }
				  
	       }
	       
	       public String listado() {
		       AmigosListaService service;
				  try {
				  
					service = Factories.services.createAmigosListaService();
					amigosLista = (Amigos [])service.getAmigosLista().toArray(new Amigos[0]);
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
				  
		 	  }
	       public String baja(Amigos amigos) {
		       AmigosListaService service;
				  try {
				  // Acceso a la implementacion de la capa de negocio 
					// a trav��s de la factor��a
					service = Factories.services.createAmigosListaService();
			      //Aliminamos el amigos seleccionado en la tabla
					service.deleteAmigos(amigos);
				  //Actualizamos el javabean de amigosLista inyectado en la tabla.
					amigosLista = (Amigos [])service.getAmigosLista().toArray(new Amigos[0]);
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
				  
		 	  }
	       public String edit() {
		       AmigosListaService service;
				  try {
				  // Acceso a la implementacion de la capa de negocio 
					// a trav��s de la factor��a
					service = Factories.services.createAmigosListaService();
			      //Recargamos el amigos seleccionado en la tabla de la base de datos por si hubiera cambios.
					amigos = (BeanAmigos) service.find(amigos);
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
		 	  }
	       
	       public String salva() {
		       AmigosListaService service;
				  try {
		
					service = Factories.services.createAmigosListaService();
			      
					if (amigos.getEmail_usuario() == null) {
						service.saveAmigos(amigos);
					}
					else 
					{
						service.updateAmigos(amigos); 
					} 
					
					amigosLista = (Amigos [])service.getAmigosLista().toArray(new Amigos[0]);
					return "exito";
					
				  } catch (Exception e) {
					  e.printStackTrace();
					return "error";
				  }
				  
		 	  }
	     //Se inicia correctamente el MBean inyectado si JSF lo hubiera crea
	     //y en caso contrario se crea. (hay que tener en cuenta que es un Bean de sesión)
	     //Se usa @PostConstruct, ya que en el contructor no se sabe todavía si el Managed Bean
	     //ya estaba construido y en @PostConstruct SI.
	     @PostConstruct
	     public void init() {    	  
	       System.out.println("BeanAmigosLista - PostConstruct"); 
	       //Buscamos el amigos en la sesión. Esto es un patrón factoría claramente.
	       amigos = (BeanAmigos) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("amigos"));
	       //si no existe lo creamos e inicializamos
	       if (amigos == null) { 
	         System.out.println("BeanAmigosLista - No existia");
	         amigos = new BeanAmigos();
	         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "amigos", amigos);
	       }
	       sesion = (BeanSesion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("sesion"));
	       if (sesion == null) { 
		         System.out.println("BeanSesion - No existia");
		         sesion = new BeanSesion();
		         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "sesion", sesion);
		       }
	       System.out.println("Sesion inyectada:" + sesion.getEmail());
	       usuarios = (BeanUsuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("usuarios"));
	       if (usuarios == null) { 
		         System.out.println("BeanUsuarios - No existia");
		         usuarios = new BeanUsuarios();
		         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "usuarios", usuarios);
		       }
	       System.out.println("Usuarios inyectada:" + usuarios.getUsuarios().toString());
	     }
	     @PreDestroy
	     public void end()  {
	         System.out.println("BeanAmigosLista - PreDestroy");
	     }

	}


	
