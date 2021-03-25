/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Payment;
import entity.Project;
import entity.StartUp;
import entity.Student;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.CreateNewPaymentException;
import util.exception.InputDataValidationException;
import util.exception.PaymentNotFoundException;
import util.exception.ProjectNotFoundException;
import util.exception.StartUpNotFoundException;
import util.exception.StudentNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Stateless
public class PaymentSessionBean implements PaymentSessionBeanLocal {

    @PersistenceContext(unitName = "PerfectMatch-ejbPU")
    private EntityManager em;

    @EJB
    private PostingSessionBeanLocal postingSessionBeanLocal;

    @EJB
    private StartUpSessionBeanLocal startupSessionBeanLocal;

    @EJB
    private StudentSessionBeanLocal studentSessionBeanLocal;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public PaymentSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public Payment createNewPayment(Payment pay, Long projectId, Long startupId, Long studentId) throws InputDataValidationException, CreateNewPaymentException {

        Set<ConstraintViolation<Payment>> constraintViolations = validator.validate(pay);

        if (!constraintViolations.isEmpty()) {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }

        try {
            Student student = studentSessionBeanLocal.retrieveStudentByStudentId(studentId);
            Project project = postingSessionBeanLocal.retrieveProjectByProjectId(projectId);
            StartUp startup = startupSessionBeanLocal.retrieveStartUpByStartUpId(startupId);

            pay.setStudent(student);
            pay.setProject(project);
            pay.setStartup(startup);

            em.persist(pay);
            em.flush();

            student.getPayments().add(pay);
            startup.getPayments().add(pay);

            return pay;

        } catch (StudentNotFoundException | ProjectNotFoundException | StartUpNotFoundException ex) {
            throw new CreateNewPaymentException(ex.getMessage());
        }

    }

    @Override
    public List<Payment> retrieveAllPayment() {
        Query q = em.createQuery("SELECT p FROM Payment p");
        return q.getResultList();
    }

    @Override
    public Payment retrievePaymentByPaymentId(Long paymentId) throws PaymentNotFoundException {
        Payment pay = em.find(Payment.class, paymentId);
        if (pay == null) {
            throw new PaymentNotFoundException("Payment ID " + paymentId + " does not exist");
        }
        return pay;
    }
    
    @Override
    public StartUp retrieveStartupByPaymentId(Long paymentId) throws PaymentNotFoundException {
        Payment payment = retrievePaymentByPaymentId(paymentId);
        return payment.getStartup();
    }
    
    @Override
    public Project retrieveProjectByPaymentId(Long paymentId) throws PaymentNotFoundException {
        Payment payment = retrievePaymentByPaymentId(paymentId);
        return payment.getProject();
    }
    
    @Override
    public Student retrieveStudentByPaymentId(Long paymentId) throws PaymentNotFoundException {
        Payment payment = retrievePaymentByPaymentId(paymentId);
        return payment.getStudent();
    }

    @Override
    public void updatePayment(Payment payment) {
        em.merge(payment);
    }

    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<Payment>> constraintViolations) {
        String msg = "Input data validation error!:";

        for (ConstraintViolation constraintViolation : constraintViolations) {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }

        return msg;
    }
}
