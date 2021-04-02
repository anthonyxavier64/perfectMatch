/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Application;
import entity.Offer;
import entity.Payment;
import entity.StartUp;
import entity.Student;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.StudentNotFoundException;

/**
 *
 * @author Antho
 */
@Stateless
public class StudentSessionBean implements StudentSessionBeanLocal {

    @PersistenceContext(unitName = "PerfectMatch-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public StudentSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Student registerStudentAccount(Student student) {
        em.persist(student);
        em.flush();
        return student;
    }

    @Override
    public Student loginStudent(String email, String password) throws NonUniqueResultException, NoResultException {
        Query query = em.createQuery("SELECT s FROM Student s WHERE s.email = :email and s.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        try {
            Student student = (Student) query.getSingleResult();
            return student;
        } catch (NonUniqueResultException | NoResultException ex) {
            throw ex;
        }
    }

    @Override
    public Student retrieveStudentByStudentId(Long studentId) throws StudentNotFoundException {
        Student student = em.find(Student.class, studentId);
        if (student == null) {
            throw new StudentNotFoundException("Student ID " + studentId + " does not exist");
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        Query query = em.createQuery("SELECT s FROM Student s ORDER BY s.name ASC");
        List<Student> studentEntities = query.getResultList();

        return studentEntities;
    }

    @Override
    public List<Offer> getStudentOffers(Long studentId) {
        Query query = em.createQuery("SELECT o FROM Offer WHERE offer.studentId = :studentId");
        query.setParameter("studentId", studentId);

        List<Offer> offers = query.getResultList();
        return offers;
    }

    @Override
    public List<Application> getStudentApplications(Long studentId) {
        Query query = em.createQuery("SELECT a FROM Application WHERE Application.studentId = :studentId");
        query.setParameter("studentId", studentId);

        List<Application> applications = query.getResultList();
        return applications;
    }

    @Override
    public List<Payment> getStudentPayments(Long studentId) {
        Query query = em.createQuery("SELECT p FROM Payment WHERE Payment.studentId = :studentId");
        query.setParameter("studentId", studentId);

        List<Payment> payments = query.getResultList();
        return payments;
    }

    @Override
    public Student editStudentDetails(Student student) {
        try {
            em.merge(student);
            em.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return student;
    }

    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<StartUp>> constraintViolations) {
        String msg = "Input data validation error!:";

        for (ConstraintViolation constraintViolation : constraintViolations) {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }

        return msg;
    }

}
