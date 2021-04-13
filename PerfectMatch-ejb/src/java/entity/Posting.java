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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Antho
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Posting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postingId;

    @NotNull
    @Column(nullable = false)
    private String title;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private Double pay;

    @NotNull
    @Column(nullable = false)
    private Industry industry;

    private List<String> requiredSkills;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date earliestStartDate;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date latestStartDate;

    @OneToMany(mappedBy = "posting")
    private List<Offer> offers;

    @OneToMany(mappedBy = "posting")
    private List<Application> applications;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private StartUp startup;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Student acceptedStudent;
   
    public Posting() {
        offers = new ArrayList<>();
        applications = new ArrayList<>();
        requiredSkills = new ArrayList<>();
    }

    public Posting(String title, String description, Double pay, Industry industry, List<String> requiredSkills, Date earliestStartDate, Date latestStartDate) {
        this();
        this.title = title;
        this.description = description;
        this.pay = pay;
        this.industry = industry;
        this.requiredSkills = requiredSkills;
        this.earliestStartDate = earliestStartDate;
        this.latestStartDate = latestStartDate;
    }
    
    public Long getPostingId() {
        return postingId;
    }

    public void setPostingId(Long postingId) {
        this.postingId = postingId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postingId != null ? postingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the postingId fields are not set
        if (!(object instanceof Posting)) {
            return false;
        }
        Posting other = (Posting) object;
        if ((this.postingId == null && other.postingId != null) || (this.postingId != null && !this.postingId.equals(other.postingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Posting[ id=" + postingId + " ]";
    }

    public Student getAcceptedStudent() {
        return acceptedStudent;
    }

    public void setAcceptedStudent(Student acceptedStudent) {
        this.acceptedStudent = acceptedStudent;
    }
    
    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public StartUp getStartup() {
        return startup;
    }

    public void setStartup(StartUp startup) {
        this.startup = startup;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
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

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
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
}
