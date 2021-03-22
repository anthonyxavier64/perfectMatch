/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Application;
import entity.Posting;
import entity.Student;
import java.util.List;
import javax.ejb.Local;
import util.exception.ApplicationNotFoundException;
import util.exception.CreateNewApplicationException;
import util.exception.InputDataValidationException;

/**
 *
 * @author yappeizhen
 */
@Local
public interface ApplicationSessionBeanLocal {

    public Application createNewApplication(Application app, Long studentId, Long postingId) throws InputDataValidationException, CreateNewApplicationException;

    public List<Application> retrieveAllApplication();

    public Application retrieveApplicationByApplicationId(Long applicationId) throws ApplicationNotFoundException;

    public void updateApplication(Application application);

    public Student getStudentFromApplicationId(Long appId) throws ApplicationNotFoundException;

    public Posting getPostingFromApplicationId(Long appId) throws ApplicationNotFoundException;
    
}
