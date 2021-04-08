
package ejb.session.stateless;

import entity.ReviewOfStudent;
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
import util.exception.CreateNewReviewOfStudentException;
import util.exception.InputDataValidationException;
import util.exception.ReviewOfStudentNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Stateless
public class ReviewOfStudentSessionBean implements ReviewOfStudentSessionBeanLocal {

    @PersistenceContext(unitName = "PerfectMatch-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public ReviewOfStudentSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public ReviewOfStudent createNewStartUp(ReviewOfStudent review) throws CreateNewReviewOfStudentException, InputDataValidationException {
        Set<ConstraintViolation<ReviewOfStudent>> constraintViolations = validator.validate(review);

        if (!constraintViolations.isEmpty()) {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }

        if (review != null) {
            em.persist(review);
            em.flush();
            return review;
        } else {
            throw new CreateNewReviewOfStudentException("Review information not provided");
        }
    }

    @Override
    public List<ReviewOfStudent> retrieveAllReviewOfStartUp() {
        Query q = em.createQuery("SELECT r FROM ReviewOfStudent r");
        List<ReviewOfStudent> results = q.getResultList();
        return results;
    }

    @Override
    public ReviewOfStudent retrieveReviewOfStudentByReviewOfStudentId(Long reviewOfStudentId) throws ReviewOfStudentNotFoundException {
        ReviewOfStudent review = em.find(ReviewOfStudent.class, reviewOfStudentId);
        if (review == null) {
            throw new ReviewOfStudentNotFoundException("ReviewOfStudent ID " + reviewOfStudentId + " does not exist");
        }
        return review;
    }
    
    @Override
    public void updateReviewOfStartUp(ReviewOfStudent review) {
        em.merge(review);
    }
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<ReviewOfStudent>> constraintViolations) {
        String msg = "Input data validation error!:";

        for (ConstraintViolation constraintViolation : constraintViolations) {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }

        return msg;
    }

}
