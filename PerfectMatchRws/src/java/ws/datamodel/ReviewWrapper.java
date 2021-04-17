/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

/**
 *
 * @author yappeizhen
 */
public class ReviewWrapper {
    private Long reviewId;
    private Integer rating;
    private String review;
    
    private Long studentId;
    private Long startUpBeingRatedId;

    public ReviewWrapper() {
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStartUpBeingRatedId() {
        return startUpBeingRatedId;
    }

    public void setStartUpBeingRatedId(Long startUpBeingRatedId) {
        this.startUpBeingRatedId = startUpBeingRatedId;
    }
    
    
}
