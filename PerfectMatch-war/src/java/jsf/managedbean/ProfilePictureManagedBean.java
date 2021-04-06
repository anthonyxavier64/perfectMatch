/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import entity.StartUp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Phire
 */
@Named(value = "profilePictureManagedBean")
@RequestScoped
public class ProfilePictureManagedBean {

    private StartUp currentStartUp;

    /**
     * Creates a new instance of ProfilePictureManagedBean
     */
    public ProfilePictureManagedBean() {
        currentStartUp = (StartUp) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("currentStartup");
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            String fileExtension = "." + event.getFile().getFileName().split("\\.")[1];
            String outputFileName = currentStartUp.getStartupId()
                    + "_ProfilePicture"
                    + fileExtension;

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
                File oldPic = new File(profilePicturePath + ".jpg");
                oldPic.delete();
            } else if (hasProfilePictureJpeg) {
                File oldPic = new File(profilePicturePath + ".jpeg");
                oldPic.delete();
            }
            if (hasProfilePicturePng) {
                File oldPic = new File(profilePicturePath + ".png");
                oldPic.delete();
            }

            String newFilePath = FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getInitParameter("alternatedocroot_1")
                    + System.getProperty("file.separator")
                    + outputFileName;

            System.out.println("********** profilePictureManagedBean.handleFileUpload() - Original File name: " + event.getFile().getFileName());
            System.out.println("********** profilePictureManagedBean.handleFileUpload() - New File name: " + outputFileName);
            System.out.println("********** profilePictureManagedBean.handleFileUpload() - newFilePath: " + newFilePath);

            File file = new File(newFilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            int a;
            int BUFFER_SIZE = 8192;
            byte[] buffer = new byte[BUFFER_SIZE];

            InputStream inputStream = event.getFile().getInputStream();

            while (true) {
                a = inputStream.read(buffer);

                if (a < 0) {
                    break;
                }

                fileOutputStream.write(buffer, 0, a);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            FacesContext
                    .getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "File uploaded successfully", ""));
        } catch (IOException ex) {
            FacesContext
                    .getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "File upload error: " + ex.getMessage(), ""));
        }
    }

}
