/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import entity.ReviewOfStartUp;
import entity.ReviewOfStudent;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Phire
 */
@Named(value = "reviewsManagedBean")
@ViewScoped
public class ReviewsManagedBean implements Serializable {

    private ReviewOfStartUp reviewOfStartUpToView;
    
    private ReviewOfStudent reviewOfStudentToView;
    
    /**
     * Creates a new instance of ReviewsManagedBean
     */
    public ReviewsManagedBean() {
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
    
    
}
