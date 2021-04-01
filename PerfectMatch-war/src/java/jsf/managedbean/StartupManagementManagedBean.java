/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.PaymentSessionBeanLocal;
import ejb.session.stateless.PostingSessionBeanLocal;
import ejb.session.stateless.StartUpSessionBeanLocal;
import entity.StartUp;
import enumeration.Industry;
import enumeration.StartUpLocation;
import java.io.File;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import util.exception.CreateNewStartUpException;
import util.exception.InputDataValidationException;

/**
 *
 * @author Phire
 */
@Named(value = "startupManagementManagedBean")
@ViewScoped
public class StartupManagementManagedBean implements Serializable {

    @EJB
    private PaymentSessionBeanLocal paymentSessionBean;
    @EJB
    private PostingSessionBeanLocal postingSessionBean;
    @EJB
    private StartUpSessionBeanLocal startUpSessionBean;

    private StartUp newStartUp;
    private StartUpLocation[] startUpLocations;
    private Industry[] industries;

    private StreamedContent startUpProfilePicture;

    /**
     * Creates a new instance of StartupManagementManagedBean
     */
    public StartupManagementManagedBean() {
        newStartUp = new StartUp();
        startUpLocations = StartUpLocation.values();
        industries = Industry.values();
    }

    public void DynamicImageController() {
        StartUp currentStartup = (StartUp) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("currentStartup");

        String profilePicturePath = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getInitParameter("alternatedocroot_1")
                + System.getProperty("file.separator")
                + currentStartup.getStartupId()
                + "_ProfilePicture";
        boolean hasProfilePictureJpg = ((new File(profilePicturePath + ".jpg")).exists());

        boolean hasProfilePictureJpeg = ((new File(profilePicturePath + ".jpeg")).exists());

        boolean hasProfilePicturePng = ((new File(profilePicturePath + ".png")).exists());

        if (hasProfilePictureJpg) {
            System.out.println("********** StartupManagementManagedBean.hasProfilePictureJpg");

            startUpProfilePicture = DefaultStreamedContent.builder()
                    .contentType("image/jpg")
                    .stream(() -> this.getClass().getResourceAsStream(profilePicturePath + ".jpg"))
                    .build();
        } else if (hasProfilePictureJpeg) {
            System.out.println("********** StartupManagementManagedBean.hasProfilePictureJpeg");

            startUpProfilePicture = DefaultStreamedContent.builder()
                    .contentType("image/jpeg")
                    .stream(() -> this.getClass().getResourceAsStream(profilePicturePath + ".jpeg"))
                    .build();
        } else if (hasProfilePicturePng) {
            System.out.println("********** StartupManagementManagedBean.hasProfilePicturePng");

            startUpProfilePicture = DefaultStreamedContent.builder()
                    .contentType("image/png")
                    .stream(() -> this.getClass().getResourceAsStream("/" + profilePicturePath + ".png"))
                    .build();
        }
    }

    public void createNewStartUp(ActionEvent event) {
        try {
            StartUp startup = startUpSessionBean.createNewStartUp(newStartUp);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "New StartUp registered (StartUp ID: " + startup.getStartupId() + ")",
                            null));
        } catch (InputDataValidationException | CreateNewStartUpException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while registering the new StartUp: " + ex.getMessage(), null));
        }
    }

    public StreamedContent getStartUpProfilePicture() {
        return startUpProfilePicture;
    }

    public void setStartUpProfilePicture(StreamedContent startUpProfilePicture) {
        this.startUpProfilePicture = startUpProfilePicture;
    }

    
    
    public StartUp getNewStartUp() {
        return newStartUp;
    }

    public void setNewStartUp(StartUp newStartUp) {
        this.newStartUp = newStartUp;
    }

    public StartUpLocation[] getStartUpLocations() {
        return startUpLocations;
    }

    public void setStartUpLocations(StartUpLocation[] startUpLocations) {
        this.startUpLocations = startUpLocations;
    }

    public Industry[] getIndustries() {
        return industries;
    }

    public void setIndustries(Industry[] industries) {
        this.industries = industries;
    }

}