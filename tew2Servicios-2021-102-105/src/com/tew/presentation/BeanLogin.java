package com.tew.presentation;
import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.tew.business.LoginService;
import com.tew.infrastructure.Factories;
import com.tew.model.User;
import com.tew.model.Usuario;

@ManagedBean(name="login")
@SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 828037108714014407L;
	private String name = "";
	private String password = "";
	private User current = null;
	private String emailRegistrado = "";
	private String contraseñaRegistrado = "";
	private String nombreRegistrado = "";
	private boolean LOPDaceptada = false;

	@ManagedProperty(value="#{loginManager}")
    private BeanLoginManager loginManager;
	
	public BeanLoginManager getLoginManager() {
		return loginManager;
	}
	public void setLoginManager(BeanLoginManager loginManager) {
		this.loginManager = loginManager;
	}
	
	

	public String getEmailRegistrado() {
		return emailRegistrado;
	}
	public void setEmailRegistrado(String emailRegistrado) {
		this.emailRegistrado = emailRegistrado;
	}
	public String getContraseñaRegistrado() {
		return contraseñaRegistrado;
	}
	public void setContraseñaRegistrado(String contraseñaRegistrado) {
		this.contraseñaRegistrado = contraseñaRegistrado;
	}
	public String getNombreRegistrado() {
		return nombreRegistrado;
	}
	public void setNombreRegistrado(String nombreRegistrado) {
		this.nombreRegistrado = nombreRegistrado;
	}
	public User getCurrent() {
		return current;
	}
	public void setCurrent(User current) {
		this.current = current;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public boolean isLOPDaceptada() {
		return LOPDaceptada;
	}
	public void setLOPDaceptada(boolean lOPDaceptada) {
		
		LOPDaceptada = lOPDaceptada;
	}
	private void putUserInSession(User user) 
	{
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
	}
	
	public String verify() {
		
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		FacesMessage msg = null;
		LoginService login = Factories.services.createLoginService();
		User usuario = login.verify(name, password);
		
		if (usuario != null) 
		{
			current = usuario;
			loginManager.getLogins().add(usuario);
			putUserInSession(usuario);
			if(usuario.getRol().equals("admin"))
			{
				return "successAdmin";
			}
			return "successUsuarios";
		}
		jsfCtx.addMessage(null, new FacesMessage("Usuario y Contraseña inexistentes"));
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN,bundle.getString("login_form_result_error"), null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "login";
	}	
	
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        return "CIERRASESION";
    }
		
	
	public String registry()
	{
		LoginService login = Factories.services.createLoginService();
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuarioExistente = login.compruebaExiste(emailRegistrado);
		Usuario usuarioRegistrar = new Usuario(emailRegistrado,contraseñaRegistrado,nombreRegistrado,"usuario");
		if(usuarioExistente != null)
		{
			context.addMessage(null, new FacesMessage("Ya existe un usuario con ese email, debes elegir otro"));
			return "existente";
		}
		else if(!LOPDaceptada) {
			context.addMessage(null, new FacesMessage("Es necesario aceptar la LOPD"));
			return "existente";
		}
		else
		{
		login.registry(usuarioRegistrar);
		this.name = this.emailRegistrado;
		this.password = this.contraseñaRegistrado;
		String resultados = this.verify();
		return resultados;
		}
	}
	
	 @PostConstruct
     public void init() {    	  
       System.out.println("BeanLogin - PostConstruct"); 
      
       loginManager = (BeanLoginManager) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(new String("loginManager"));
       
       if (loginManager == null) { 
         System.out.println("BeanLogin - LoginManager No existia");
         loginManager = new BeanLoginManager();
         FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put( "loginManager", loginManager);
       }
     }
     @PreDestroy
     public void end()  {
         System.out.println("BeanLogin - PreDestroy");
     }
			
}