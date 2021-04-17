/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import entity.Application;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Phire
 */
@Named(value = "viewApplicationManagedBean")
@ViewScoped
public class ViewApplicationManagedBean implements Serializable {

    private Application applicationEntityToView;
    
    /**
     * Creates a new instance of ViewApplicationManagedBean
     */
    public ViewApplicationManagedBean() {
        applicationEntityToView = new Application();
    }

    public Application getApplicationEntityToView() {
        return applicationEntityToView;
    }

    public void setApplicationEntityToView(Application applicationEntityToView) {
        this.applicationEntityToView = applicationEntityToView;
    }
    
    
    
}
