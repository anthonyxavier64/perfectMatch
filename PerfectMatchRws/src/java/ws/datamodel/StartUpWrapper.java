/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Posting;
import entity.Project;
import entity.StartUp;
import enumeration.Industry;
import enumeration.StartUpLocation;

/**
 *
 * @author yappeizhen
 */
public class StartUpWrapper {
    private Long startupId;
    private String startupRegistrationNum;
    private String companyName;
    private String description;
    private String email;
    private String password;
    private Industry industry;
    private StartUpLocation startupLocation;
    private PostingWrapper[] postings;
    private ProjectWrapper[] projects;
    private JobWrapper[] jobs;
    private PaymentWrapper[] payments;
    private String rating;
    private ReviewOfStartUpWrapper[] reviews;    

    public StartUpWrapper() {
    }

    public static StartUpWrapper convertStartUpToStartUpWrapper(StartUp startup) {
        StartUpWrapper result = new StartUpWrapper();
        result.setCompanyName(startup.getCompanyName());
        result.setStartupId(startup.getStartupId());
        result.setStartupRegistrationNum(startup.getStartupRegistrationNum());
        result.setDescription(startup.getDescription());
        result.setEmail(startup.getEmail());
        result.setPassword(startup.getPassword());
        result.setIndustry(startup.getIndustry());
        result.setStartupLocation(startup.getStartupLocation());
        result.setRating(startup.getRating());
   
        return result;
    }

    public StartUpWrapper(Long startupId, String startupRegistrationNum, String companyName, String description, String email, String password, Industry industry, StartUpLocation startupLocation, PostingWrapper[] postings, ProjectWrapper[] projects, JobWrapper[] jobs, PaymentWrapper[] payments, String rating, ReviewOfStartUpWrapper[] reviews) {
        this.startupId = startupId;
        this.startupRegistrationNum = startupRegistrationNum;
        this.companyName = companyName;
        this.description = description;
        this.email = email;
        this.password = password;
        this.industry = industry;
        this.startupLocation = startupLocation;
        this.postings = postings;
        this.projects = projects;
        this.jobs = jobs;
        this.payments = payments;
        this.rating = rating;
        this.reviews = reviews;
    }

    public Long getStartupId() {
        return startupId;
    }

    public void setStartupId(Long startupId) {
        this.startupId = startupId;
    }

    public String getStartupRegistrationNum() {
        return startupRegistrationNum;
    }

    public void setStartupRegistrationNum(String startupRegistrationNum) {
        this.startupRegistrationNum = startupRegistrationNum;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public StartUpLocation getStartupLocation() {
        return startupLocation;
    }

    public void setStartupLocation(StartUpLocation startupLocation) {
        this.startupLocation = startupLocation;
    }

    public PostingWrapper[] getPostings() {
        return postings;
    }

    public void setPostings(PostingWrapper[] postings) {
        this.postings = postings;
    }

    public ProjectWrapper[] getProjects() {
        return projects;
    }

    public void setProjects(ProjectWrapper[] projects) {
        this.projects = projects;
    }

    public JobWrapper[] getJobs() {
        return jobs;
    }

    public void setJobs(JobWrapper[] jobs) {
        this.jobs = jobs;
    }

    public PaymentWrapper[] getPayments() {
        return payments;
    }

    public void setPayments(PaymentWrapper[] payments) {
        this.payments = payments;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public ReviewOfStartUpWrapper[] getReviews() {
        return reviews;
    }

    public void setReviews(ReviewOfStartUpWrapper[] reviews) {
        this.reviews = reviews;
    }
    
    
}
