/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import enumeration.Industry;
import java.io.Serializable;
import java.util.Date;
import java.util.Timer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Antho
 */
@Entity
public class Job extends Posting implements Serializable {
    
    @NotNull
    @Column(nullable = false)
    private String jobTitle;
    
    @NotNull
    @Column(nullable = false)
    private String jobDescription;
    
    @NotNull
    @Column(nullable = false)
    private Double monthlySalary;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date earlietStartDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date latestStartDate;
    
    @NotNull
    @Column(nullable = false)
    private Industry industry;
    
    private String[] requiredSkills;

    public Job() {
    }

    public Job(String jobTitle, String jobDescription, Double monthlySalary, Date earlietStartDate, Date latestStartDate, Industry industry, String[] requiredSkills) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.monthlySalary = monthlySalary;
        this.earlietStartDate = earlietStartDate;
        this.latestStartDate = latestStartDate;
        this.industry = industry;
        this.requiredSkills = requiredSkills;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public Date getEarlietStartDate() {
        return earlietStartDate;
    }

    public void setEarlietStartDate(Date earlietStartDate) {
        this.earlietStartDate = earlietStartDate;
    }

    public Date getLatestStartDate() {
        return latestStartDate;
    }

    public void setLatestStartDate(Date latestStartDate) {
        this.latestStartDate = latestStartDate;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public String[] getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String[] requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

}
