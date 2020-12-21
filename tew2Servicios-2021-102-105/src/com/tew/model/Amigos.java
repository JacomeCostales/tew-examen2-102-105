package com.tew.model;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlRootElement(name = "amigos") 
public class Amigos 
{
	private String email_usuario;
	private String email_amigo;
	private boolean aceptada;
	
	public Amigos() 
	{
		
	}
	
	public Amigos(String emailusu, String emailamig, boolean acept) 
	{
		 this.email_usuario = emailusu;
		 this.email_amigo = emailamig;
		 this.aceptada = acept;
	}
	
	
	@XmlElement
	public String getEmail_usuario() 
	{
		return email_usuario;
	}
	
	
	public void setEmail_usuario(String email_usuario) 
	{
		this.email_usuario = email_usuario;
	}
	
	@XmlElement 
	public String getEmail_amigo() 
	{
		return email_amigo;
	}
	
 
	public void setEmail_amigo(String email_amigo) 
	{
		this.email_amigo = email_amigo;
	}
	
	@XmlElement
	public boolean isAceptada() 
	{
		return aceptada;
	}
	

	public void setAceptada(boolean aceptada) 
	{
		this.aceptada = aceptada;
	}
	
	@Override
	public String toString() 
	{
		return ("Usuario:"+email_usuario+"Amigo: "+email_amigo);
	}

}
