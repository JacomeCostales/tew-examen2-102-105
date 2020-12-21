package impl.tew.business;

import impl.tew.business.classes.*;


import java.util.List;

import com.tew.business.AmigosListaService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Amigos;


public class SimpleAmigosListaService implements AmigosListaService {

	@Override
	public List<Amigos> getAmigosLista() throws Exception{
		return new AmigosListaListado().getAmigosLista();
	}

	@Override
	public void saveAmigos(Amigos amigos) throws EntityAlreadyExistsException {
		new AmigosListaAlta().save(amigos);
	}

	@Override
	public void updateAmigos(Amigos amigos) throws EntityNotFoundException {
		new AmigosListaUpdate().update(amigos);
	}

	@Override
	public void deleteAmigos(Amigos amigos) throws EntityNotFoundException {
		new AmigosListaBaja().delete(amigos);
	}

	@Override
	public Amigos find(Amigos amigos) throws EntityNotFoundException {
		return new AmigosListaBuscar().find(amigos);
	}

	@Override
	public List<Amigos> getListadoPeticiones(String email) {
		return new AmigosListaListado().getAmigosListaPeticiones(email);
	}
}
