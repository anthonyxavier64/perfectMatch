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
public class ReviewOfStudent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewOfStudentId;
    
    @NotNull
    @Column(nullable = false)
    private Integer rating;    
   
    @Column(length = 512)
    private String review;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private StartUp startup;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Student studentBeingRated;

    public ReviewOfStudent() {
    }

    public ReviewOfStudent(Integer rating, String review, StartUp startup, Student studentBeingRated) {
        this.rating = rating;
        this.review = review;
        this.startup = startup;
        this.studentBeingRated = studentBeingRated;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewOfStudentId != null ? reviewOfStudentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the reviewOfStudentId fields are not set
        if (!(object instanceof ReviewOfStudent)) {
            return false;
        }
        ReviewOfStudent other = (ReviewOfStudent) object;
        if ((this.reviewOfStudentId == null && other.reviewOfStudentId != null) || (this.reviewOfStudentId != null && !this.reviewOfStudentId.equals(other.reviewOfStudentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Review[ id=" + reviewOfStudentId + " ]";
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

    public StartUp getStartup() {
        return startup;
    }

    public void setStartup(StartUp startup) {
        this.startup = startup;
    }

    public Student getStudentBeingRated() {
        return studentBeingRated;
    }

    public void setStudentBeingRated(Student studentBeingRated) {
        this.studentBeingRated = studentBeingRated;
    }
        
}
