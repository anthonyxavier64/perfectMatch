/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.ReviewOfStudentSessionBeanLocal;
import ejb.session.stateless.StudentSessionBeanLocal;
import entity.Posting;
import entity.ReviewOfStartUp;
import entity.ReviewOfStudent;
import entity.StartUp;
import entity.Student;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.exception.CreateNewReviewOfStudentException;
import util.exception.InputDataValidationException;

/**
 *
 * @author Phire
 */
@Named(value = "reviewsManagedBean")
@ViewScoped
public class ReviewsManagedBean implements Serializable {

    @EJB
    private StudentSessionBeanLocal studentSessionBean;

    @EJB
    private ReviewOfStudentSessionBeanLocal reviewOfStudentSessionBean;

    private ReviewOfStartUp reviewOfStartUpToView;

    private ReviewOfStudent reviewOfStudentToView;

    // To create new reviews
    private StartUp currentStartUp;
    private ReviewOfStudent newReviewOfStudent;
    private List<Posting> allPostingsByCurrentStartUp;
    private List<Student> allStudentsEmployedByCurrentStartUp;
    private Student studentToBeRated;

    /**
     * Creates a new instance of ReviewsManagedBean
     */
    public ReviewsManagedBean() {
        newReviewOfStudent = new ReviewOfStudent();
        allPostingsByCurrentStartUp = new ArrayList<>();
        allStudentsEmployedByCurrentStartUp = new ArrayList<>();
    }

    @PostConstruct
    public void postConstruct() {
        currentStartUp = (StartUp) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("currentStartUp");

        for (Posting posting : currentStartUp.getPostings()) {
            if (posting.getAcceptedStudent() != null) {
                allStudentsEmployedByCurrentStartUp.add(posting.getAcceptedStudent());
                System.out.println("Student Name: " + posting.getAcceptedStudent().getName());
            }
        }
        
        FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap()
                .put("allStudentsEmployedByCurrentStartUp", allStudentsEmployedByCurrentStartUp);
    }

    public void createNewReviewOfStudent(ActionEvent event) {
        newReviewOfStudent.setStartup(currentStartUp);

        try {
//            newReviewOfStudent.setStudentBeingRated(studentSessionBean.retrieveStudentByStudentId(studentToBeRated));

            ReviewOfStudent review = reviewOfStudentSessionBean.createNewStartUp(newReviewOfStudent);
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "The review was successfully created (ID: " + review.getReviewOfStudentId() + ")",
                                    null));

            newReviewOfStudent = new ReviewOfStudent();
        } catch (CreateNewReviewOfStudentException
                | InputDataValidationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the review: " + ex.getMessage(), null));
        }
    }

    public ReviewOfStartUp getReviewOfStartUpToView() {
        return reviewOfStartUpToView;
    }

    public void setReviewOfStartUpToView(ReviewOfStartUp reviewOfStartUpToView) {
        this.reviewOfStartUpToView = reviewOfStartUpToView;
    }

    public ReviewOfStudent getReviewOfStudentToView() {
        return reviewOfStudentToView;
    }

    public void setReviewOfStudentToView(ReviewOfStudent reviewOfStudentToView) {
        this.reviewOfStudentToView = reviewOfStudentToView;
    }

    public StartUp getCurrentStartUp() {
        return currentStartUp;
    }

    public void setCurrentStartUp(StartUp currentStartUp) {
        this.currentStartUp = currentStartUp;
    }

    public ReviewOfStudent getNewReviewOfStudent() {
        return newReviewOfStudent;
    }

    public void setNewReviewOfStudent(ReviewOfStudent newReviewOfStudent) {
        this.newReviewOfStudent = newReviewOfStudent;
    }

    public List<Posting> getAllPostingsByCurrentStartUp() {
        return allPostingsByCurrentStartUp;
    }

    public void setAllPostingsByCurrentStartUp(List<Posting> allPostingsByCurrentStartUp) {
        this.allPostingsByCurrentStartUp = allPostingsByCurrentStartUp;
    }

    public List<Student> getAllStudentsEmployedByCurrentStartUp() {
        return allStudentsEmployedByCurrentStartUp;
    }

    public void setAllStudentsEmployedByCurrentStartUp(List<Student> allStudentsEmployedByCurrentStartUp) {
        this.allStudentsEmployedByCurrentStartUp = allStudentsEmployedByCurrentStartUp;
    }

    public Student getStudentToBeRated() {
        return studentToBeRated;
    }

    public void setStudentToBeRated(Student studentToBeRated) {
        this.studentToBeRated = studentToBeRated;
    }

}
