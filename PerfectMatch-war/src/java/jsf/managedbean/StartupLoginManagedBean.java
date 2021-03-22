/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.StartupSessionBeanLocal;
import entity.Startup;
import exceptions.StartupInvalidLoginException;
import javafx.event.ActionEvent;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author Antho
 */
@Named(value = "startupLoginManagedBean")
@RequestScoped
public class StartupLoginManagedBean {

    @EJB
    private StartupSessionBeanLocal startupSessionBeanLocal;
    
    private String email;
    private String password;
    
    /**
     * Creates a new instance of StartupLoginManagedBean
     */
    public StartupLoginManagedBean() {
    }
    
    public void login(ActionEvent event) throws StartupInvalidLoginException { 
        try { 
            Startup startup = startupSessionBeanLocal.loginStartup(email, password);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLogin", true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentStartup", startup);
        } catch (NoResultException | NonUniqueResultException ex) { 
            throw new StartupInvalidLoginException("Invalid login credentials");
        }
    }
    
}
