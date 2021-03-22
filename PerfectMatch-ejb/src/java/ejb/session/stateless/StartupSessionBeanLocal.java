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
import javax.ejb.Local;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author Antho
 */
@Local
public interface StartupSessionBeanLocal {

    Startup registerStartupAccount(Startup startup);

    Startup loginStartup(String email, String password) throws NonUniqueResultException, NoResultException;

    List<Job> getJobPostings(Long startupId);

    List<Project> getProjectPostings(Long startupId);;

    List<Payment> getStartupPayments(Long startupId);

}
