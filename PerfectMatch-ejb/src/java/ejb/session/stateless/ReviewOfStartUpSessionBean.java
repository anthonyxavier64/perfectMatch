/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.ReviewOfStartUp;
import entity.StartUp;
import entity.Student;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.CreateNewReviewOfStartUpException;
import util.exception.DuplicateReviewException;
import util.exception.InputDataValidationException;
import util.exception.ReviewOfStartUpNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Stateless
public class ReviewOfStartUpSessionBean implements ReviewOfStartUpSessionBeanLocal {

    @PersistenceContext(unitName = "PerfectMatch-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public ReviewOfStartUpSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public ReviewOfStartUp createNewStartUpReview(ReviewOfStartUp review) throws CreateNewReviewOfStartUpException, InputDataValidationException, DuplicateReviewException {
        Set<ConstraintViolation<ReviewOfStartUp>> constraintViolations = validator.validate(review);

        if (!constraintViolations.isEmpty()) {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
        
        List<ReviewOfStartUp> prevReviews = review.getStartUpBeingRated().getReviews();
        for (ReviewOfStartUp r : prevReviews) {
            if (r.getStudent().getStudentId() == review.getStudent().getStudentId()) {
                throw new DuplicateReviewException("You have already reviewed this company");
            }
        }

        if (review != null) {
            em.persist(review);
            em.flush();
            return review;
        } else {
            throw new CreateNewReviewOfStartUpException("Review information not provided");
        }
    }

    @Override
    public ReviewOfStartUp addStartupReview(Long startupId, Long studentId, ReviewOfStartUp review) throws CreateNewReviewOfStartUpException, InputDataValidationException, DuplicateReviewException {
        StartUp startup = em.find(StartUp.class, startupId);
        Student stud = em.find(Student.class, studentId);
        review.setStartUpBeingRated(startup);
        review.setStudent(stud);
        try {
            review = createNewStartUpReview(review);
        } catch (CreateNewReviewOfStartUpException | InputDataValidationException ex) {
            throw ex;
        }

        startup.getReviews().add(review);

        em.flush();

        return review;
    }

    @Override
    public List<ReviewOfStartUp> retrieveAllReviewOfStartUp() {
        Query q = em.createQuery("SELECT r FROM ReviewOfStartUp r");
        List<ReviewOfStartUp> results = q.getResultList();
        return results;
    }

    @Override
    public ReviewOfStartUp retrieveReviewOfStartUpByReviewOfStartUpId(Long reviewOfStartUpId) throws ReviewOfStartUpNotFoundException {
        ReviewOfStartUp review = em.find(ReviewOfStartUp.class, reviewOfStartUpId);
        if (review == null) {
            throw new ReviewOfStartUpNotFoundException("ReviewOfStartUp ID " + reviewOfStartUpId + " does not exist");
        }
        return review;
    }

    @Override
    public void updateReviewOfStartUp(ReviewOfStartUp review) {
        em.merge(review);
    }

    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<ReviewOfStartUp>> constraintViolations) {
        String msg = "Input data validation error!:";

        for (ConstraintViolation constraintViolation : constraintViolations) {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }

        return msg;
    }

}
