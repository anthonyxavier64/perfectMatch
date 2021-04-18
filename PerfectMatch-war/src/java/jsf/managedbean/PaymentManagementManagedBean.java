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
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import util.exception.CreateNewPaymentException;
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
    
    private Payment paymentToView;
    

    /**
     * Creates a new instance of PaymentManagementManagedBean
     */
    public PaymentManagementManagedBean() {
        newPayment = new Payment();
    }

    public void completePayment() {
        try {
            System.out.println("********** PaymentManagementManagedBean.completePayment");

            newPayment.setDateOfTransaction(new Date());
            newPayment.setDescription("For Project: "
                    + projectToPay.getTitle()
                    + " with ID: "
                    + projectToPay.getPostingId());
            newPayment.setPaymentAmount(projectToPay.getPay());

            Payment payment = paymentSessionBean
                    .createNewPayment(newPayment,
                            projectToPay.getPostingId(),
                            projectToPay.getStartup().getStartupId(),
                            studentToPay.getStudentId());
            
            StartUp currentStartUp = (StartUp) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("currentStartUp");
            
            if (currentStartUp.isIsPremium() == false) {
                FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Payment has been transferred (Payment ID: " + payment.getPaymentId() + ") "
                            + "to Student ID: " + studentToPay.getStudentId()
                            + " for Project ID: " + projectToPay.getPostingId() 
                            + ". A 5% commission has been charged.",
                            null));
            } else {
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Payment has been transferred (Payment ID: " + payment.getPaymentId() + ") "
                                + "to Student ID: " + studentToPay.getStudentId()
                                + " for Project ID: " + projectToPay.getPostingId(),
                                null));
            }
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

    public Payment getPaymentToView() {
        return paymentToView;
    }

    public void setPaymentToView(Payment paymentToView) {
        this.paymentToView = paymentToView;
    }

}
