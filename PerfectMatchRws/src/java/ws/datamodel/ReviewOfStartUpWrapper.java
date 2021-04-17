/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.ReviewOfStartUp;

/**
 *
 * @author yappeizhen
 */
public class ReviewOfStartUpWrapper {
    private Long reviewOfStartUpId;
    private Integer rating;
    private String review;
    private StudentWrapper student;
    private StartUpWrapper startUpBeingRated;

    public ReviewOfStartUpWrapper() {
    }

    public ReviewOfStartUpWrapper(Long reviewOfStartUpId, Integer rating, String review, StudentWrapper student, StartUpWrapper startUpBeingRated) {
        this.reviewOfStartUpId = reviewOfStartUpId;
        this.rating = rating;
        this.review = review;
        this.student = student;
        this.startUpBeingRated = startUpBeingRated;
    }

    public static ReviewOfStartUp convertWrapperToReview(ReviewOfStartUpWrapper wrapper) {
        ReviewOfStartUp newReview = new ReviewOfStartUp();

        newReview.setRating(wrapper.getRating());
        newReview.setReview(wrapper.getReview());

        return newReview;
    }

    public static ReviewOfStartUpWrapper convertReviewToWrapper(ReviewOfStartUp review) {
        ReviewOfStartUpWrapper newReview = new ReviewOfStartUpWrapper();
        newReview.setReviewOfStartUpId(review.getReviewOfStartUpId());
        newReview.setRating(review.getRating());
        newReview.setReview(review.getReview());

        return newReview;
    }

    public Long getReviewOfStartUpId() {
        return reviewOfStartUpId;
    }

    public void setReviewOfStartUpId(Long reviewOfStartUpId) {
        this.reviewOfStartUpId = reviewOfStartUpId;
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

    public StudentWrapper getStudent() {
        return student;
    }

    public void setStudent(StudentWrapper student) {
        this.student = student;
    }

    public StartUpWrapper getStartUpBeingRated() {
        return startUpBeingRated;
    }

    public void setStartUpBeingRated(StartUpWrapper startUpBeingRated) {
        this.startUpBeingRated = startUpBeingRated;
    } 
}
