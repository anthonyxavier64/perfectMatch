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
import util.exception.IncorrectStartUpPasswordException;
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
    private String passwordToVerify;
    private String newPassword1;
    private String newPassword2;

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
//        updateIndustry = currentStartUp.getIndustry();
//        updateLocation = currentStartUp.getStartupLocation();
    }

    public void setProfilePic() {
        StartUp currentStartup = (StartUp) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("currentStartUp");

        String profilePicturePath = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getInitParameter("alternatedocroot_1")
                + System.getProperty("file.separator")
                + currentStartup.getStartupId()
                + "_ProfilePicture";

        System.out.println("********** StartupManagementManagedBean.setProfilePic Profile Pic Path: "
                + profilePicturePath);

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

    public void verifyPassword(ActionEvent event) {
        System.out.println("********** StartupManagementManagedBean.verifyPassword");
        try {
            if (!passwordToVerify.equals(currentStartUp.getPassword())) {
                System.out.println("********** StartupManagementManagedBean.verifyPassword.incorrect password");
                throw new IncorrectStartUpPasswordException("Incorrect Pasword!");
            } else {
                System.out.println("********** StartupManagementManagedBean.verifyPassword.password verified");
                if (newPassword1.equals(newPassword2)) {
                    changePassword();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "New passwords do not match!", null));
                }
            }
        } catch (IncorrectStartUpPasswordException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
        }
    }

    private void changePassword() {
        currentStartUp.setPassword(newPassword1);
        startUpSessionBean.updateStartUp(currentStartUp);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Password has been changed successfully!", null));
    }

    public void updateStartUp(ActionEvent event) {
        System.out.println("********** StartupManagementManagedBean.updateStartUp");

        currentStartUp.setIndustry(updateIndustry);
        currentStartUp.setStartupLocation(updateLocation);
        startUpSessionBean.updateStartUp(currentStartUp);

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .put("currentStartUp", currentStartUp);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "StartUp has been updated successfully!", null));
    }
    
    public void upgradeToPremium(ActionEvent event) {
        System.out.println("********** StartupManagementManagedBean.upgradeToPremium");
        
        currentStartUp.setIsPremium(true);
        startUpSessionBean.updateStartUp(currentStartUp);
        System.out.println(currentStartUp.getStartupId() + " now has a premium account!");
    }
    
    public void terminateSubscription(ActionEvent event) {
       System.out.println("********** StartupManagementManagedBean.terminateSubscription");        
       currentStartUp.setIsPremium(false); 
       startUpSessionBean.updateStartUp(currentStartUp);
       System.out.println(currentStartUp.getStartupId() + " now has a free account!");
    }

    public String getPasswordToVerify() {
        return passwordToVerify;
    }

    public void setPasswordToVerify(String passwordToVerify) {
        this.passwordToVerify = passwordToVerify;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
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
