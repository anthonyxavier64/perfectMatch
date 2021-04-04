/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import enumeration.Industry;

/**
 *
 * @author yappeizhen
 */
public class JobWrapper {
    private Long jobId;
    private String jobTitle;
    private String jobDescription;
    private Double monthlySalary;
    private String earliestStartDate;
    private String latestStartDate;
    private Industry industry;
    private String[] requiredSkills;

    public JobWrapper() {
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

    public String getEarliestStartDate() {
        return earliestStartDate;
    }

    public void setEarliestStartDate(String earliestStartDate) {
        this.earliestStartDate = earliestStartDate;
    }

    public String getLatestStartDate() {
        return latestStartDate;
    }

    public void setLatestStartDate(String latestStartDate) {
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

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
    
}