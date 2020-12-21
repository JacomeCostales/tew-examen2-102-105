package com.tew.infrastructure;

import impl.tew.business.SimpleServicesFactory;
import impl.tew.persistence.SimplePersistenceFactory;

import com.tew.persistence.PersistenceFactory;
import com.tew.business.ServicesFactory;


public class Factories {

	public static ServicesFactory services = new SimpleServicesFactory();
	public static PersistenceFactory persistence = new SimplePersistenceFactory();

}
