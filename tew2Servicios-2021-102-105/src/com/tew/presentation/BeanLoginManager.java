package com.tew.presentation;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.*;

import com.tew.model.User;

@ManagedBean(name = "loginManager", eager=true)
@ApplicationScoped
public class BeanLoginManager implements Serializable {

	private static final long serialVersionUID = 3192773889359774741L;
	
	private Set<User> logins = new HashSet<User>();

	public Set<User> getLogins() {
		
		return logins;
	}

	public void setLogins(Set<User> logins) {
		this.logins = logins;
	}
	
	public boolean inLogins(String email)
	{
		//System.out.print("inLogins comprobando email: "+email);
		boolean r= false;
		for(User u : logins) {
			if(u.getLogin().equals(email)) {r= true;}
		}
		return r;
	}
	
	

}