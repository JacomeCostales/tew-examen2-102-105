package com.tew.business;

public interface ServicesFactory 
{
	UsuariosService createUsuariosService();
	PublicacionesService createPublicacionesService();
	AmigosListaService createAmigosListaService();
	LoginService createLoginService();
	AdministradorService createAdministradorService();

}
