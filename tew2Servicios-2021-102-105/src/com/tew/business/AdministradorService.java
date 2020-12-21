package com.tew.business;



import java.util.List;

import com.tew.persistence.exception.NotPersistedException;


public interface AdministradorService {

	void borrarUsarios(List<String> a) throws NotPersistedException;
	void reiniciarBD() throws Exception;

}