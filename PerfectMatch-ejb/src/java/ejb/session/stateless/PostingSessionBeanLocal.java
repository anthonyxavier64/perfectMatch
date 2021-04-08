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
import javax.ejb.Local;
import util.exception.CreateNewPostingException;
import util.exception.InputDataValidationException;
import util.exception.JobNotFoundException;
import util.exception.PostingNotFoundException;
import util.exception.ProjectNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Local
public interface PostingSessionBeanLocal {

    public Posting createNewPosting(Posting posting, Long startupId) throws CreateNewPostingException, InputDataValidationException;

    public List<Posting> retrieveAllPostings();

    public Posting retrievePostingByPostingId(Long postingId) throws PostingNotFoundException;

    public void updatePosting(Posting posting);

    public List<Project> retrieveAllProjects();

    public List<Job> retrieveAllJobs();

    public Project retrieveProjectByProjectId(Long projId) throws ProjectNotFoundException;

    public Job retrieveJobByJobId(Long jobId) throws JobNotFoundException;

    public List<Application> retrievePostingApplications(Long postingId);

    public List<Offer> retrievePostingOffers(Long postingId);

    public StartUp retrieveStartupFromPostingId(Long postingId) throws PostingNotFoundException;
    
    public void deletePosting(Long postingId) throws PostingNotFoundException;
}
