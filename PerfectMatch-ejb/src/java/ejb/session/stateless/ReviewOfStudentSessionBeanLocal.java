/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.ReviewOfStudent;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewReviewOfStudentException;
import util.exception.InputDataValidationException;
import util.exception.ReviewOfStudentNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Local
public interface ReviewOfStudentSessionBeanLocal {

    public ReviewOfStudent createNewStartUp(ReviewOfStudent review) throws CreateNewReviewOfStudentException, InputDataValidationException;

    public List<ReviewOfStudent> retrieveAllReviewOfStartUp();

    public ReviewOfStudent retrieveReviewOfStudentByReviewOfStudentId(Long reviewOfStudentId) throws ReviewOfStudentNotFoundException;

    public void updateReviewOfStartUp(ReviewOfStudent review);
    
}
