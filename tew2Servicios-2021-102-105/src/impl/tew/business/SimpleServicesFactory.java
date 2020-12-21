package impl.tew.business;


import com.tew.business.*;


public class SimpleServicesFactory implements ServicesFactory {


	@Override
	public UsuariosService createUsuariosService() {
		
		return new SimpleUsuariosService();
	}
	
	@Override
	public PublicacionesService createPublicacionesService() {
		
		return new SimplePublicacionesService();
	}
	
	@Override
	public AmigosListaService createAmigosListaService() {
		
		return new SimpleAmigosListaService();
	}

	@Override
	public LoginService createLoginService() {
		return new SimpleLoginService();
	}

	@Override
	public AdministradorService createAdministradorService() {
		
		return new SimpleAdministradorService();
	}

}
