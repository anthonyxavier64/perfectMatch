/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Posting;
import entity.Student;
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

    public StudentWrapper getStudent() {
        return student;
    }

    public void setStudent(StudentWrapper student) {
        this.student = student;
    }
    
    
}
