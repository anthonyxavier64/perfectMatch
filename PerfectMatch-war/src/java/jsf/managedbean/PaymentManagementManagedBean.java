/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.PaymentSessionBeanLocal;
import ejb.session.stateless.ProjectSessionBeanLocal;
import ejb.session.stateless.StudentSessionBeanLocal;
import entity.Payment;
import entity.Project;
import entity.StartUp;
import entity.Student;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import util.exception.CreateNewPaymentException;
import util.exception.CreateNewStartUpException;
import util.exception.InputDataValidationException;

/**
 *
 * @author Phire
 */
@Named(value = "paymentManagementManagedBean")
@SessionScoped
public class PaymentManagementManagedBean implements Serializable {

    @EJB
    private ProjectSessionBeanLocal projectSessionBean;

    @EJB
    private StudentSessionBeanLocal studentSessionBean;

    @EJB
    private PaymentSessionBeanLocal paymentSessionBean;

    private Student studentToPay;
    private Project projectToPay;
    private Payment newPayment;

    /**
     * Creates a new instance of PaymentManagementManagedBean
     */
    public PaymentManagementManagedBean() {
        newPayment = new Payment();
    }

    public void createNewStartUp(ActionEvent event) {
        try {
            Payment payment = paymentSessionBean
                    .createNewPayment(newPayment,
                            projectToPay.getPostingId(),
                            projectToPay.getStartup().getStartupId(),
                            studentToPay.getStudentId());
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Payment has been transferred (Payment ID: " + payment.getPaymentId()+ ")"
                            + "to Student ID: " + studentToPay.getStudentId()
                            + "for Project ID: " + projectToPay.getPostingId(),
                            null));
            newPayment = new Payment();
            studentToPay = null;
            projectToPay = null;
        } catch (InputDataValidationException | CreateNewPaymentException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while transfering payment: " + ex.getMessage(), null));
        }
    }

    
    public Student getStudentToPay() {
        return studentToPay;
    }

    public void setStudentToPay(Student studentToPay) {
        this.studentToPay = studentToPay;
    }

    public Project getProjectToPay() {
        return projectToPay;
    }

    public void setProjectToPay(Project projectToPay) {
        this.projectToPay = projectToPay;
    }

    public Payment getNewPayment() {
        return newPayment;
    }

    public void setNewPayment(Payment newPayment) {
        this.newPayment = newPayment;
    }

    
    
}
