/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import ejb.session.stateless.ProjectSessionBeanLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import entity.StartUp;
import enumeration.Industry;
import enumeration.StartUpLocation;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.CreateNewStartUpException;
import util.exception.InputDataValidationException;
import util.exception.StartUpNotFoundException;
import ejb.session.stateless.StartUpSessionBeanLocal;
import entity.Project;
import util.exception.CreateNewPostingException;

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
    @EJB
    private ProjectSessionBeanLocal projectSessionBean;

    @PostConstruct
    public void postConstruct() {
        try {

            startUpSessionBean.retrieveStartUpByStartUpId(1l);
        } catch (StartUpNotFoundException ex) {
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
            projectSessionBean.createNewProject(
                    new Project("Project 1", "Project 1", 2000.00, Industry.FINANCE, false), 1l);
            projectSessionBean.createNewProject(
                    new Project("Project 2", "Project 2", 2000.00, Industry.FINANCE, false), 1l);
            projectSessionBean.createNewProject(
                    new Project("Project 3", "Project 3", 2000.00, Industry.FINANCE, false), 1l);
            projectSessionBean.createNewProject(
                    new Project("Project 4", "Project 4", 2000.00, Industry.EDUCATION, false), 1l);
            projectSessionBean.createNewProject(
                    new Project("Project 5", "Project 5", 2000.00, Industry.EDUCATION, false), 1l);
            projectSessionBean.createNewProject(
                    new Project("Project 6", "Project 6", 2000.00, Industry.EDUCATION, false), 1l);
            projectSessionBean.createNewProject(
                    new Project("Project 7", "Project 7", 2000.00, Industry.ENGINEERING, false), 1l);
            projectSessionBean.createNewProject(
                    new Project("Project 8", "Project 8", 2000.00, Industry.ENGINEERING, false), 1l);
            projectSessionBean.createNewProject(
                    new Project("Project 9", "Project 9", 2000.00, Industry.ENGINEERING, false), 1l);
            projectSessionBean.createNewProject(
                    new Project("Project 10", "Project 10", 2000.00, Industry.ENGINEERING, false), 1l);
        } catch (CreateNewStartUpException | InputDataValidationException | CreateNewPostingException ex) {
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
