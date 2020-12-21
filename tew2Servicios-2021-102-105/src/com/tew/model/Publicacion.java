package com.tew.model;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "publicacion")
public class Publicacion {
	
	private int id;
	private String email;
	private String titulo;
	private String texto;
	private Long fecha;
	
	public Publicacion() {}
	
	public Publicacion(int id,String email, String titulo, String texto,long fecha) {
		this.id =id;this.email=email;this.titulo=titulo;this.texto=texto;this.fecha=fecha;
	}
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@XmlElement
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	@XmlElement
	public Long getFecha() {
		return fecha;
	}
	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}
	@XmlElement
	public Date getFecha_format() {
		
		return new Date(fecha);
	}
	public void setFecha_format(Date fecha_format) {
		fecha = fecha_format.getTime();
	}

	@XmlElement
	public String getFecha_string() {
		
		return new Date(fecha).toString();
	}
	
	

	
}
