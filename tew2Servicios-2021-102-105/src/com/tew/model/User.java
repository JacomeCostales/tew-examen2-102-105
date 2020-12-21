package com.tew.model;

import java.io.Serializable;

public class User implements Serializable
{
	private static final long serialVersionUID = 852556274670135358L;
	private String login;
	private String rol;
	
	public User(String login2, String string) 
	{
		login = login2;
		rol = string;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
	
}
