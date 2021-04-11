/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import enumeration.Industry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Antho
 */
@Entity
public class Project extends Posting implements Serializable {
    
    private String projectSpecialisation;
    
    private List<String> milestones;

    @NotNull
    @Column(nullable = false)
    private boolean isComplete;

    public Project() {
        super();
        milestones = new ArrayList<>();
        isComplete = false;
    }

    public Project(String title, String description, Double pay, Date earliestStartDate, Date latestStartDate, Industry industry, List<String> requiredSkills, String projectSpecialisation) {
        super(title, description, pay, industry, requiredSkills, earliestStartDate, latestStartDate);
        this.isComplete = false;
        this.milestones = new ArrayList<>();
        this.projectSpecialisation = projectSpecialisation;
    }

    public String getProjectSpecialisation() {
        return projectSpecialisation;
    }

    public void setProjectSpecialisation(String projectSpecialisation) {
        this.projectSpecialisation = projectSpecialisation;
    }

    public List<String> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<String> milestones) {
        this.milestones = milestones;
    }

    public boolean isIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
}