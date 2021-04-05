/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Application;
import entity.Job;
import entity.Offer;
import entity.Posting;
import entity.Project;
import entity.StartUp;
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
import util.exception.CreateNewPostingException;
import util.exception.InputDataValidationException;
import util.exception.JobNotFoundException;
import util.exception.PostingNotFoundException;
import util.exception.ProjectNotFoundException;
import util.exception.StartUpNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Stateless
public class PostingSessionBean implements PostingSessionBeanLocal {

    @PersistenceContext(unitName = "PerfectMatch-ejbPU")
    private EntityManager em;

    @EJB
    private StartUpSessionBeanLocal startupSessionBeanLocal;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public PostingSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public Posting createNewPosting(Posting posting, Long startupId) throws CreateNewPostingException, InputDataValidationException {
        Set<ConstraintViolation<Posting>> constraintViolations = validator.validate(posting);

        if (!constraintViolations.isEmpty()) {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }

        if (posting == null) {
            throw new CreateNewPostingException("Posting information not provided");
        }

        try {
            StartUp startup = startupSessionBeanLocal.retrieveStartUpByStartUpId(startupId);
            posting.setStartup(startup);
            em.persist(posting);

            startup.getPostings().add(posting);

            em.flush();

        } catch (StartUpNotFoundException ex) {
            throw new CreateNewPostingException(ex.getMessage());
        }

        return posting;
    }
    
    @Override
    public List<Posting> retrieveAllPostings() {
        Query q = em.createQuery("SELECT p FROM Posting p");
        List<Posting> results = q.getResultList();
        for (Posting p : results) {
            p.getApplications().size();
            p.getOffers().size();
        }
        return results;
    }
        
    @Override
    public List<Project> retrieveAllProjects() {
        Query q = em.createQuery("SELECT p FROM Project p");
        List<Project> results = q.getResultList();
        for (Project p : results) {
            p.getApplications().size();
            p.getOffers().size();
        }
        return q.getResultList();
    }
    
   @Override
    public List<Job> retrieveAllJobs() {
        Query q = em.createQuery("SELECT j FROM Job j");
        List<Job> results = q.getResultList();
        for (Job j : results) {
            j.getApplications().size();
            j.getOffers().size();
        }
        return q.getResultList();
    }
    
    @Override
    public Posting retrievePostingByPostingId(Long postingId) throws PostingNotFoundException {
        Posting posting = em.find(Posting.class, postingId);
        if (posting == null) {
            throw new PostingNotFoundException("Posting ID " + postingId + " does not exist");
        }
        
        posting.getApplications().size();
        posting.getOffers().size();
        
        return posting;
    }
    
    @Override
    public Project retrieveProjectByProjectId(Long projId) throws ProjectNotFoundException {
        Project proj = em.find(Project.class, projId);
        if (proj == null) {
            throw new ProjectNotFoundException("Project ID " + projId + " does not exist");
        }
        proj.getApplications().size();
        proj.getOffers().size();
        
        return proj;
    }
    
    @Override
    public Job retrieveJobByJobId(Long jobId) throws JobNotFoundException {
        Job job = em.find(Job.class, jobId);
        if (job == null) {
            throw new JobNotFoundException("Job ID " + jobId + " does not exist");
        }
        job.getApplications().size();
        job.getOffers().size();
        
        return job;
    }
    
    @Override
    public List<Application> retrievePostingApplications(Long postingId) {
        Query query = em.createQuery("SELECT a FROM Application WHERE a.postingId = :postingId");
        query.setParameter("postingId", postingId);

        List<Application> applications = query.getResultList();
        return applications;
    }
    
    @Override
    public List<Offer> retrievePostingOffers(Long postingId) {
        Query query = em.createQuery("SELECT o FROM Offer WHERE o.postingId = :postingId");
        query.setParameter("postingId", postingId);

        List<Offer> offers = query.getResultList();
        return offers;
    }
    
    @Override
    public StartUp retrieveStartupFromPostingId(Long postingId) throws PostingNotFoundException {
        Posting post = retrievePostingByPostingId(postingId);
        return post.getStartup();
    }
        
    @Override
    public void updatePosting(Posting posting) {
        em.merge(posting);
    }
    
    @Override
    public void deletePosting(Long postingId) throws PostingNotFoundException
    {
        try {
            Posting postingToRemove = retrievePostingByPostingId(postingId);

            List<Offer> offersAssociated = postingToRemove.getOffers();

            if (offersAssociated != null) {
                for (int i = 0; i < offersAssociated.size(); i++) {
                    offersAssociated.get(i).getStudent().getOffers().remove(offersAssociated.get(i));
                }
            }

            em.remove(postingToRemove);
        }
        catch(NoResultException ex)
        {
            throw new PostingNotFoundException("Posting ID" + postingId + "does not exist!");
        }
        
    }

    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<Posting>> constraintViolations) {
        String msg = "Input data validation error!:";

        for (ConstraintViolation constraintViolation : constraintViolations) {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }

        return msg;
    }
}
