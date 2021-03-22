/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Payment;
import entity.Project;
import entity.Startup;
import entity.Student;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewPaymentException;
import util.exception.InputDataValidationException;
import util.exception.PaymentNotFoundException;

/**
 *
 * @author yappeizhen
 */
@Local
public interface PaymentSessionBeanLocal {

    public Payment createNewPayment(Payment pay, Long projectId, Long startupId, Long studentId) throws InputDataValidationException, CreateNewPaymentException;

    public List<Payment> retrieveAllPayment();

    public Payment retrievePaymentByPaymentId(Long paymentId) throws PaymentNotFoundException;

    public void updatePayment(Payment payment);

    public Startup retrieveStartupByPaymentId(Long paymentId) throws PaymentNotFoundException;

    public Project retrieveProjectByPaymentId(Long paymentId) throws PaymentNotFoundException;

    public Student retrieveStudentByPaymentId(Long paymentId) throws PaymentNotFoundException;
    
}
