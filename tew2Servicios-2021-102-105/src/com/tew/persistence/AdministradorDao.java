package com.tew.persistence;

import java.util.List;
import com.tew.persistence.exception.NotPersistedException;


public interface AdministradorDao {

	void borrarUsarios(List<String> a) throws NotPersistedException;
	void reiniciarBD();

}