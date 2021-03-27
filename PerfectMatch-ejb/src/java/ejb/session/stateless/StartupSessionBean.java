/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Payment;
import entity.Posting;
import util.exception.CreateNewStartUpException;
import entity.StartUp;
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
import util.exception.StartUpNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Stateless
public class StartUpSessionBean implements StartUpSessionBeanLocal {

    @PersistenceContext(unitName = "PerfectMatch-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public StartUpSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public StartUp createNewStartUp(StartUp startup) throws CreateNewStartUpException, InputDataValidationException {
        Set<ConstraintViolation<StartUp>> constraintViolations = validator.validate(startup);

        if (!constraintViolations.isEmpty()) {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }

        if (startup != null) {
            em.persist(startup);
            em.flush();
            return startup;
        } else {
            throw new CreateNewStartUpException("StartUp information not provided");
        }
    }

    @Override
    public StartUp loginStartUp(String email, String password) throws NonUniqueResultException, NoResultException {
        Query query = em.createQuery("SELECT s FROM StartUp s WHERE s.email = :email and s.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        try {
            StartUp startup = (StartUp) query.getSingleResult();
            return startup;
        } catch (NonUniqueResultException | NoResultException ex) {
            throw ex;
        }
    }

    @Override
    public List<StartUp> retrieveAllStartUps() {
        Query q = em.createQuery("SELECT s FROM StartUp s");
        List<StartUp> results = q.getResultList();
        for (StartUp s : results) {
            s.getPostings().size();
            s.getPayments().size();
        }
        return results;
    }

    @Override
    public StartUp retrieveStartUpByStartUpId(Long startupId) throws StartUpNotFoundException {
        StartUp startup = em.find(StartUp.class, startupId);
        if (startup == null) {
            throw new StartUpNotFoundException("StartUp ID " + startupId + " does not exist");
        }
        startup.getPostings().size();
        startup.getPayments().size();
        return startup;
    }
    
    @Override
    public StartUp retrieveStartUpByEmail(String email) throws StartUpNotFoundException {
        StartUp startup = em.find(StartUp.class, email);
        if (startup == null) {
            throw new StartUpNotFoundException("StartUp Email " + email + " does not exist");
        }
        startup.getPostings().size();
        startup.getPayments().size();
        return startup;
    }

    @Override
    public void updateStartUp(StartUp startup) {
        em.merge(startup);
    }

    @Override
    public List<Posting> retrieveStartUpPostings(Long startupId) {
        Query query = em.createQuery("SELECT p FROM Posting WHERE p.startupId = :startupId");
        query.setParameter("startupId", startupId);

        List<Posting> postings = query.getResultList();
        return postings;
    }

    @Override
    public List<Payment> retrieveStartUpPayments(Long startupId) {
        Query query = em.createQuery("SELECT p FROM Payments WHERE p.startupId = :startupId");
        query.setParameter("startupId", startupId);

        List<Payment> payments = query.getResultList();
        return payments;
    }

    @Override
    public void deleteAllStartUps() {
        Query q = em.createQuery("SELECT s FROM StartUp s");
        List<StartUp> results = q.getResultList();
        for (StartUp s : results) {
            em.remove(s);
        }
    }
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<StartUp>> constraintViolations) {
        String msg = "Input data validation error!:";

        for (ConstraintViolation constraintViolation : constraintViolations) {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }

        return msg;
    }

}
