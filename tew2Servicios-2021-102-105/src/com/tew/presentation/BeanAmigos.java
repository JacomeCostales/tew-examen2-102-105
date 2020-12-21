package com.tew.presentation;
import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.event.ActionEvent;

import com.tew.model.Amigos;

@ManagedBean(name="amigos")
@SessionScoped
public class BeanAmigos extends Amigos implements Serializable {
	private static final long serialVersionUID = 55546L;
	
	public BeanAmigos() {
		iniciaAmigos(null);
	}

	public void setAmigos(Amigos amigos) {
		setEmail_usuario(amigos.getEmail_usuario());
		setEmail_amigo(amigos.getEmail_amigo());
		setAceptada(amigos.isAceptada());
	}

    public void iniciaAmigos(ActionEvent event) 
    {
    	setEmail_usuario("POR DEFECTO");
    	setEmail_amigo("POR DEFECTO");
    	setAceptada(false);
	  }	      
}
