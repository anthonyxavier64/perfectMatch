/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Payment;
import entity.Posting;
import util.exception.CreateNewStartupException;
import entity.Startup;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.InputDataValidationException;
import util.exception.StartupNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Stateless
public class StartupSessionBean implements StartupSessionBeanLocal {

    @PersistenceContext(unitName = "PerfectMatch-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public StartupSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public Startup createNewStartup(Startup startup) throws CreateNewStartupException, InputDataValidationException {
        Set<ConstraintViolation<Startup>> constraintViolations = validator.validate(startup);
        
        if (!constraintViolations.isEmpty()) {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }

        if (startup != null) {
            em.persist(startup);
            em.flush();
            return startup;
        } else {
            throw new CreateNewStartupException("Startup information not provided");
        }
    }
    
    @Override
    public Startup loginStartup(String email, String password) throws NonUniqueResultException, NoResultException {
        Query query = em.createQuery("SELECT s FROM Startup s WHERE s.email = :email and s.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        try {
            Startup startup = (Startup) query.getSingleResult();
            return startup;
        } catch (NonUniqueResultException | NoResultException ex) {
            throw ex;
        }
    }

    @Override
    public List<Startup> retrieveAllStartups() {
        Query q = em.createQuery("SELECT s FROM Startup s");
        List<Startup> results = q.getResultList();
        for (Startup s : results) {
            s.getPostings().size();
            s.getPayments().size();
        }
        return results;
    }

    @Override
    public Startup retrieveStartupByStartupId(Long startupId) throws StartupNotFoundException {
        Startup startup = em.find(Startup.class, startupId);
        if (startup == null) {
            throw new StartupNotFoundException("Startup ID " + startupId + " does not exist");
        }
        startup.getPostings().size();
        startup.getPayments().size();
        return startup;
    }
    
    @Override
    public void updateStartup(Startup startup) {
        em.merge(startup);
    }
    
    @Override
    public List<Posting> retrieveStartupPostings(Long startupId) {
        Query query = em.createQuery("SELECT p FROM Posting WHERE p.startupId = :startupId");
        query.setParameter("startupId", startupId);

        List<Posting> postings = query.getResultList();
        return postings;
    }
    
    @Override
    public List<Payment> retrieveStartupPayments(Long startupId) {
        Query query = em.createQuery("SELECT p FROM Payments WHERE p.startupId = :startupId");
        query.setParameter("startupId", startupId);

        List<Payment> payments = query.getResultList();
        return payments;
    }
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<Startup>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }
}
