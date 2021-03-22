/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Job;
import entity.Payment;
import entity.Project;
import entity.Startup;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Antho
 */
@Stateless
public class StartupSessionBean implements StartupSessionBeanLocal {

    @PersistenceContext(unitName = "PerfectMatch-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Startup registerStartupAccount(Startup startup) {
        em.persist(startup);
        em.flush();
        return startup;
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
    public List<Job> getJobPostings(Long startupId) {
        Query query = em.createQuery("SELECT j FROM Job j WHERE j.startupId = :startupId");
        query.setParameter("startupId", startupId);

        List<Job> jobPostings = query.getResultList();
        return jobPostings;
    }

    @Override
    public List<Project> getProjectPostings(Long startupId) {
        Query query = em.createQuery("SELECT p FROM Posting p WHERE p.startupId = :startupId");
        query.setParameter("startupId", startupId);

        List<Project> projectPostings = query.getResultList();
        return projectPostings;
    }

    @Override
    public List<Payment> getStartupPayments(Long startupId) {
        Query query = em.createQuery("SELECT p FROM Payment p WHERE p.startupId = :startupId");
        query.setParameter("startupId", startupId);

        List<Payment> payments = query.getResultList();
        return payments;
    }

}
