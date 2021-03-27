/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import entity.Offer;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

/**
 *
 * @author user
 */
@Named(value = "viewOfferManagedBean")
@ViewScoped
public class viewOfferManagedBean implements Serializable {
    
    private Offer offerEntityToView;
    
    /**
     * Creates a new instance of viewOfferManagedBean
     */
    public viewOfferManagedBean() {
        offerEntityToView = new Offer();
    }
    
    @PostConstruct
    public void postConstruct() 
    {
        
    }

    public Offer getOfferEntityToView() {
        return offerEntityToView;
    }

    public void setOfferEntityToView(Offer offerEntityToView) {
        this.offerEntityToView = offerEntityToView;
    }
    
    
    
}
