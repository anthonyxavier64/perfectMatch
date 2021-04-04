package jsf.managedbean;

import entity.StartUp;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpSession;
import ejb.session.stateless.StartUpSessionBeanLocal;
import java.io.File;
import javax.inject.Inject;

@Named(value = "startupLoginManagedBean")
@RequestScoped

public class StartupLoginManagedBean {

    @EJB
    private StartUpSessionBeanLocal startupSessionBean;

    @Inject
    private StartupManagementManagedBean startupManagementManagedBean;

    private String email;
    private String password;

    public StartupLoginManagedBean() {
    }

    public void login(ActionEvent event) throws IOException {
        try {
            StartUp currentStartup = startupSessionBean.loginStartUp(email, password);
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSession(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                    .put("isLogin", true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                    .put("currentStartup", currentStartup);
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.xhtml");

            startupManagementManagedBean.setProfilePic();
            // Check if CompanyProfilePic exists
//            String profilePicturePath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
//                    + System.getProperty("file.separator") + "resources"
//                    + System.getProperty("file.separator") + "images"
//                    + System.getProperty("file.separator") + "startUpProfilePictures"
//                    + System.getProperty("file.separator")
//                    + currentStartup.getStartupId()
//                    + "_ProfilePicture";
//            String profilePicturePath = FacesContext
//                    .getCurrentInstance()
//                    .getExternalContext()
//                    .getInitParameter("alternatedocroot_1")
//                    + currentStartup.getStartupId()
//                    + "_ProfilePicture";
//            
//            boolean hasProfilePictureJpg = ((new File(profilePicturePath + ".jpg")).exists());
//
//            boolean hasProfilePictureJpeg = ((new File(profilePicturePath + ".jpeg")).exists());
//
//            boolean hasProfilePicturePng = ((new File(profilePicturePath + ".png")).exists());
//
//            if (hasProfilePictureJpg) {
//                System.out.println("********** StartupLoginManagedBean.hasProfilePictureJpg");
//
//                File currentStartupProfilePicture = new File(profilePicturePath + ".jpg");
//                FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
//                        .put("currentStartupProfilePicture", currentStartupProfilePicture);
//            } else if (hasProfilePictureJpeg) {
//                System.out.println("********** StartupLoginManagedBean.hasProfilePictureJpeg");
//
//                File currentStartupProfilePicture = new File(profilePicturePath + ".jpeg");
//                FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
//                        .put("currentStartupProfilePicture", currentStartupProfilePicture);
//            } else if (hasProfilePicturePng) {
//                System.out.println("********** StartupLoginManagedBean.hasProfilePicturePng");
//
//                File currentStartupProfilePicture = new File(profilePicturePath + ".png");
//                FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
//                        .put("currentStartupProfilePicture", currentStartupProfilePicture);
//            }
        } catch (NonUniqueResultException | NoResultException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login credential: " + ex.getMessage(), null));
        }
    }

    public void logout(ActionEvent event) throws IOException {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.xhtml");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
