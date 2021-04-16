/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import ejb.session.stateless.ApplicationSessionBeanLocal;
import ejb.session.stateless.JobSessionBeanLocal;
import ejb.session.stateless.OfferSessionBeanLocal;
import ejb.session.stateless.PaymentSessionBeanLocal;
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
import entity.Application;
import entity.Job;
import entity.Offer;
import entity.Payment;
import entity.Project;
import entity.Student;
import enumeration.ApplicationStatus;
import enumeration.OfferStatus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.exception.ApplicationNotFoundException;
import util.exception.CreateNewApplicationException;
import util.exception.CreateNewOfferException;
import util.exception.CreateNewPaymentException;
import util.exception.CreateNewPostingException;
import util.exception.CreateNewStudentException;
import util.exception.OfferNotFoundException;
import util.exception.PaymentNotFoundException;
import util.exception.RepeatedApplicationException;
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
    private ApplicationSessionBeanLocal applicationSessionBean;

    @EJB
    private PaymentSessionBeanLocal paymentSessionBean;

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

    String[] requiredSkillsOneArray = new String[]{"Java", "Javascript", "SQL", "Web services"};
    List<String> requiredSkillsOne = Arrays.asList(requiredSkillsOneArray);

    String[] requiredSkillsTwoArray = new String[]{"Marketing", "Communication", "Critical thinking", "Flexible"};
    List<String> requiredSkillsTwo = Arrays.asList(requiredSkillsTwoArray);

    List<String> skills = Arrays.asList(new String[]{"Eat", "Study", "Code"});

    @PostConstruct
    public void postConstruct() {
        try {
            startUpSessionBean.retrieveStartUpByStartUpId(1l);
        } catch (StartUpNotFoundException ex) {
            initStartUps();
        }

        try {
            studentSessionBean.retrieveStudentByStudentId(1l);
        } catch (StudentNotFoundException ex) {
            initStudents();
        }

        try {
            offerSessionBean.retrieveOfferByOfferId(1l);
        } catch (OfferNotFoundException ex) {
            initOffers();
        }

        try {
            applicationSessionBean.retrieveApplicationByApplicationId(1l);
        } catch (ApplicationNotFoundException ex) {
            initApplications();
        }

        try {
            paymentSessionBean.retrievePaymentByPaymentId(1l);
        } catch (PaymentNotFoundException ex) {
            //initPayments();
        }
        
        System.out.println("**************** DataInitSessionBean.postConstruct");
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
                                    Industry.EDUCATION,
                                    StartUpLocation.CENTRAL));
            startUpSessionBean
                    .createNewStartUp(
                            new StartUp(
                                    "0001",
                                    "StartUp01",
                                    "DataInit Startup",
                                    "StartUp01@gmail.com",
                                    "password",
                                    Industry.ENGINEERING,
                                    StartUpLocation.SOUTH));
            startUpSessionBean
                    .createNewStartUp(
                            new StartUp(
                                    "0002",
                                    "StartUp02",
                                    "DataInit Startup",
                                    "StartUp02@gmail.com",
                                    "password",
                                    Industry.FINANCE,
                                    StartUpLocation.EAST));
            startUpSessionBean
                    .createNewStartUp(
                            new StartUp(
                                    "0003",
                                    "StartUp03",
                                    "DataInit Startup",
                                    "StartUp03@gmail.com",
                                    "password",
                                    Industry.MARKETING,
                                    StartUpLocation.CENTRAL));
            startUpSessionBean
                    .createNewStartUp(
                            new StartUp(
                                    "0004",
                                    "StartUp04",
                                    "DataInit Startup",
                                    "StartUp04@gmail.com",
                                    "password",
                                    Industry.EDUCATION,
                                    StartUpLocation.NORTH));
            startUpSessionBean
                    .createNewStartUp(
                            new StartUp(
                                    "0005",
                                    "StartUp05",
                                    "DataInit Startup",
                                    "StartUp05@gmail.com",
                                    "password",
                                    Industry.ENGINEERING,
                                    StartUpLocation.EAST));
            startUpSessionBean
                    .createNewStartUp(
                            new StartUp(
                                    "0006",
                                    "StartUp06",
                                    "DataInit Startup",
                                    "StartUp06@gmail.com",
                                    "password",
                                    Industry.FINANCE,
                                    StartUpLocation.SOUTH));
            startUpSessionBean
                    .createNewStartUp(
                            new StartUp(
                                    "0007",
                                    "StartUp07",
                                    "DataInit Startup",
                                    "StartUp07@gmail.com",
                                    "password",
                                    Industry.MARKETING,
                                    StartUpLocation.WEST));
            System.out.println("**************** DataInitSessionBean.initStartUps");

            try {
                Project project1 = new Project("Project 1", "This is project 1", 2000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.SOFTWARE_DEV, requiredSkillsOne, "Software Development");
                project1.setIsComplete(true);
                projectSessionBean.createNewProject(project1, 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 2", "This is project 2", 2000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-11"), Industry.SOFTWARE_DEV, requiredSkillsOne, "Software Development"), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 3", "This is project 3", 3000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-11"), Industry.SOFTWARE_DEV, requiredSkillsOne, "Software Development"), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 4", "This is project 4", 3000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.MARKETING, requiredSkillsTwo, "Marketing"), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 5", "This is project 5", 2000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.MARKETING, requiredSkillsOne, "Marketing"), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 6", "This is project 6", 4000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.MARKETING, requiredSkillsOne, "Marketing"), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 7", "This is project 7", 2000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.ENGINEERING, requiredSkillsOne, "Software Development"), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 8", "This is project 8", 2000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.ENGINEERING, requiredSkillsOne, "Software Development"), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 9", "This is project 9", 5000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.ENGINEERING, requiredSkillsOne, "Software Development"), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                projectSessionBean.createNewProject(new Project("Project 10", "This is project 10", 5000.00, new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11"), Industry.ENGINEERING, requiredSkillsOne, "Software Development"), 1l);
            } catch (ParseException ex) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("**************** DataInitSessionBean.initProjects");

            jobSessionBean.createNewJob(
                    new Job("Job 1", "Job 1", 2000.00, new Date(), new Date(), Industry.FINANCE, skills), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 2", "Job 2", 2000.00, new Date(), new Date(), Industry.FINANCE, skills), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 3", "Job 3", 2000.00, new Date(), new Date(), Industry.FINANCE, skills), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 4", "Job 4", 2000.00, new Date(), new Date(), Industry.EDUCATION, skills), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 5", "Job 5", 2000.00, new Date(), new Date(), Industry.EDUCATION, skills), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 6", "Job 6", 2000.00, new Date(), new Date(), Industry.EDUCATION, skills), 1l);
            jobSessionBean.createNewJob(
                    new Job("Job 7", "Job 7", 2000.00, new Date(), new Date(), Industry.ENGINEERING, skills), 5l);
            jobSessionBean.createNewJob(
                    new Job("Job 8", "Job 8", 2000.00, new Date(), new Date(), Industry.ENGINEERING, skills), 6l);
            jobSessionBean.createNewJob(
                    new Job("Job 9", "Job 9", 2000.00, new Date(), new Date(), Industry.ENGINEERING, skills), 7l);
            jobSessionBean.createNewJob(
                    new Job("Job 10", "Job 10", 2000.00, new Date(), new Date(), Industry.ENGINEERING, skills), 8l);

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
                                    new SimpleDateFormat("yyyy").parse("2023"),
                                    skills,
                                    new Date[]{new SimpleDateFormat("yyyy-MM-dd").parse("2021-04-07"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11")}));
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
                                    new SimpleDateFormat("yyyy").parse("2023"),
                                    skills,
                                    new Date[]{new SimpleDateFormat("yyyy-MM-dd").parse("2021-04-07"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11")}));
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
                                    new SimpleDateFormat("yyyy").parse("2023"),
                                    skills,
                                    new Date[]{new SimpleDateFormat("yyyy-MM-dd").parse("2021-04-07"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11")}));
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
                                    new SimpleDateFormat("yyyy").parse("2023"),
                                    skills,
                                    new Date[]{new SimpleDateFormat("yyyy-MM-dd").parse("2021-04-07"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11")}));
            studentSessionBean
                    .createNewStudent(
                            new Student(
                                    "Roger",
                                    "NUS",
                                    "forTesting",
                                    "roger@gmail.com",
                                    "password",
                                    "testCourse",
                                    3,
                                    new SimpleDateFormat("yyyy").parse("2023"),
                                    skills,
                                    new Date[]{new SimpleDateFormat("yyyy-MM-dd").parse("2021-04-07"), new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-11")}));
            System.out.println("**************** DataInitSessionBean.initStudents");
        } catch (ParseException | CreateNewStudentException | InputDataValidationException ex) {
            Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
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
                                    OfferStatus.PENDING),
                            (long) 3, (long) 3);
            offerSessionBean
                    .createNewOffer(
                            new Offer(
                                    "TestOffer5",
                                    OfferStatus.PENDING),
                            (long) 3, (long) 4);
            offerSessionBean
                    .createNewOffer(
                            new Offer(
                                    "TestOffer6",
                                    OfferStatus.PENDING),
                            (long) 3, (long) 5);
            offerSessionBean
                    .createNewOffer(
                            new Offer(
                                    "TestOffer3",
                                    OfferStatus.PENDING),
                            (long) 3, (long) 6);
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

    private void initPayments() {
        try {
            Payment payment1 = new Payment(1000D, "For_Testing", new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-02"));
            Payment payment2 = new Payment(1200D, "For_Testing", new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-15"));
            Payment payment3 = new Payment(2100D, "For_Testing", new SimpleDateFormat("yyyy-MM-dd").parse("2022-11-28"));
            Payment payment4 = new Payment(3600D, "For_Testing", new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-03"));

            paymentSessionBean.createNewPayment(payment1, 1l, 1l, 1l);
            paymentSessionBean.createNewPayment(payment2, 1l, 1l, 2l);
            paymentSessionBean.createNewPayment(payment3, 1l, 1l, 3l);
            paymentSessionBean.createNewPayment(payment4, 1l, 1l, 4l);
        } catch (ParseException
                | InputDataValidationException
                | CreateNewPaymentException ex) {
            System.out.println("There was an error in initialising the Payments: "
                    + ex.getMessage());
        }
    }

    private void initApplications() {
        try {
            Application application1 = new Application(true, ApplicationStatus.PENDING);
            Application application2 = new Application(true, ApplicationStatus.PENDING);
            Application application3 = new Application(true, ApplicationStatus.REJECTED);
            Application application4 = new Application(true, ApplicationStatus.REJECTED);
            Application application5 = new Application(false, ApplicationStatus.REJECTED);

            applicationSessionBean.createNewApplication(application1, 1l, 1l);
            applicationSessionBean.createNewApplication(application2, 2l, 1l);
            applicationSessionBean.createNewApplication(application3, 3l, 1l);
            applicationSessionBean.createNewApplication(application4, 4l, 1l);
            applicationSessionBean.createNewApplication(application5, 5l, 1l);
            System.out.println("**************** DataInitSessionBean.initApplications");
        } catch (CreateNewApplicationException
                | InputDataValidationException
                | RepeatedApplicationException ex) {
            System.out.println("There was an error in initialising the Application entities: "
                    + ex.getMessage());
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void persist(Object object) {
        em.persist(object);
    }
}
