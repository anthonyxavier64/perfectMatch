/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import entity.StartUp;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import util.exception.StartUpNotFoundException;
import ejb.session.stateless.StartUpSessionBeanLocal;
import javax.faces.context.FacesContext;

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
        startUpToView = (StartUp) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentStartup");
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
