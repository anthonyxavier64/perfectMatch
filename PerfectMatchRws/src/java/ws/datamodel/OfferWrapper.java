/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Job;
import entity.Offer;
import entity.Project;
import enumeration.OfferStatus;

/**
 *
 * @author yappeizhen
 */
public class OfferWrapper {
    private Long offerId;
    private String offerMessage;
    private OfferStatus offerStatus;
    private PostingWrapper posting;
    private StudentWrapper student;

    public OfferWrapper() {
    }

    public OfferWrapper(Long offerId, String offerMessage, OfferStatus offerStatus, PostingWrapper posting, StudentWrapper student) {
        this.offerId = offerId;
        this.offerMessage = offerMessage;
        this.offerStatus = offerStatus;
        this.posting = posting;
        this.student = student;
    }

    public static OfferWrapper convertOfferToOfferWrapper(Offer offer) {
        OfferWrapper offerWrapper = new OfferWrapper();
        offerWrapper.setOfferId(offer.getOfferId());
        offerWrapper.setOfferMessage(offer.getOfferMessage());
        offerWrapper.setOfferStatus(offer.getOfferStatus());
        PostingWrapper postWrap = new PostingWrapper();
        
        if (offer.getPosting() instanceof Project) {
            postWrap = ProjectWrapper.convertProjectToProjectWrapper((Project) offer.getPosting());
        } else {
            postWrap = JobWrapper.convertJobToJobWrapper((Job) offer.getPosting());
        }
        offerWrapper.setPosting(postWrap);
        
        StudentWrapper stuWrap = StudentWrapper.convertStudentToStudentWrapper(offer.getStudent());
        offerWrapper.setStudent(stuWrap);
        
        return offerWrapper;
    }
    
    public StudentWrapper getStudent() {
        return student;
    }

    public void setStudent(StudentWrapper student) {
        this.student = student;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getOfferMessage() {
        return offerMessage;
    }

    public void setOfferMessage(String offerMessage) {
        this.offerMessage = offerMessage;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public PostingWrapper getPosting() {
        return posting;
    }

    public void setPosting(PostingWrapper posting) {
        this.posting = posting;
    }
}
