package com.tew.business.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.tew.infrastructure.Factories;
import com.tew.persistence.AdministradorDao;

@WebListener
public class initDB implements ServletContextListener {

	public initDB() {
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		
		
		AdministradorDao dao = Factories.persistence.createAdministradorDao();
		dao.reiniciarBD();
		
		
	}

}
