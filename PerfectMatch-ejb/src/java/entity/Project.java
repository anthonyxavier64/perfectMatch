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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Antho
 */
@Entity
public class Project extends Posting implements Serializable {

    @NotNull
    @Column(nullable = false)
    private String projectTitle;

    @NotNull
    @Column(nullable = false)
    private String projectDescription;

    @NotNull
    @Column(nullable = false)
    private Double compensation;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date earliestStartDate;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date latestStartDate;

    @NotNull
    @Column(nullable = false)
    private Industry industry;

    private String[] requiredSkills;

    private String projectSpecialisation;

    @NotNull
    @Column(nullable = false)
    private boolean isComplete;
    
    

    public Project() {
    }

    public Project(String projectTitle, String projectDescription, Double compensation, Industry industry, boolean isComplete) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.compensation = compensation;
        this.industry = industry;
        this.isComplete = isComplete;
    }

    public Project(String projectTitle, String projectDescription, Double compensation, Date earliestStartDate, Date latestStartDate, Industry industry, String[] requiredSkills, String projectSpecialisation, boolean isComplete) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.compensation = compensation;
        this.earliestStartDate = earliestStartDate;
        this.latestStartDate = latestStartDate;
        this.industry = industry;
        this.requiredSkills = requiredSkills;
        this.projectSpecialisation = projectSpecialisation;
        this.isComplete = isComplete;
    }
    
    

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Double getCompensation() {
        return compensation;
    }

    public void setCompensation(Double compensation) {
        this.compensation = compensation;
    }

    public Date getEarliestStartDate() {
        return earliestStartDate;
    }

    public void setEarliestStartDate(Date earliestStartDate) {
        this.earliestStartDate = earliestStartDate;
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

    public String getProjectSpecialisation() {
        return projectSpecialisation;
    }

    public void setProjectSpecialisation(String projectSpecialisation) {
        this.projectSpecialisation = projectSpecialisation;
    }

    public boolean isIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
}
