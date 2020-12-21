package com.tew.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.tew.model.User;
import com.tew.presentation.BeanLoginManager;

@WebListener
public class LogoutListener implements HttpSessionListener {

       @Override
       public void sessionCreated(HttpSessionEvent event) {
           // NOOP.
       }

       @Override
       public void sessionDestroyed(HttpSessionEvent event) {
        /*   BeanLogin Login = (BeanLogin) event.getSession().getAttribute("login");
           if (Login != null && Login.getCurrent() != null) {
        	   System.out.println("ESTAMOS EN DESTRUIR SESION: ");
        	   System.out.println(Login.getLoginManager() == null);
               Login.getLoginManager().getLogins().remove(Login.getCurrent());
           }
           */
           User user = (User) event.getSession().getAttribute("LOGGEDIN_USER");
           if (user != null) {
               BeanLoginManager loginManager = (BeanLoginManager) event.getSession().getServletContext().getAttribute("loginManager");
               loginManager.getLogins().remove(user);
           }
       }

}