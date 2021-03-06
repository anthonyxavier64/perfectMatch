/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import java.util.Set;

/**
 *
 * @author Antho
 */
@javax.ws.rs.ApplicationPath("Resources")
public class ApplicationConfig extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.rest.ApplicationResource.class);
        resources.add(ws.rest.CorsFilter.class);
        resources.add(ws.rest.JobResource.class);
        resources.add(ws.rest.OfferResource.class);
        resources.add(ws.rest.PostingResource.class);
        resources.add(ws.rest.ProjectResource.class);
        resources.add(ws.rest.StartUpResource.class);
        resources.add(ws.rest.StudentResource.class);
    }
    
}
