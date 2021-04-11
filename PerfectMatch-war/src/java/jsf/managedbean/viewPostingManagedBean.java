/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import entity.Posting;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author user
 */
@Named(value = "viewPostingManagedBean")
@ViewScoped
public class viewPostingManagedBean implements Serializable{

         private Posting postingToView;
    
    /**
     * Creates a new instance of ViewPostingManagedBean
     */
    public viewPostingManagedBean() {
        postingToView = new Posting() {};
    }
    
    
    @PostConstruct
    public void postConstruct() 
    {
        
    }

    public Posting getPostingToView() {
        return postingToView;
    }

    public void setPostingToView(Posting postingToView) {
        this.postingToView = postingToView;
    }
    
    
    
    
}
