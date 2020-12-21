package com.tew.business;


import com.tew.model.User;
import com.tew.model.Usuario;

/**
 * 
 * @author Jácome y Miguel
 *
 */
public interface LoginService 
{

	User verify(String name, String password);

	void registry(Usuario usuarioRegistrar);

	Usuario compruebaExiste(String emailRegistrado);


}