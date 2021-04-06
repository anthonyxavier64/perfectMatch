/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import ejb.session.stateless.JobSessionBeanLocal;
import ejb.session.stateless.OfferSessionBeanLocal;
import ejb.session.stateless.PostingSessionBeanLocal;
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

import ejb.session.stateless.StudentSessionBeanLocal;
import entity.Job;
import entity.Offer;
import entity.Project;
import entity.Student;
import enumeration.OfferStatus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.exception.CreateNewOfferException;
import util.exception.CreateNewPostingException;
import util.exception.CreateNewStudentException;
import util.exception.OfferNotFoundException;
import util.exception.PostingNotFoundException;
import util.exception.StudentNotFoundException;

/**
 *
 * @author Phire
 */
@Singleton
@LocalBean
@Startup
public class DataInitSessionBean {

    @EJB
    private JobSessionBeanLocal jobSessionBean;

    @EJB
    private PostingSessionBeanLocal postingSessionBean;

    @EJB
    private StartUpSessionBeanLocal startUpSessionBean;

    @EJB
    private ProjectSessionBeanLocal projectSessionBean;

    @EJB
    private OfferSessionBeanLocal offerSessionBean;

    @EJB
    private StudentSessionBeanLocal studentSessionBean;

    @PersistenceContext(unitName = "PerfectMatch-ejbPU")
    private EntityManager em;

    @PostConstruct
    public void postConstruct() {
        try {

            startUpSessionBean.retrieveStartUpByStartUpId(1l);
            studentSessionBean.retrieveStudentByStudentId(1l);
            postingSessionBean.retrievePostingByPostingId(1l);
            offerSessionBean.retrieveOfferByOfferId(1l);

        } catch (StartUpNotFoundException | StudentNotFoundException | OfferNotFoundException | PostingNotFoundException ex) {
            initStartUps();
            initStudents();
//            initJobs();
//            initProjects();
            initOffers();
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

            String[] requiredSkillsOne = new String[]{"Java", "Javascript", "SQL", "Web services"};
            String[] requiredSkillsTwo = new String[]{"Marketing", "Communication", "Critical thinking", "Flexible"};

            try {
                projectSessionBean.createNewProject(new Project("Project 1", "This is project 1", 2000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.SOFTWARE_DEV, requiredSkillsOne, "Software Development", false), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 2", "This is project 2", 2000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-11"), Industry.SOFTWARE_DEV, requiredSkillsOne, "Software Development", false), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 3", "This is project 3", 3000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-11"), Industry.SOFTWARE_DEV, requiredSkillsOne, "Software Development", false), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 4", "This is project 4", 3000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.MARKETING, requiredSkillsTwo, "Marketing", false), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 5", "This is project 5", 2000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.MARKETING, requiredSkillsOne, "Marketing", false), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 6", "This is project 6", 4000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.MARKETING, requiredSkillsOne, "Marketing", false), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 7", "This is project 7", 2000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.ENGINEERING, requiredSkillsOne, "Software Development", false), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 8", "This is project 8", 2000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.ENGINEERING, requiredSkillsOne, "Software Development", false), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 9", "This is project 9", 5000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.ENGINEERING, requiredSkillsOne, "Software Development", false), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 10", "This is project 10", 5000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.ENGINEERING, requiredSkillsOne, "Software Development", false), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("**************** DataInitSessionBean.initProjects");

            jobSessionBean.createNewJob(
                    new Job("Job 1", "Job 1", 2000.00, new Date(), new Date(), Industry.FINANCE, new String[]{"Eat", "Study", "Code"}), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 2", "Job 2", 2000.00, new Date(), new Date(), Industry.FINANCE, new String[]{"Eat", "Study", "Code"}), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 3", "Job 3", 2000.00, new Date(), new Date(), Industry.FINANCE, new String[]{"Eat", "Study", "Code"}), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 4", "Job 4", 2000.00, new Date(), new Date(), Industry.EDUCATION, new String[]{"Eat", "Study", "Code"}), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 5", "Job 5", 2000.00, new Date(), new Date(), Industry.EDUCATION, new String[]{"Eat", "Study", "Code"}), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 6", "Job 6", 2000.00, new Date(), new Date(), Industry.EDUCATION, new String[]{"Eat", "Study", "Code"}), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 7", "Job 7", 2000.00, new Date(), new Date(), Industry.ENGINEERING, new String[]{"Eat", "Study", "Code"}), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 8", "Job 8", 2000.00, new Date(), new Date(), Industry.ENGINEERING, new String[]{"Eat", "Study", "Code"}), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 9", "Job 9", 2000.00, new Date(), new Date(), Industry.ENGINEERING, new String[]{"Eat", "Study", "Code"}), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 10", "Job 10", 2000.00, new Date(), new Date(), Industry.ENGINEERING, new String[]{"Eat", "Study", "Code"}), 1l);
            System.out.println("**************** DataInitSessionBean.initJobs");
        } catch (CreateNewStartUpException | InputDataValidationException | CreateNewPostingException ex) {
            System.out.println("There was an error in initialising the StartUps: "
                    + ex.getMessage());
        }
    }

    private void initStudents() {
        try {
            studentSessionBean
                    .createNewStudent(
                            new Student(
                                    "Anthony",
                                    "NUS",
                                    "forTesting",
                                    "anthony@gmail.com",
                                    "password",
                                    "testCourse",
                                    2,
                                    new Date(2023),
                                    new String[]{"Eat", "Study", "Code"},
                                    new Date[]{new Date(), new Date()}));
            studentSessionBean
                    .createNewStudent(
                            new Student(
                                    "Taha",
                                    "NUS",
                                    "forTesting",
                                    "taha@gmail.com",
                                    "password",
                                    "testCourse",
                                    2,
                                    new Date(2023),
                                    new String[]{"Eat", "Study", "Code"},
                                    new Date[]{new Date(), new Date()}));
            studentSessionBean
                    .createNewStudent(
                            new Student(
                                    "Pei Zhen",
                                    "NUS",
                                    "forTesting",
                                    "peizhen@gmail.com",
                                    "password",
                                    "testCourse",
                                    2,
                                    new Date(2023),
                                    new String[]{"Eat", "Study", "Code"},
                                    new Date[]{new Date(), new Date()}));
            studentSessionBean
                    .createNewStudent(
                            new Student(
                                    "Marcus",
                                    "NUS",
                                    "forTesting",
                                    "marcus@gmail.com",
                                    "password",
                                    "testCourse",
                                    2,
                                    new Date(2023),
                                    new String[]{"Eat", "Study", "Code"},
                                    new Date[]{new Date(), new Date()}));
            System.out.println("**************** DataInitSessionBean.initStudents");
        } catch (CreateNewStudentException | InputDataValidationException ex) {
            System.out.println("There was an error in initialising the Students: "
                    + ex.getMessage());
        }
    }

    private void initOffers() {
        try {
            offerSessionBean
                    .createNewOffer(
                            new Offer(
                                    "TestOffer1",
                                    OfferStatus.ACCEPTED),
                            (long) 1, (long) 1);
            offerSessionBean
                    .createNewOffer(
                            new Offer(
                                    "TestOffer2",
                                    OfferStatus.PENDING),
                            (long) 2, (long) 2);
            offerSessionBean
                    .createNewOffer(
                            new Offer(
                                    "TestOffer3",
                                    OfferStatus.REJECTED),
                            (long) 3, (long) 3);
            offerSessionBean
                    .createNewOffer(
                            new Offer(
                                    "TestOffer4",
                                    OfferStatus.PENDING),
                            (long) 4, (long) 4);
            System.out.println("**************** DataInitSessionBean.initOffers");
        } catch (CreateNewOfferException | InputDataValidationException ex) {
            System.out.println("There was an error in initialising the Offers: "
                    + ex.getMessage());
        }
    }

//    private void initProjects() {
//        try {
//            postingSessionBean.
//                    createNewPosting(
//                            new Project(
//                                    "Project1",
//                                    "TestProject1",
//                                    1000.00,
//                                    Industry.EDUCATION,
//                                    false
//                            ), 
//                            (long) 1);
//            postingSessionBean.
//                    createNewPosting(
//                            new Project(
//                                    "Project2",
//                                    "TestProject2",
//                                    1500.00,
//                                    Industry.FINANCE,
//                                    false
//                            ), 
//                            (long) 2);
//            postingSessionBean.
//                    createNewPosting(
//                            new Project(
//                                    "Project3",
//                                    "TestProject3",
//                                    800.00,
//                                    Industry.ENGINEERING,
//                                    false
//                            ), 
//                            (long) 3);
//            postingSessionBean.
//                    createNewPosting(
//                            new Project(
//                                    "Project4",
//                                    "TestProject4",
//                                    1800.00,
//                                    Industry.SOFTWARE_DEV,
//                                    false
//                            ), 
//                            (long) 4);
//            System.out.println("**************** DataInitSessionBean.initProjects");
//        } catch (CreateNewPostingException | InputDataValidationException ex) {
//            System.out.println("There was an error in initialising the Projects: "
//                    + ex.getMessage());
//        }
//    }
//    private void initJobs() {
//        try {
//            postingSessionBean.
//                    createNewPosting(
//                            new Job(
//                                    "Job1",
//                                    "TestJob1",
//                                    1000.00,
//                                    new Date(2021, 5, 10),
//                                    new Date(2021, 7, 31),
//                                    Industry.EDUCATION,
//                                    new String[]{"Eat", "Study", "Code"}
//                            ), 
//                            (long) 1);
//            postingSessionBean.
//                    createNewPosting(
//                            new Job(
//                                    "Job2",
//                                    "TestJob2",
//                                    1500.00,
//                                    new Date(2021, 5, 10),
//                                    new Date(2021, 7, 31),
//                                    Industry.MARKETING,
//                                    new String[]{"Eat", "Study", "Code"}
//                            ), 
//                            (long) 2);
//            postingSessionBean.
//                    createNewPosting(
//                            new Job(
//                                    "Job3",
//                                    "TestJob3",
//                                    800.00,
//                                    new Date(2021, 5, 10),
//                                    new Date(2021, 7, 31),
//                                    Industry.ENGINEERING,
//                                    new String[]{"Eat", "Study", "Code"}
//                            ), 
//                            (long) 3);
//            postingSessionBean.
//                    createNewPosting(
//                            new Job(
//                                    "Job4",
//                                    "TestJob4",
//                                    2000.00,
//                                    new Date(2021, 5, 10),
//                                    new Date(2021, 7, 31),
//                                    Industry.SOFTWARE_DEV,
//                                    new String[]{"Eat", "Study", "Code"}
//                            ), 
//                            (long) 4);
//            System.out.println("**************** DataInitSessionBean.initJobs");
//        } catch (CreateNewPostingException | InputDataValidationException ex) {
//            System.out.println("There was an error in initialising the Jobs: "
//                    + ex.getMessage());
//        }
//    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void persist(Object object) {
        em.persist(object);
    }
}
