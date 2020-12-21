package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Amigos;

public interface AmigosListaService 
{

	List<Amigos> getAmigosLista() throws Exception;
	Amigos find(Amigos a) throws EntityNotFoundException;
	void saveAmigos(Amigos amigos) throws EntityAlreadyExistsException;
	void updateAmigos(Amigos amigos) throws EntityNotFoundException;
	void deleteAmigos(Amigos amigos) throws EntityNotFoundException;
	List<Amigos> getListadoPeticiones(String email);

}