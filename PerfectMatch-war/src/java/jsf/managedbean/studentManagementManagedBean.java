/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.StudentSessionBeanLocal;
import entity.Student;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author user
 */
@Named(value = "studentManagementManagedBean")
@ViewScoped
public class studentManagementManagedBean implements Serializable {

    @EJB
    private StudentSessionBeanLocal studentSessionBean;
    
    @Inject
    private viewStudentManagedBean viewStudentManagedBean;
    
    private Student newStudent;
    private List<Student> listOfStudents;
    
    private List<Student> filteredStudents;
    
    
    

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
    }
    
    public void viewStudentDetails(ActionEvent event) throws IOException 
    {
        Long studentIdToView = (Long)event.getComponent().getAttributes().get("studentId");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("studentIdToView", studentIdToView);
        FacesContext.getCurrentInstance().getExternalContext().redirect("studentManagement.xhtml");
    }
    
//        public void viewStudentDetails(ActionEvent event) throws IOException {
//        Long studentIdToView = (Long) event.getComponent().getAttributes().get("studentId");
//        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("studentIdToView", studentIdToView);
//        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("backMode", "viewStudent");
//        FacesContext.getCurrentInstance().getExternalContext().redirect("viewStudentDetails.xhtml");
//    }

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
    
    
    
}
