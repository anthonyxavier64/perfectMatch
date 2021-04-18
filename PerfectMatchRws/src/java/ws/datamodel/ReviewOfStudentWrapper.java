/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.ReviewOfStudent;

/**
 *
 * @author yappeizhen
 */
public class ReviewOfStudentWrapper {
    private Long reviewOfStudentId;
    private Integer rating;    
    private String review;
    private StartUpWrapper startup;
    private StudentWrapper studentBeingRated;

    public ReviewOfStudentWrapper() {
    }

    public ReviewOfStudentWrapper(Long reviewOfStudentId, Integer rating, String review, StartUpWrapper startup, StudentWrapper studentBeingRated) {
        this.reviewOfStudentId = reviewOfStudentId;
        this.rating = rating;
        this.review = review;
        this.startup = startup;
        this.studentBeingRated = studentBeingRated;
    }
    
    public static ReviewOfStudentWrapper convertReviewToWrapper(ReviewOfStudent rw) { 
        ReviewOfStudentWrapper newWrap = new ReviewOfStudentWrapper();
        newWrap.setRating(rw.getRating());
        newWrap.setReview(rw.getReview());
        newWrap.setStartup(StartUpWrapper.convertStartUpToStartUpWrapper(rw.getStartup()));
        newWrap.setStudentBeingRated(StudentWrapper.convertStudentToStudentWrapper(rw.getStudentBeingRated()));
        
        return newWrap;
    }

    public Long getReviewOfStudentId() {
        return reviewOfStudentId;
    }

    public void setReviewOfStudentId(Long reviewOfStudentId) {
        this.reviewOfStudentId = reviewOfStudentId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public StartUpWrapper getStartup() {
        return startup;
    }

    public void setStartup(StartUpWrapper startup) {
        this.startup = startup;
    }

    public StudentWrapper getStudentBeingRated() {
        return studentBeingRated;
    }

    public void setStudentBeingRated(StudentWrapper studentBeingRated) {
        this.studentBeingRated = studentBeingRated;
    }
    
    
}
