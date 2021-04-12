/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Student;
import enumeration.Industry;

/**
 *
 * @author yappeizhen
 */
public class PostingWrapper {
    private Long postingId;
    private String title;
    private String description;
    private Double pay;
    private String earliestStartDate;
    private String latestStartDate;
    private Industry industry;
    private String[] requiredSkills;
    
    private StartUpWrapper startup;
    private StudentWrapper student;

    public PostingWrapper() {
    }

    public PostingWrapper(Long postingId, String title, String description, Double pay, String earliestStartDate, String latestStartDate, Industry industry, String[] requiredSkills) {
        this.postingId = postingId;
        this.title = title;
        this.description = description;
        this.pay = pay;
        this.earliestStartDate = earliestStartDate;
        this.latestStartDate = latestStartDate;
        this.industry = industry;
        this.requiredSkills = requiredSkills;
    }

    public Long getPostingId() {
        return postingId;
    }

    public void setPostingId(Long postingId) {
        this.postingId = postingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
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
