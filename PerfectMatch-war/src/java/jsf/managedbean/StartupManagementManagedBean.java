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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
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

    // Used to update StartUp
    private StartUp currentStartUp;
    private Industry updateIndustry;
    private StartUpLocation updateLocation;

    /**
     * Creates a new instance of StartupManagementManagedBean
     */
    public StartupManagementManagedBean() {
        newStartUp = new StartUp();
        startUpLocations = StartUpLocation.values();
        industries = Industry.values();
    }

    @PostConstruct
    public void postConstruct() {
        currentStartUp = (StartUp) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("currentStartUp");
        updateIndustry = currentStartUp.getIndustry();
        updateLocation = currentStartUp.getStartupLocation();
    }

    public void setProfilePic() {
        StartUp currentStartup = (StartUp) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("currentStartUp");

        String profilePicturePath = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRealPath("")
                + System.getProperty("file.separator")
                + "resources"
                + System.getProperty("file.separator")
                + "images"
                + System.getProperty("file.separator")
                + currentStartup.getStartupId()
                + "_ProfilePicture";
        boolean hasProfilePictureJpg = ((new File(profilePicturePath + ".jpg")).exists());

        boolean hasProfilePictureJpeg = ((new File(profilePicturePath + ".jpeg")).exists());

        boolean hasProfilePicturePng = ((new File(profilePicturePath + ".png")).exists());

        if (hasProfilePictureJpg) {
            System.out.println("********** StartupManagementManagedBean.hasProfilePictureJpg");

            String startUpProfilePicture = currentStartup.getStartupId() + "_ProfilePicture.jpg";
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                    .put("startUpProfilePicture", startUpProfilePicture);
        } else if (hasProfilePictureJpeg) {
            System.out.println("********** StartupManagementManagedBean.hasProfilePictureJpeg");

            String startUpProfilePicture = currentStartup.getStartupId() + "_ProfilePicture.jpeg";
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                    .put("startUpProfilePicture", startUpProfilePicture);
        } else if (hasProfilePicturePng) {
            System.out.println("********** StartupManagementManagedBean.hasProfilePicturePng");

            String startUpProfilePicture = currentStartup.getStartupId() + "_ProfilePicture.png";
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                    .put("startUpProfilePicture", startUpProfilePicture);
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                    .put("startUpProfilePicture", "person.png");
        }
    }

    public void createNewStartUp(ActionEvent event) {
        try {
            StartUp startup = startUpSessionBean.createNewStartUp(newStartUp);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "New StartUp registered (StartUp ID: " + startup.getStartupId() + ")",
                            null));
            newStartUp = new StartUp();
        } catch (InputDataValidationException | CreateNewStartUpException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while registering the new StartUp: " + ex.getMessage(), null));
        }
    }

    public void updateStartUp(ActionEvent event) {
        currentStartUp.setIndustry(updateIndustry);
        currentStartUp.setStartupLocation(updateLocation);
        startUpSessionBean.updateStartUp(currentStartUp);

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .put("currentStartUp", currentStartUp);
    }

    public StartUp getCurrentStartUp() {
        return currentStartUp;
    }

    public void setCurrentStartUp(StartUp currentStartUp) {
        this.currentStartUp = currentStartUp;
    }

    public Industry getUpdateIndustry() {
        return updateIndustry;
    }

    public void setUpdateIndustry(Industry updateIndustry) {
        this.updateIndustry = updateIndustry;
    }

    public StartUpLocation getUpdateLocation() {
        return updateLocation;
    }

    public void setUpdateLocation(StartUpLocation updateLocation) {
        this.updateLocation = updateLocation;
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
