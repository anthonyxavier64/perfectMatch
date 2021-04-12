/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

/**
 *
 * @author yappeizhen
 */
public class PaymentWrapper {
    private Long paymentId;
    private Double paymentAmount;
    private String description;
    private String dateOfTransaction;
    private ProjectWrapper project;
    private StartUpWrapper startup;
    private StudentWrapper student;

    public PaymentWrapper() {
    }

    public PaymentWrapper(Long paymentId, Double paymentAmount, String description, String dateOfTransaction, ProjectWrapper project, StartUpWrapper startup, StudentWrapper student) {
        this.paymentId = paymentId;
        this.paymentAmount = paymentAmount;
        this.description = description;
        this.dateOfTransaction = dateOfTransaction;
        this.project = project;
        this.startup = startup;
        this.student = student;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(String dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public ProjectWrapper getProject() {
        return project;
    }

    public void setProject(ProjectWrapper project) {
        this.project = project;
    }

    public StartUpWrapper getStartup() {
        return startup;
    }

    public void setStartup(StartUpWrapper startup) {
        this.startup = startup;
    }

    public StudentWrapper getStudent() {
        return student;
    }

    public void setStudent(StudentWrapper student) {
        this.student = student;
    }
    
    
}
