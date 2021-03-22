/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Offer;
import entity.Posting;
import entity.Student;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewOfferException;
import util.exception.InputDataValidationException;
import util.exception.OfferNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Local
public interface OfferSessionBeanLocal {

    public Offer createNewOffer(Offer offer, Long studentId, Long postingId) throws InputDataValidationException, CreateNewOfferException;

    public List<Offer> retrieveAllOffers();

    public Offer retrieveOfferByOfferId(Long offerId) throws OfferNotFoundException;

    public void updateOffer(Offer offer);

    public Student getStudentByOfferId(Long offerId) throws OfferNotFoundException;

    public Posting getPostingByOfferId(Long offerId) throws OfferNotFoundException;
    
}
