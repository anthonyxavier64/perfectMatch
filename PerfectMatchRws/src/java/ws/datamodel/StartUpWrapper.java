/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Job;
import entity.Payment;
import entity.Posting;
import entity.Project;
import entity.ReviewOfStartUp;
import enumeration.Industry;
import enumeration.StartUpLocation;
import java.util.ArrayList;
import java.util.List;

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
}
