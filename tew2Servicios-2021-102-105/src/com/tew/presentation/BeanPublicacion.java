package com.tew.presentation;
import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.event.ActionEvent;

import com.tew.model.Publicacion;

@ManagedBean(name="publicacion")
@SessionScoped
public class BeanPublicacion extends Publicacion implements Serializable {
	private static final long serialVersionUID = 555591L;
	
	public BeanPublicacion() {
		iniciaPublicacion(null);
	}

	public void setPublicacion(Publicacion publicacion) {
		setId(publicacion.getId());
		setEmail(publicacion.getEmail());
		setTitulo(publicacion.getTitulo());
		setTexto(publicacion.getTexto());
		setFecha(publicacion.getFecha());
	}

    public void iniciaPublicacion(ActionEvent event) 
    {
    	setId(-1);
    	setEmail("email");
    	setTitulo("Título");
    	setTexto("Texto");
    	setFecha(10000000l);
	  }	      
}
