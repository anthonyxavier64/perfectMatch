/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Application;
import enumeration.ApplicationStatus;

/**
 *
 * @author yappeizhen
 */
public class ApplicationWrapper {

    private Long applicationId;
    private Boolean offerSent;
    private ApplicationStatus applicationStatus;
    private Long postingId;
    private Long studentId;
    private PostingWrapper posting;
    private StudentWrapper student;

    public static ApplicationWrapper createApplicationWrapper(Application app) {
        ApplicationWrapper appWrapper = new ApplicationWrapper();
        appWrapper.setApplicationId(app.getApplicationId());
        appWrapper.setOfferSent(app.isOfferSent());
        appWrapper.setApplicationStatus(app.getApplicationStatus());
        appWrapper.setPostingId(app.getPosting().getPostingId());
        appWrapper.setStudentId(app.getStudent().getStudentId());
        appWrapper.setPosting(PostingWrapper.convertPostingToPostingWrapper(app.getPosting()));
        appWrapper.setStudent(StudentWrapper.convertStudentToStudentWrapper(app.getStudent()));
        return appWrapper;
    }

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

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
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
