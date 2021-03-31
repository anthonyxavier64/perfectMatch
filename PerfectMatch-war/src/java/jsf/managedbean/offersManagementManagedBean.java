/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.OfferSessionBeanLocal;
import ejb.session.stateless.StudentSessionBeanLocal;
import entity.Offer;
import entity.Student;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import util.exception.CreateNewOfferException;
import util.exception.InputDataValidationException;
import util.exception.OfferNotFoundException;
import util.exception.StudentNotFoundException;

/**
 *
 * @author user
 */
@Named(value = "offersManagementManagedBean")
@ViewScoped
public class offersManagementManagedBean implements Serializable {

    @EJB
    private StudentSessionBeanLocal studentSessionBean;

    @EJB
    private OfferSessionBeanLocal offerSessionBean;
    
    
    @Inject
    private viewOfferManagedBean viewOfferManagedBean;
   
    private List<Offer> offers;
    private List<Offer> filteredOffers;
   
    private Offer newOffer;
    private Long postingId;
    private Long studentId;
    private List<Student> listOfStudents;
    
    private Offer selectedOfferToUpdate;
    private Long studentIdUpdate;
    
    /**
     * Creates a new instance of offersManagementManagedBean
     */
    public offersManagementManagedBean() {
        newOffer = new Offer();
    }
    
    @PostConstruct
    public void postConstruct()
    {
        offers = offerSessionBean.retrieveAllOffers();
    }
    
    public void viewOfferDetails(ActionEvent event) throws IOException
    {
        Long offerIdToView = (Long)event.getComponent().getAttributes().get("offerId");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("offerIdToView", offerIdToView);
        FacesContext.getCurrentInstance().getExternalContext().redirect("offersManagement.xhtml");
    }
    
    public void createNewProduct(ActionEvent event) throws CreateNewOfferException
    {                     
        
        try
        {
            Offer offer = offerSessionBean.createNewOffer(newOffer, studentId, postingId);
            offers.add(offer);
            
            if(filteredOffers != null)
            {
                filteredOffers.add(offer);
            }
            
            newOffer = new Offer();
            studentId = null;
            postingId = null;
            

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New offer created successfully (Offer ID: " + offer.getOfferId() + ")", null));
        }
        catch(InputDataValidationException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new offer: " + ex.getMessage(), null));
        }
    }
    
    public void doUpdateOffer(ActionEvent event) throws StudentNotFoundException 
    {
        selectedOfferToUpdate = (Offer)event.getComponent().getAttributes().get("offerToUpdate");
        
        Student toUpdate = studentSessionBean.retrieveStudentByStudentId(studentIdUpdate);
        
        selectedOfferToUpdate.setStudent(toUpdate);
    }
    
    public void updateOffer(ActionEvent event)
    {
         try
        {
            Student toUpdate = studentSessionBean.retrieveStudentByStudentId(studentIdUpdate);
            selectedOfferToUpdate.setStudent(toUpdate);

            offerSessionBean.updateOffer(selectedOfferToUpdate);
                        

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Offer updated successfully", null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
    
    public void deleteOffer(ActionEvent event) 
    {
        try
        {
            Offer offerToDelete = (Offer)event.getComponent().getAttributes().get("offerToDelete");
            offerSessionBean.deleteOffer(offerToDelete.getOfferId());
            
            offers.remove(offerToDelete);
            
            if(filteredOffers != null)
            {
                filteredOffers.remove(offerToDelete);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Offer deleted successfully", null));
        }
        catch(OfferNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while deleting offer: " + ex.getMessage(), null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
}
