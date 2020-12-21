package com.tew.persistence;

import com.tew.persistence.AlumnoDao;

public interface PersistenceFactory {
	
	AlumnoDao createAlumnoDao();
	
	UsuarioDao createUsuarioDao();
	
	PublicacionDao createPublicacionDao();

	AmigosDao createAmigosDao();
	
	AdministradorDao createAdministradorDao();
	
}

