package com.tew.presentation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.tew.business.AdministradorService;
import com.tew.infrastructure.Factories;
import com.tew.model.Usuario;


@ManagedBean
@SessionScoped
public class BeanAdministrador  implements Serializable {
	private static final long serialVersionUID = 55556L;
	
	private  List<Usuario> users= null;
	private List<String> nombresBorrar = null;
	
	@ManagedProperty(value="#{usuarios}") 
    private BeanUsuarios usuarios;
    public BeanUsuarios getUsuarios() { return usuarios; }
    public void setUsuarios(BeanUsuarios usuarios) {this.usuarios = usuarios;}
    

	@ManagedProperty(value="#{loginManager}")
    private BeanLoginManager loginManager;
	
	
	public String borrarUsuarios() {
		AdministradorService service;
		FacesContext context = FacesContext.getCurrentInstance();
		
		  try {
		 
			service = Factories.services.createAdministradorService();

			for(Usuario u : users) {
				System.out.print(u);
				if(!loginManager.inLogins(u.getEmail())){nombresBorrar.add(u.getEmail());}
			}
			
			if(users.size()!=nombresBorrar.size()) {context.addMessage(null, new FacesMessage("Error",  "Hay usuarios con la sesion iniciada") );}
			//DEBUG
			//System.out.println("BeanAdministrador - EMAILS: ");
			//System.out.println(nombresBorrar.toString());
			
			service.borrarUsarios(nombresBorrar);
			
			nombresBorrar.clear();
			
			usuarios.listado();
			
			
			return "exito";
			
		  } catch (Exception e) {
			e.printStackTrace();  
			return "error";
		  }
	}
	
	
	public String reiniciarBD() {
		AdministradorService service;

		try {
			service = Factories.services.createAdministradorService();

			service.reiniciarBD();
			loginManager.getLogins().clear();
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			return "exito";
		}
		catch (Exception e) {
			e.printStackTrace();  
			return "error";
		  }
	}
	
	
	@PostConstruct
    public void init() {    	  
      System.out.println("BeanAdministrador - PostConstruct"); 
      nombresBorrar = new ArrayList<String>();
      
      usuarios = (BeanUsuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("usuarios"));
      if (usuarios == null) { 
	         
	         usuarios = new BeanUsuarios();
	         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "usuarios", usuarios);
	       }
      
      loginManager = (BeanLoginManager) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(new String("loginManager"));
      
      if (loginManager == null) { 
        System.out.println("BeanLogin - LoginManager No existia");
        loginManager = new BeanLoginManager();
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put( "loginManager", loginManager);
      }
      
    }
    @PreDestroy
    public void end()  {
        System.out.println("BeanAdministrador - PreDestroy");
    }

	public List<Usuario> getUsers() {
		return users;
	}

	public void setUsers( List<Usuario> users) {
		this.users = users;
	}
    

}
