/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import ejb.session.stateless.StartUpSessionBeanLocal;
import entity.StartUp;
import enumeration.Industry;
import enumeration.StartUpLocation;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.CreateNewStartupException;
import util.exception.InputDataValidationException;
import util.exception.StartupNotFoundException;

/**
 *
 * @author Phire
 */
@Singleton
@LocalBean
@Startup
public class DataInitSessionBean {

    @PersistenceContext(unitName = "PerfectMatch-ejbPU")
    private EntityManager em;

    @EJB
    private StartUpSessionBeanLocal startUpSessionBean;

    @PostConstruct
    public void postConstruct() {
        try {

            startUpSessionBean.retrieveStartUpByStartUpId(1l);
        } catch (StartupNotFoundException ex) {
            initStartUps();
        } finally {
            System.out.println("**************** DataInitSessionBean.postConstruct");
        }
    }

    private void initStartUps() {
        try {
            startUpSessionBean
                    .createNewStartUp(
                            new StartUp(
                                    "0000",
                                    "manager",
                                    "forTesting",
                                    "manager@gmail.com",
                                    "password",
                                    Industry.FOR_TESTING_ONLY,
                                    StartUpLocation.FOR_TESTING_ONLY));
            startUpSessionBean
                    .createNewStartUp(
                            new StartUp(
                                    "0001",
                                    "StartUp01",
                                    "DataInit Startup",
                                    "StartUp01@gmail.com",
                                    "password",
                                    Industry.FOR_TESTING_ONLY,
                                    StartUpLocation.FOR_TESTING_ONLY));
            startUpSessionBean
                    .createNewStartUp(
                            new StartUp(
                                    "0002",
                                    "StartUp02",
                                    "DataInit Startup",
                                    "StartUp02@gmail.com",
                                    "password",
                                    Industry.FOR_TESTING_ONLY,
                                    StartUpLocation.FOR_TESTING_ONLY));
            startUpSessionBean
                    .createNewStartUp(
                            new StartUp(
                                    "0003",
                                    "StartUp03",
                                    "DataInit Startup",
                                    "StartUp03@gmail.com",
                                    "password",
                                    Industry.FOR_TESTING_ONLY,
                                    StartUpLocation.FOR_TESTING_ONLY));
            System.out.println("**************** DataInitSessionBean.initStartUps");
        } catch (CreateNewStartupException
                | InputDataValidationException ex) {
            System.out.println("There was an error in initialising the StartUps: "
                    + ex.getMessage());
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }
}
