/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.ReviewOfStartUp;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewReviewOfStartUpException;
import util.exception.DuplicateReviewException;
import util.exception.InputDataValidationException;
import util.exception.ReviewOfStartUpNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Local
public interface ReviewOfStartUpSessionBeanLocal {

    public List<ReviewOfStartUp> retrieveAllReviewOfStartUp();

    public ReviewOfStartUp retrieveReviewOfStartUpByReviewOfStartUpId(Long reviewOfStartUpId) throws ReviewOfStartUpNotFoundException;

    public void updateReviewOfStartUp(ReviewOfStartUp review);

    ReviewOfStartUp addStartupReview(Long startupId, Long studentId, ReviewOfStartUp review) throws CreateNewReviewOfStartUpException, InputDataValidationException, DuplicateReviewException;

    public ReviewOfStartUp createNewStartUpReview(ReviewOfStartUp review) throws CreateNewReviewOfStartUpException, InputDataValidationException, DuplicateReviewException;

}
