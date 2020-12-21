package com.tew.persistence;

import java.util.List;
import com.tew.model.Amigos;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface AmigosDao {

	List<Amigos> getAmigos();
	void save(Amigos a) throws AlreadyPersistedException;
	void update(Amigos a) throws NotPersistedException;
	void delete(Amigos a) throws NotPersistedException;
	Amigos find(Amigos a);
	List<Amigos> getAmigosPeticiones(String email);

}