package com.tew.presentation;
import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.event.ActionEvent;
import com.tew.model.Usuario;

@ManagedBean(name="usuario")
@SessionScoped
public class BeanUsuario extends Usuario implements Serializable {
	private static final long serialVersionUID = 555590L;
	
	public BeanUsuario() {
		iniciaUsuario(null);
	}

	public void setAlumno(Usuario usuario) {
		setNombre(usuario.getNombre());
		setPasswd(usuario.getPasswd());
		setRol(usuario.getRol());
		setEmail(usuario.getEmail());
	}

	public void iniciaUsuario(ActionEvent event) 
	{
		setNombre("POR_DEFECTO");
		setPasswd("POR_DEFECTO");
		setRol("POR_DEFECTO");
		setEmail("POR_DEFECTO");
	}	      
}
