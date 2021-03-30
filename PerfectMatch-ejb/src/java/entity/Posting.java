/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Antho
 */
@Entity
public abstract class Posting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postingId;
    
    @OneToMany(mappedBy = "posting")
    private List<Offer> offers;
    
    @OneToMany(mappedBy = "posting")
    private List<Application> applications;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Startup startup;

    public Posting() {
        offers = new ArrayList<>();
        applications = new ArrayList<>();
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

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Startup getStartup() {
        return startup;
    }

    public void setStartup(Startup startup) {
        this.startup = startup;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
    
}