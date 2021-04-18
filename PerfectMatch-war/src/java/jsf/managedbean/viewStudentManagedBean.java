/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import entity.Student;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

/**
 *
 * @author user
 */
@Named(value = "viewStudentManagedBean")
@ViewScoped
public class viewStudentManagedBean implements Serializable {

    private Student studentEntityToView;

    /**
     * Creates a new instance of viewStudentManagedBean
     */
    public viewStudentManagedBean() {
        studentEntityToView = new Student();
    }

    @PostConstruct
    public void postConstruct() {

    }

    public Student getStudentEntityToView() {
        return studentEntityToView;
    }

    public void setStudentEntityToView(Student studentEntityToView) {
        this.studentEntityToView = studentEntityToView;
    }



}
