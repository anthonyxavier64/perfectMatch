/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import enumeration.Industry;
import enumeration.StartUpLocation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Antho
 */
@Entity
public class StartUp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long startupId;

    @NotNull
    @Column(nullable = false)
    private String startupRegistrationNum;

    @NotNull
    @Column(nullable = false)
    private String companyName;

    private String description;

    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private Industry industry;
    
    private StartUpLocation startupLocation;
    
    @OneToMany(mappedBy = "startup")
    private List<Posting> postings;
    
    @OneToMany(mappedBy = "startup")
    private List<Payment> payments;

    public StartUp() {
    }

    public StartUp(String startupRegistrationNum, String description, String email, String password, Industry industry, StartUpLocation startupLocation) {
        this.startupRegistrationNum = startupRegistrationNum;
        this.description = description;
        this.email = email;
        this.password = password;
        this.industry = industry;
        this.startupLocation = startupLocation;
        this.postings = new ArrayList<>();
        this.payments = new ArrayList<>();
    }

    public StartUp(String startupRegistrationNum, String companyName, String description, String email, String password, Industry industry, StartUpLocation startupLocation) {
        this.startupRegistrationNum = startupRegistrationNum;
        this.companyName = companyName;
        this.description = description;
        this.email = email;
        this.password = password;
        this.industry = industry;
        this.startupLocation = startupLocation;
    }
    
    
   

    public Long getStartupId() {
        return startupId;
    }

    public void setStartupId(Long startupId) {
        this.startupId = startupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (startupId != null ? startupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the startupId fields are not set
        if (!(object instanceof StartUp)) {
            return false;
        }
        StartUp other = (StartUp) object;
        if ((this.startupId == null && other.startupId != null) || (this.startupId != null && !this.startupId.equals(other.startupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StartupEntity[ id=" + startupId + " ]";
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

    public List<Posting> getPostings() {
        return postings;
    }

    public void setPostings(List<Posting> postings) {
        this.postings = postings;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

}
