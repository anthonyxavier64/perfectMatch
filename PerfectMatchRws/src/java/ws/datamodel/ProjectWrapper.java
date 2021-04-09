/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import enumeration.Industry;

/**
 *
 * @author Antho
 */
public class ProjectWrapper {
    private Long postingId;
    private String projectTitle;
    private String projectDescription;
    private Double compensation;
    private String earliestStartDate;
    private String latestStartDate;
    private Industry industry;
    private String[] requiredSkills;
    private String projectSpecialisation;
    private Boolean isComplete;

    public ProjectWrapper() {
    }

    public ProjectWrapper(long postingId, String projectTitle, String projectDescription, double compensation, String earliestStartDate, String latestEndDate, Industry industry, String[] requiredSkills, String projectSpecialisation, boolean isComplete) {
        this.postingId = postingId;
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.compensation = compensation;
        this.earliestStartDate = earliestStartDate;
        this.latestStartDate = latestEndDate;
        this.industry = industry;
        this.requiredSkills = requiredSkills;
        this.projectSpecialisation = projectSpecialisation;
        this.isComplete = isComplete;
    }

    public long getPostingId() {
        return postingId;
    }

    public void setPostingId(long postingId) {
        this.postingId = postingId;
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

    public double getCompensation() {
        return compensation;
    }

    public void setCompensation(double compensation) {
        this.compensation = compensation;
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
