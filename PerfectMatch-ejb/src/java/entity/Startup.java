/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Antho
 */
@Entity
public class Startup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long startupId;

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
        if (!(object instanceof Startup)) {
            return false;
        }
        Startup other = (Startup) object;
        if ((this.startupId == null && other.startupId != null) || (this.startupId != null && !this.startupId.equals(other.startupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StartupEntity[ id=" + startupId + " ]";
    }
    
}
