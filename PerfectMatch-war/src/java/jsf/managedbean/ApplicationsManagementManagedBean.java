/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.ApplicationSessionBeanLocal;
import ejb.session.stateless.StudentSessionBeanLocal;
import entity.Application;
import entity.Student;
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
import util.exception.StudentNotFoundException;

/**
 *
 * @author Phire
 */
@Named(value = "applicationsManagementManagedBean")
@ViewScoped
public class ApplicationsManagementManagedBean implements Serializable {

    @EJB
    private StudentSessionBeanLocal studentSessionBean;

    @EJB
    private ApplicationSessionBeanLocal applicationSessionBean;

    @Inject
    private ViewApplicationManagedBean viewApplicationManagedBean;
    
    private List<Application> listOfApplications;
    private List<Application> filteredApplications;

    private Application selectedApplicationToUpdate;
    private Long studentIdUpdate;

    private Application selectedApplicationToDelete;

    /**
     * Creates a new instance of ApplicationsManagementManagedBean
     */
    public ApplicationsManagementManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {
        setListOfApplications(applicationSessionBean.retrieveAllApplication());
        for(Application app:listOfApplications) {
            System.out.println("App Id: " + app.getApplicationId());
        }
    }

    public void viewApplicationDetails(ActionEvent event) throws IOException {
        Long applicationIdToView = (Long) event.getComponent().getAttributes().get("applicationId");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("applicationIdToView", applicationIdToView);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewAllApplcations.xhtml");
    }

    public void doUpdateApplication(ActionEvent event) throws StudentNotFoundException {

        selectedApplicationToUpdate = (Application) event.getComponent().getAttributes().get("selectedApplicationToUpdate");

        //System.out.println((long) event.getComponent().getAttributes().get("studentToUpdate"));
        studentIdUpdate = selectedApplicationToUpdate.getStudent().getStudentId();

        Student toUpdate = studentSessionBean.retrieveStudentByStudentId(studentIdUpdate);

        selectedApplicationToUpdate.setStudent(toUpdate);
        applicationSessionBean.updateApplication(selectedApplicationToUpdate);
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

}
