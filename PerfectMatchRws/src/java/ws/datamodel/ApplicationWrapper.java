/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import enumeration.ApplicationStatus;

/**
 *
 * @author yappeizhen
 */
public class ApplicationWrapper {

    private Long applicationId;
    private Boolean offerSent;
    private String applicationStatus;
    private Long postingId;
    private Long studentId;
    private PostingWrapper posting;
    private StudentWrapper student;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Boolean getOfferSent() {
        return offerSent;
    }

    public void setOfferSent(Boolean offerSent) {
        this.offerSent = offerSent;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public PostingWrapper getPosting() {
        return posting;
    }

    public void setPosting(PostingWrapper posting) {
        this.posting = posting;
    }

    public StudentWrapper getStudent() {
        return student;
    }

    public void setStudent(StudentWrapper student) {
        this.student = student;
    }

    public Long getPostingId() {
        return postingId;
    }

    public void setPostingId(Long postingId) {
        this.postingId = postingId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
