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
public class ProjectWrapper extends PostingWrapper {
    
    private String projectSpecialisation;
    private Boolean isComplete;
    private String[] milestones;

    public ProjectWrapper() {
        super();
    }

    public ProjectWrapper(String projectSpecialisation, Boolean isComplete, String[] milestones, Long postingId, String title, String description, Double pay, String earliestStartDate, String latestStartDate, Industry industry, String[] requiredSkills) {
        super(postingId, title, description, pay, earliestStartDate, latestStartDate, industry, requiredSkills);
        this.projectSpecialisation = projectSpecialisation;
        this.isComplete = isComplete;
        this.milestones = milestones;
    }

    public String getProjectSpecialisation() {
        return projectSpecialisation;
    }

    public void setProjectSpecialisation(String projectSpecialisation) {
        this.projectSpecialisation = projectSpecialisation;
    }

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String[] getMilestones() {
        return milestones;
    }

    public void setMilestones(String[] milestones) {
        this.milestones = milestones;
    }
}
