/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import enumeration.Industry;

/**
 *
 * @author yappeizhen
 */
public class JobWrapper extends PostingWrapper {

    public JobWrapper() {
        super();
    }

    public JobWrapper(Long postingId, String title, String description, Double pay, String earliestStartDate, String latestStartDate, Industry industry, String[] requiredSkills) {
        super(postingId, title, description, pay, earliestStartDate, latestStartDate, industry, requiredSkills);
    }

}