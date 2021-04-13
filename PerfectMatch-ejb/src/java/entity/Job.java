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
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Antho
 */
@Entity
public class Job extends Posting implements Serializable {
    public Job() {
        super();
    }

    public Job(String title, String description, Double pay, Date earliestStartDate, Date latestStartDate, Industry industry, List<String> requiredSkills) {
        super(title, description, pay, industry, requiredSkills, earliestStartDate, latestStartDate);
    }
    
}
