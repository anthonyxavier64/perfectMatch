/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Project;
import enumeration.Industry;
import java.text.SimpleDateFormat;

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
    
    public static ProjectWrapper convertProjectToProjectWrapper(Project project) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ProjectWrapper newProjectWrapper = new ProjectWrapper();
        newProjectWrapper.setPostingId(project.getPostingId());
        newProjectWrapper.setTitle(project.getTitle());
        newProjectWrapper.setDescription(project.getDescription());
        newProjectWrapper.setPay(project.getPay());

        if (project.getEarliestStartDate() != null) {
            newProjectWrapper.setEarliestStartDate(simpleDateFormat.format(project.getEarliestStartDate()));
        }

        if (project.getLatestStartDate() != null) {
            newProjectWrapper.setLatestStartDate(simpleDateFormat.format(project.getLatestStartDate()));
        }

        newProjectWrapper.setIndustry(project.getIndustry());

        String[] skillsArray = project.getRequiredSkills().toArray(new String[0]);
        String[] milestoneArray = project.getMilestones().toArray(new String[0]);

        newProjectWrapper.setRequiredSkills(skillsArray);
        newProjectWrapper.setProjectSpecialisation(project.getProjectSpecialisation());
        newProjectWrapper.setIsComplete(project.isIsComplete());
        newProjectWrapper.setMilestones(milestoneArray);
                
        return newProjectWrapper;
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
