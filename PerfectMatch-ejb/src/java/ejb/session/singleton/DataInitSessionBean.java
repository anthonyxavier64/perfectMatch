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
import entity.Student;
import java.util.Date;
import util.exception.CreateNewStudentException;
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
    private StudentSessionBeanLocal studentSessionBean;

    @PersistenceContext(unitName = "PerfectMatch-ejbPU")
    private EntityManager em;

    @EJB
    private StartUpSessionBeanLocal startUpSessionBean;

    @PostConstruct
    public void postConstruct() {
        try {

            startUpSessionBean.retrieveStartUpByStartUpId(1l);
            studentSessionBean.retrieveStudentByStudentId(1l);
        } catch (StartUpNotFoundException | StudentNotFoundException ex) {
            initStartUps();
            initStudents();
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
        } catch (CreateNewStartUpException | InputDataValidationException ex) {
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
                                    new Date(2023, 5, 10),
                                    new String[]{"Eat", "Study", "Code"},
                                    new Date[]{new Date(2021, 5, 10), new Date(2021, 7, 31)}));
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
                                    new Date(2023, 5, 10),
                                    new String[]{"Eat", "Study", "Code"},
                                    new Date[]{new Date(2021, 5, 10), new Date(2021, 7, 31)}));
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
                                    new Date(2023, 5, 10),
                                    new String[]{"Eat", "Study", "Code"},
                                    new Date[]{new Date(2021, 5, 10), new Date(2021, 7, 31)}));
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
                                    new Date(2023, 5, 10),
                                    new String[]{"Eat", "Study", "Code"},
                                    new Date[]{new Date(2021, 5, 10), new Date(2021, 7, 31)}));
            System.out.println("**************** DataInitSessionBean.initStudents");
        } catch (CreateNewStudentException | InputDataValidationException ex) {
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
