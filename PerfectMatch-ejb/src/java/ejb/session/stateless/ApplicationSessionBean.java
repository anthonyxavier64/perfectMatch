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
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.ApplicationNotFoundException;
import util.exception.CreateNewApplicationException;
import util.exception.InputDataValidationException;
import util.exception.PostingNotFoundException;
import util.exception.StudentNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Stateless
public class ApplicationSessionBean implements ApplicationSessionBeanLocal {

    @PersistenceContext(unitName = "PerfectMatch-ejbPU")
    private EntityManager em;
    
    @EJB
    private PostingSessionBeanLocal postingSessionBeanLocal;

    @EJB
    private StudentSessionBeanLocal studentSessionBeanLocal;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public ApplicationSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public Application createNewApplication(Application app, Long studentId, Long postingId) throws InputDataValidationException, CreateNewApplicationException {
        
        Set<ConstraintViolation<Application>> constraintViolations = validator.validate(app);

        if (!constraintViolations.isEmpty()) {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
        
        try {
            Student student = studentSessionBeanLocal.retrieveStudentByStudentId(studentId);
            Posting posting = postingSessionBeanLocal.retrievePostingByPostingId(postingId);
            
            app.setStudent(student);
            app.setPosting(posting);
            em.persist(app);
            em.flush();

            student.getApplications().add(app);
            posting.getApplications().add(app);

            return app;
            
        } catch (StudentNotFoundException | PostingNotFoundException ex){
            throw new CreateNewApplicationException(ex.getMessage());
        }
    }
    
    @Override
    public List<Application> retrieveAllApplication() {
        Query q = em.createQuery("SELECT a FROM Application a");
        return q.getResultList();
    }
    
    @Override
    public Application retrieveApplicationByApplicationId(Long applicationId) throws ApplicationNotFoundException {
        Application app = em.find(Application.class, applicationId);
        if (app == null) {
            throw new ApplicationNotFoundException("Application ID " + applicationId + " does not exist");
        }
        return app;
    }
    
    @Override
    public void updateApplication(Application application) {
        em.merge(application);
    }
    
    @Override
    public Student getStudentFromApplicationId(Long appId) throws ApplicationNotFoundException {
        Application app = retrieveApplicationByApplicationId(appId);
        return app.getStudent();
    }
    
    @Override
    public Posting getPostingFromApplicationId(Long appId) throws ApplicationNotFoundException {
        Application app = retrieveApplicationByApplicationId(appId);
        return app.getPosting();
    }
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<Application>> constraintViolations) {
        String msg = "Input data validation error!:";

        for (ConstraintViolation constraintViolation : constraintViolations) {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }

        return msg;
    }
    
}
