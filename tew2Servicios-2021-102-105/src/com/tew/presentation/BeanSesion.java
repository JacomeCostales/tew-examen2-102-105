package com.tew.presentation;
import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.event.ActionEvent;


@ManagedBean(name="sesion")
@SessionScoped
public class BeanSesion implements Serializable {
	private static final long serialVersionUID = 54556L;
	
	private String email;
	private String rol;
	
	public BeanSesion() {
		iniciaSesion(null);
	}

	public void setSesion(String email, String rol) {
		this.email = email;
		this.rol = rol;
		System.out.println("BeanSesion - set email: "+email+" y rol: "+rol);
	}

    public void iniciaSesion(ActionEvent event) 
    {
    	this.email = "EMAIL POR DEFECTO";
    	this.rol = "ROL POR DEFECTO";
	  }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}	  
    
    
    
}
