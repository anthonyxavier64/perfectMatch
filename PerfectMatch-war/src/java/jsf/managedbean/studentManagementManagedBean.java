/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.StartUpSessionBeanLocal;
import ejb.session.stateless.StudentSessionBeanLocal;
import entity.StartUp;
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
import util.exception.StudentNotFoundException;

/**
 *
 * @author user
 */
@Named(value = "studentManagementManagedBean")
@ViewScoped
public class studentManagementManagedBean implements Serializable {

    @EJB
    private StartUpSessionBeanLocal startUpSessionBean;

    @EJB
    private StudentSessionBeanLocal studentSessionBean;
    
    @Inject
    private viewStudentManagedBean viewStudentManagedBean;
    
    private Student newStudent;
    private List<Student> listOfStudents;
    
    private List<Student> filteredStudents;
    
    private StartUp currentStartUp;
    private Student favouriteStudent;
    
    private Student studentToView;
    private long studentIdToView;

    /**
     * Creates a new instance of studentManagementManagedBean
     */
    public studentManagementManagedBean() {
        newStudent = new Student();
    }
    
    @PostConstruct
    public void postConstruct() 
    {
        listOfStudents = studentSessionBean.getAllStudents();
        setCurrentStartUp((StartUp) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("currentStartUp"));
    }
    
    public void viewStudentDetails(ActionEvent event) throws IOException 
    {
        Long studentIdToView = (Long) event.getComponent().getAttributes().get("studentId");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("studentIdToView", studentIdToView);
        FacesContext.getCurrentInstance().getExternalContext().redirect("studentManagement.xhtml");
    }
    
        
    public void retrieveStudentByStudentId(ActionEvent event) throws IOException, StudentNotFoundException 
    {
            studentIdToView = (Long)event.getComponent().getAttributes().get("studentId");
            System.out.println("Student ID is:" + getStudentIdToView());

            if (studentSessionBean.retrieveStudentByStudentId(getStudentIdToView()) == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Student ID " + getStudentIdToView() + " does not exist.", null));
            } else {
                setStudentToView(studentSessionBean.retrieveStudentByStudentId(getStudentIdToView()));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Student ID " + getStudentToView().getStudentId() + " is selected.", null));
            }
    }
    
    public void addStudentToFavourite(ActionEvent event) 
    {
        
        setFavouriteStudent((Student)event.getComponent().getAttributes().get("favStudent"));
        System.out.println(getFavouriteStudent().getStudentId());
     
        if (getCurrentStartUp().getFavouriteStudents().contains(getFavouriteStudent())) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Student ID " + getFavouriteStudent().getStudentId() + " is already in your favourites.", null));
           return;
        }
        
        getCurrentStartUp().getFavouriteStudents().add(getFavouriteStudent());
        
        startUpSessionBean.updateStartUp(getCurrentStartUp());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Student ID " + getFavouriteStudent().getStudentId() + " has been added to favourites.", null));
        
        
    }
    
    public void removeStudentFromFavourite(ActionEvent event) 
    {
        setFavouriteStudent((Student)event.getComponent().getAttributes().get("favStudent"));
        System.out.println(getFavouriteStudent().getStudentId());
        
        getCurrentStartUp().getFavouriteStudents().remove(getFavouriteStudent());
        
        startUpSessionBean.updateStartUp(getCurrentStartUp());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Student ID " + getFavouriteStudent().getStudentId() + " has been removed from favourites.", null));
        
        
    }
    
    public void updateFavouritesList(ActionEvent event) throws IOException {
        for (int i = 0; i < listOfStudents.size(); i++) {
             if (getCurrentStartUp().getFavouriteStudents().contains(listOfStudents.get(i))) {
                 int indexToUpdate = getCurrentStartUp().getFavouriteStudents().indexOf(listOfStudents.get(i));
                 getCurrentStartUp().getFavouriteStudents().set(indexToUpdate, listOfStudents.get(i));
             }
        }        
        FacesContext.getCurrentInstance().getExternalContext()
            .redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/studentManagement/viewFavouriteStudents.xhtml");
        
    }
    
    public void compareStudents(ActionEvent event) throws IOException {
     FacesContext.getCurrentInstance().getExternalContext()
            .redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/studentManagement/compareStudents.xhtml");
        
    }
    


    public StudentSessionBeanLocal getStudentSessionBean() {
        return studentSessionBean;
    }

    public void setStudentSessionBean(StudentSessionBeanLocal studentSessionBean) {
        this.studentSessionBean = studentSessionBean;
    }

    public Student getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(Student newStudent) {
        this.newStudent = newStudent;
    }

    public List<Student> getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    public List<Student> getFilteredStudents() {
        return filteredStudents;
    }

    public void setFilteredStudents(List<Student> filteredStudents) {
        this.filteredStudents = filteredStudents;
    }

    public viewStudentManagedBean getViewStudentManagedBean() {
        return viewStudentManagedBean;
    }

    public void setViewStudentManagedBean(viewStudentManagedBean viewStudentManagedBean) {
        this.viewStudentManagedBean = viewStudentManagedBean;
    }

    public StartUp getCurrentStartUp() {
        return currentStartUp;
    }

    public void setCurrentStartUp(StartUp currentStartUp) {
        this.currentStartUp = currentStartUp;
    }

    public Student getFavouriteStudent() {
        return favouriteStudent;
    }

    public void setFavouriteStudent(Student favouriteStudent) {
        this.favouriteStudent = favouriteStudent;
    }

    public Student getStudentToView() {
        return studentToView;
    }

    public void setStudentToView(Student studentToView) {
        this.studentToView = studentToView;
    }

    public long getStudentIdToView() {
        return studentIdToView;
    }

    public void setStudentIdToView(long studentIdToView) {
        this.studentIdToView = studentIdToView;
    }
    
    
    
}
