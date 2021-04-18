/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import entity.StartUp;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Phire
 */
@Named(value = "viewStartUpProfileManagedBean")
@RequestScoped
public class ViewStartUpProfileManagedBean {

   private StartUp currentStartUp;
   private double startUpRating;
   private String email;
    
    /**
     * Creates a new instance of ViewStartUpProfileManagedBean
     */
    public ViewStartUpProfileManagedBean() {
        currentStartUp = (StartUp) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("currentStartUp");
        startUpRating = Integer.valueOf(currentStartUp.getRating());
        email = currentStartUp.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StartUp getCurrentStartUp() {
        return currentStartUp;
    }

    public void setCurrentStartUp(StartUp currentStartUp) {
        this.currentStartUp = currentStartUp;
    }

    public double getStartUpRating() {
        return startUpRating;
    }

    public void setStartUpRating(double startUpRating) {
        this.startUpRating = startUpRating;
    }
}
