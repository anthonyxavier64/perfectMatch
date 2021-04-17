/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.ApplicationSessionBeanLocal;
import ejb.session.stateless.PostingSessionBeanLocal;
import ejb.session.stateless.StudentSessionBeanLocal;
import entity.Application;
import entity.Project;
import entity.Student;
import enumeration.ApplicationStatus;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import util.exception.ApplicationNotFoundException;
import util.exception.ProjectNotFoundException;
import util.exception.StudentNotFoundException;

/**
 *
 * @author Phire
 */
@Named(value = "applicationsManagementManagedBean")
@ViewScoped
public class ApplicationsManagementManagedBean implements Serializable {

    @EJB
    private PostingSessionBeanLocal postingSessionBean;

    @EJB
    private StudentSessionBeanLocal studentSessionBean;

    @EJB
    private ApplicationSessionBeanLocal applicationSessionBean;

    @Inject
    private ViewApplicationManagedBean viewApplicationManagedBean;

    private List<Application> listOfApplications;
    private List<Application> filteredApplications;
    private ApplicationStatus[] applicationStatus;

    private Application selectedApplicationToUpdate;
    private Long studentIdUpdate;
    private ApplicationStatus updateApplicationStatus;

    private Application selectedApplicationToDelete;

    /**
     * Creates a new instance of ApplicationsManagementManagedBean
     */
    public ApplicationsManagementManagedBean() {
        applicationStatus = ApplicationStatus.values();
    }

    @PostConstruct
    public void postConstruct() {
        setListOfApplications(applicationSessionBean.retrieveAllApplication());
    }

    public void viewApplicationDetails(ActionEvent event) throws IOException {
        Long applicationIdToView = (Long) event.getComponent().getAttributes().get("applicationId");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("applicationIdToView", applicationIdToView);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewAllApplcations.xhtml");
    }

    public void doUpdateApplication(ActionEvent event) throws StudentNotFoundException, ProjectNotFoundException {
        //System.out.println((long) event.getComponent().getAttributes().get("studentToUpdate"));
        selectedApplicationToUpdate.setApplicationStatus(updateApplicationStatus);
        Student studentToUpdate = studentSessionBean.retrieveStudentByStudentId(studentIdUpdate);

        if (updateApplicationStatus.equals(ApplicationStatus.ACCEPTED)) {
            System.out.println("********** ApplicationsManagementManagedBean.doUpdateApplication.Accepted");
            Project projectToUpdate = postingSessionBean.retrieveProjectByProjectId(selectedApplicationToUpdate.getPosting().getPostingId());
            if (projectToUpdate.getAcceptedStudent() != null) {
                System.out.println("********** ApplicationsManagementManagedBean.doUpdateApplication.AcceptError");

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "This project has already accepted a student!",
                                null));
            } else {
                projectToUpdate.setAcceptedStudent(studentToUpdate); 
                postingSessionBean.updatePosting(projectToUpdate);

                studentToUpdate.getPostings().add(projectToUpdate);
                studentSessionBean.editStudentDetails(studentToUpdate);
                applicationSessionBean.updateApplication(selectedApplicationToUpdate);
            }
        }

        if (updateApplicationStatus.equals(ApplicationStatus.REJECTED)) {
            System.out.println("********** ApplicationsManagementManagedBean.doUpdateApplication.Rejected");
            Project projectToUpdate = postingSessionBean.retrieveProjectByProjectId(selectedApplicationToUpdate.getPosting().getPostingId());
            if (projectToUpdate.getAcceptedStudent().equals(studentToUpdate)) {
                projectToUpdate.setAcceptedStudent(null);
                postingSessionBean.updatePosting(projectToUpdate);

                studentToUpdate.getPostings().remove(projectToUpdate);
                studentSessionBean.editStudentDetails(studentToUpdate);
                applicationSessionBean.updateApplication(selectedApplicationToUpdate);
            }
        }
        
        if (updateApplicationStatus.equals(ApplicationStatus.PENDING)) {
            System.out.println("********** ApplicationsManagementManagedBean.doUpdateApplication.Pending");
            Project projectToUpdate = postingSessionBean.retrieveProjectByProjectId(selectedApplicationToUpdate.getPosting().getPostingId());
            if (projectToUpdate.getAcceptedStudent().equals(studentToUpdate)) {
                projectToUpdate.setAcceptedStudent(null);
                postingSessionBean.updatePosting(projectToUpdate);

                studentToUpdate.getPostings().remove(projectToUpdate);
                studentSessionBean.editStudentDetails(studentToUpdate);
                applicationSessionBean.updateApplication(selectedApplicationToUpdate);
            }
        }
    }

    public void deleteApplication(ActionEvent event) {
        try {
            selectedApplicationToDelete = (Application) event.getComponent().getAttributes().get("selectedApplicationToDelete");

            //System.out.println(selectedApplicationToDelete.getApplicationId());
            applicationSessionBean.deleteApplication(selectedApplicationToDelete.getApplicationId());

            listOfApplications.remove(selectedApplicationToDelete);

            if (filteredApplications != null) {
                filteredApplications.remove(selectedApplicationToDelete);
            }

            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirm Delete?", null));
        } catch (ApplicationNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while deleting application: " + ex.getMessage(), null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }

    public void selectProjectToUpdate(ActionEvent event) {
        selectedApplicationToUpdate = ((Application) event.getComponent().getAttributes().get("selectedApplicationToUpdate"));
        updateApplicationStatus = selectedApplicationToUpdate.getApplicationStatus();
        studentIdUpdate = selectedApplicationToUpdate.getStudent().getStudentId();
    }

    public List<Application> getListOfApplications() {
        return listOfApplications;
    }

    public void setListOfApplications(List<Application> listOfApplications) {
        this.listOfApplications = listOfApplications;
    }

    public List<Application> getFilteredApplications() {
        return filteredApplications;
    }

    public void setFilteredApplications(List<Application> filteredApplications) {
        this.filteredApplications = filteredApplications;
    }

    public Application getSelectedApplicationToUpdate() {
        return selectedApplicationToUpdate;
    }

    public void setSelectedApplicationToUpdate(Application selectedApplicationToUpdate) {
        this.selectedApplicationToUpdate = selectedApplicationToUpdate;
    }

    public Long getStudentIdUpdate() {
        return studentIdUpdate;
    }

    public void setStudentIdUpdate(Long studentIdUpdate) {
        this.studentIdUpdate = studentIdUpdate;
    }

    public Application getSelectedApplicationToDelete() {
        return selectedApplicationToDelete;
    }

    public void setSelectedApplicationToDelete(Application selectedApplicationToDelete) {
        this.selectedApplicationToDelete = selectedApplicationToDelete;
    }

    public ViewApplicationManagedBean getViewApplicationManagedBean() {
        return viewApplicationManagedBean;
    }

    public void setViewApplicationManagedBean(ViewApplicationManagedBean viewApplicationManagedBean) {
        this.viewApplicationManagedBean = viewApplicationManagedBean;
    }

    public ApplicationStatus[] getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus[] applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public ApplicationStatus getUpdateApplicationStatus() {
        return updateApplicationStatus;
    }

    public void setUpdateApplicationStatus(ApplicationStatus updateApplicationStatus) {
        this.updateApplicationStatus = updateApplicationStatus;
    }

}
