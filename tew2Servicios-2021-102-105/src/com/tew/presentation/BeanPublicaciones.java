package com.tew.presentation;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.*;
import com.tew.infrastructure.Factories;
import com.tew.model.Publicacion;

@ManagedBean
@SessionScoped
public class BeanPublicaciones implements Serializable{
	      private static final long serialVersionUID = 555572L;
		  
	     
          private Publicacion[] publicaciones = null;
          private Random rand;
          
		  
		  @ManagedProperty(value="#{login}") 
          private BeanLogin login;
		  public BeanLogin getLogin() {return login;}
		  public void setLogin(BeanLogin login) {this.login = login;}

		@ManagedProperty(value="#{publicacion}") 
          private BeanPublicacion publicacion;
          
          public BeanPublicacion getPublicacion() { return publicacion; }
          public void setPublicacion(BeanPublicacion publicacion) {this.publicacion = publicacion;}
          
		  public Publicacion[] getPublicaciones () {return(publicaciones);}
	      public void setPublicaciones(Publicacion[] publicaciones) {this.publicaciones = publicaciones;} 
	       
	       public void iniciaPublicacion(ActionEvent event) 
	       {
	    	   publicacion.setId(-1);
	    	   publicacion.setEmail("email");
	    	   publicacion.setTitulo("");
	    	   publicacion.setTexto("");
	    	   publicacion.setFecha(10000000l);
	    	   System.out.println("BeanPublicaciones - IniciaPublicaci�n");
	       }
	       
	       
	       public String listadoPropias(String orderCol) {
		       PublicacionesService service;
		       
				  try {
					service = Factories.services.createPublicacionesService();
					publicaciones = (Publicacion [])service.getPublicacionesPropias(login.getName(),orderCol).toArray(new Publicacion[0]);
					
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
				  
		 	  }
	       
	       public String listadoAmigos() {
		       PublicacionesService service;
		       
				  try {
					service = Factories.services.createPublicacionesService();
					publicaciones = (Publicacion [])service.getPublicacionesAmigos(login.getName()).toArray(new Publicacion[0]);
					
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
				  
		 	  }
	       
	       
	       public String baja(Publicacion publicacion) {
	    	   PublicacionesService service;
				  try {
				  // Acceso a la implementacion de la capa de negocio 
					// a trav��s de la factor��a
					service = Factories.services.createPublicacionesService();
			      //Aliminamos el alumno seleccionado en la tabla
					service.deletePublicacion(publicacion.getId());
				  //Actualizamos el javabean de alumnos inyectado en la tabla.
					publicaciones = (Publicacion [])service.getPublicacionesPropias(login.getName(),"ID").toArray(new Publicacion[0]);
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
				  
		 	  }
	       public String edit() {
	    	   PublicacionesService service;
				  try {
				  // Acceso a la implementacion de la capa de negocio 
					// a trav��s de la factor��a
					  service = Factories.services.createPublicacionesService();
			      //Recargamos el alumno seleccionado en la tabla de la base de datos por si hubiera cambios.
					publicacion = (BeanPublicacion) service.findById(publicacion.getId());
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
				  
		 	  }
	       
	       public String salva() {
	    	   PublicacionesService service;
				  try {
				  
					  service = Factories.services.createPublicacionesService();
					  
			        int id = rand.nextInt();
			        id = (id^2)%1000000;
			        if(id<0)id+=1000000;
			        
				        System.out.println(id);
					if (publicacion.getId() == -1) {
						publicacion.setId(id);
						publicacion.setEmail(login.getName());
						ZoneId zoneId = ZoneId.systemDefault(); 
						long epoch = LocalDateTime.now().atZone(zoneId).toEpochSecond();
						publicacion.setFecha(epoch*1000);
						
						service.savePublicacion(publicacion);
					}
					else {
						service.updatePublicacion(publicacion); 
					} 
					//Actualizamos el javabean de alumnos inyectado en la tabla
					publicaciones = (Publicacion [])service.getPublicacionesPropias(login.getName(),"ID").toArray(new Publicacion[0]);
					return "exito";
					
				  } catch (Exception e) {
					  e.printStackTrace();
					return "error";
				  }
				  
		 	  }
	     
	     @PostConstruct
	     public void init() {    	  
	       System.out.println("BeanPublicaciones - PostConstruct"); 
	       //Buscamos el alumno en la sesión. Esto es un patrón factoría claramente.
	       publicacion = (BeanPublicacion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("publicacion"));
	       //si no existe lo creamos e inicializamos
	       if (publicacion == null) { 
	         System.out.println("BeanPublicacion - No existia");
	         publicacion = new BeanPublicacion();
	         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "publicacion", publicacion);
	       }
	       
	       login = (BeanLogin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("login"));
	       if (login == null) { 
		         System.out.println("BeanLogin - No existia");
		         login = new BeanLogin();
		         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", login);
		       }
	       System.out.println("Login inyectado:" + login.getName());
	       
	       rand = new Random();
	     }
	     @PreDestroy
	     public void end()  {
	         System.out.println("BeanPublicaciones - PreDestroy");
	     }

	}


	
