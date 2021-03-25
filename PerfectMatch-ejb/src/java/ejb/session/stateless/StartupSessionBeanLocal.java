/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Payment;
import entity.Posting;
import entity.StartUp;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import util.exception.CreateNewStartUpException;
import util.exception.InputDataValidationException;
import util.exception.StartUpNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Local
public interface StartUpSessionBeanLocal {

    public List<StartUp> retrieveAllStartUps();

    public StartUp retrieveStartUpByStartUpId(Long startupId) throws StartUpNotFoundException;

    public void updateStartUp(StartUp startup);

    public StartUp createNewStartUp(StartUp startup) throws CreateNewStartUpException, InputDataValidationException;

    public List<Payment> retrieveStartUpPayments(Long startupId);

    public List<Posting> retrieveStartUpPostings(Long startupId);

    public StartUp loginStartUp(String email, String password) throws NonUniqueResultException, NoResultException;

    public void deleteAllStartUps();
    
}