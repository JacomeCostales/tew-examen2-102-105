package com.tew.presentation;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale = new Locale("es");

	

	public Locale getLocale() 
	{
		return (locale);
	}

	public void setSpanish(ActionEvent event) 
	{
		locale = SPANISH;
	}

	public void setEnglish(ActionEvent event) 
	{
		locale = ENGLISH;
	}

	
	@PostConstruct
	public void init() 
	{
		System.out.println("BeanSettings - PostConstruct");
	}

	
	@PreDestroy
	public void end() 
	{
		System.out.println("BeanSettings - PreDestroy");
	}

}
