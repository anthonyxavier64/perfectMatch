/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Job;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewPostingException;

/**
 *
 * @author user
 */
@Local
public interface JobSessionBeanLocal {
    public long createNewJob(Job job, Long startupId) throws CreateNewPostingException;
    
    public List<Job> retrieveAllJobs();
}
