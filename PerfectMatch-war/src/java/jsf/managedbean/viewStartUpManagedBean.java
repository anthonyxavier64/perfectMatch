/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.StartUpSessionBeanLocal;
import entity.StartUp;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import util.exception.StartUpNotFoundException;

/**
 *
 * @author user
 */
@Named(value = "viewStartUpManagedBean")
@ViewScoped
public class viewStartUpManagedBean implements Serializable {

    @EJB
    private StartUpSessionBeanLocal startUpSessionBean;

    
    
    private StartUp startUpToView;

    /**
     * Creates a new instance of viewStartUpManagedBean
     */
    public viewStartUpManagedBean() {
        startUpToView = new StartUp();
    }

    @PostConstruct
    public void postConstruct() {

    }

    public StartUp getStartUpToView() {
        return startUpToView;
    }

    public void setStartUpToView(String email) throws StartUpNotFoundException {
        StartUp loggedIn = startUpSessionBean.retrieveStartUpByEmail(email);
        
        this.startUpToView = loggedIn;
    }

    
}
