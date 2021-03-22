/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Payment;
import entity.Posting;
import entity.Startup;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import util.exception.CreateNewStartupException;
import util.exception.InputDataValidationException;
import util.exception.StartupNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Local
public interface StartupSessionBeanLocal {

    public List<Startup> retrieveAllStartups();

    public Startup retrieveStartupByStartupId(Long startupId) throws StartupNotFoundException;

    public void updateStartup(Startup startup);

    public Startup createNewStartup(Startup startup) throws CreateNewStartupException, InputDataValidationException;

    public List<Payment> retrieveStartupPayments(Long startupId);

    public List<Posting> retrieveStartupPostings(Long startupId);

    public Startup loginStartup(String email, String password) throws NonUniqueResultException, NoResultException;
    
}