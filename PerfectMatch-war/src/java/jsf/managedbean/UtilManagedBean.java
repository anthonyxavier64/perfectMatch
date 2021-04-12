/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import enumeration.Industry;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author user
 */
@Named(value = "utilManagedBean")
@RequestScoped
public class UtilManagedBean {

    List<Industry> listOfIndustries;
    
    /**
     * Creates a new instance of UtilManagedBean
     */
    public UtilManagedBean() {
    }
    
    public List<Industry> getAllIndustries() {
        for (Industry industry: Industry.values()) {
            listOfIndustries.add(industry);
        }
        return listOfIndustries;
    }
}
