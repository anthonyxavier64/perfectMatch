/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import entity.StartUp;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Phire
 */
@Named(value = "viewStartUpProfileManagedBean")
@ViewScoped
public class ViewStartUpProfileManagedBean implements Serializable {

   private StartUp currentStartUp;
    
    /**
     * Creates a new instance of ViewStartUpProfileManagedBean
     */
    public ViewStartUpProfileManagedBean() {
        currentStartUp = (StartUp) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("currentStartUp");
    }

    public StartUp getCurrentStartUp() {
        return currentStartUp;
    }

    public void setCurrentStartUp(StartUp currentStartUp) {
        this.currentStartUp = currentStartUp;
    }
    
    
}
