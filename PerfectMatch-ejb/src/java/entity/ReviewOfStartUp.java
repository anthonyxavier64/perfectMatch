/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author yappeizhen
 */
@Entity
public class ReviewOfStartUp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewOfStartUpId;
    
    @NotNull
    @Column(nullable = false)
    private Integer rating;    
   
    @Column(length = 512)
    private String review;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Student student;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private StartUp startUpBeingRated;

    public ReviewOfStartUp() {
    }

    public ReviewOfStartUp(Integer rating, String review, Student student, StartUp startUpBeingRated) {
        this.rating = rating;
        this.review = review;
        this.student = student;
        this.startUpBeingRated = startUpBeingRated;
    }
    
    public Long getReviewOfStartUpId() {
        return reviewOfStartUpId;
    }

    public void setReviewOfStartUpId(Long reviewOfStartUpId) {
        this.reviewOfStartUpId = reviewOfStartUpId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewOfStartUpId != null ? reviewOfStartUpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the reviewOfStartUpId fields are not set
        if (!(object instanceof ReviewOfStartUp)) {
            return false;
        }
        ReviewOfStartUp other = (ReviewOfStartUp) object;
        if ((this.reviewOfStartUpId == null && other.reviewOfStartUpId != null) || (this.reviewOfStartUpId != null && !this.reviewOfStartUpId.equals(other.reviewOfStartUpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReviewOfStartUp[ id=" + reviewOfStartUpId + " ]";
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StartUp getStartUpBeingRated() {
        return startUpBeingRated;
    }

    public void setStartUpBeingRated(StartUp startUpBeingRated) {
        this.startUpBeingRated = startUpBeingRated;
    }
    
    
    
}
