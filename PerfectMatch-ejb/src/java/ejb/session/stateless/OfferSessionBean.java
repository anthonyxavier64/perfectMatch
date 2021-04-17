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
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.CreateNewOfferException;
import util.exception.InputDataValidationException;
import util.exception.OfferNotFoundException;
import util.exception.PostingNotFoundException;
import util.exception.StudentNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Stateless
public class OfferSessionBean implements OfferSessionBeanLocal {

    @EJB
    private PostingSessionBeanLocal postingSessionBeanLocal;

    @EJB
    private StudentSessionBeanLocal studentSessionBeanLocal;

    @PersistenceContext(unitName = "PerfectMatch-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public OfferSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public Offer createNewOffer(Offer offer, Long studentId, Long postingId) throws InputDataValidationException, CreateNewOfferException {

        Set<ConstraintViolation<Offer>> constraintViolations = validator.validate(offer);

        if (!constraintViolations.isEmpty()) {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }

        try {
            Student student = studentSessionBeanLocal.retrieveStudentByStudentId(studentId);
            Posting posting = postingSessionBeanLocal.retrievePostingByPostingId(postingId);

            offer.setStudent(student);
            offer.setPosting(posting);
            em.persist(offer);
            em.flush();

            student.getOffers().add(offer);
            posting.getOffers().add(offer);

            return offer;

        } catch (StudentNotFoundException | PostingNotFoundException ex) {
            throw new CreateNewOfferException(ex.getMessage());
        }
    }

    @Override
    public List<Offer> retrieveAllOffers() {
        Query q = em.createQuery("SELECT o FROM Offer o");
        return q.getResultList();
    }

    @Override
    public Offer retrieveOfferByOfferId(Long offerId) throws OfferNotFoundException {
        Offer offer = em.find(Offer.class, offerId);
        if (offer == null) {
            throw new OfferNotFoundException("Offer ID " + offerId + " does not exist");
        }
        return offer;
    }

    @Override
    public Student getStudentByOfferId(Long offerId) throws OfferNotFoundException {
        Offer offer = retrieveOfferByOfferId(offerId);
        return offer.getStudent();
    }

    @Override
    public Posting getPostingByOfferId(Long offerId) throws OfferNotFoundException {
        Offer offer = retrieveOfferByOfferId(offerId);
        return offer.getPosting();
    }

    @Override
    public void updateOffer(Offer offer) {
        em.merge(offer);
    }

    @Override
    public void updatePostingResult(Offer offer) throws OfferNotFoundException {
        Posting post = getPostingByOfferId(offer.getOfferId());
        post.setAcceptedStudent(offer.getStudent());
        em.persist(post);
        offer.setPosting(post);

        em.flush();
    }

    @Override
    public void deleteOffer(Long offerId) throws OfferNotFoundException {
        try {
            Offer offerToRemove = retrieveOfferByOfferId(offerId);

            Student studentOffered = offerToRemove.getStudent();

            if (studentOffered != null) {
                studentOffered.getOffers().remove(offerToRemove);
            }

            em.remove(offerToRemove);
        } catch (NoResultException ex) {
            throw new OfferNotFoundException("Offer ID" + offerId + "does not exist!");
        }

    }

    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<Offer>> constraintViolations) {
        String msg = "Input data validation error!:";

        for (ConstraintViolation constraintViolation : constraintViolations) {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }

        return msg;
    }
}
