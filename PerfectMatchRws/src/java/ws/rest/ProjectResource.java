/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import ejb.session.stateless.ProjectSessionBeanLocal;
import entity.Project;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ws.datamodel.ProjectWrapper;

/**
 * REST Web Service
 *
 * @author Antho
 */
@Path("Project")
public class ProjectResource {

    ProjectSessionBeanLocal projectSessionBeanLocal = lookupProjectSessionBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProjectResource
     */
    public ProjectResource() {
    }

    @Path("retrieveAllProjects")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllProjects() {
        try {
            List<Project> projects = projectSessionBeanLocal.retrieveAllProjects();

            List<ProjectWrapper> projectWrappers = new ArrayList<>();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (int i = 0; i < projects.size(); i++) {
                ProjectWrapper newProjectWrapper = new ProjectWrapper();
                newProjectWrapper.setPostingId(projects.get(i).getPostingId());
                newProjectWrapper.setTitle(projects.get(i).getTitle());
                newProjectWrapper.setDescription(projects.get(i).getDescription());
                newProjectWrapper.setPay(projects.get(i).getPay());

                if (projects.get(i).getEarliestStartDate() != null) {
                    newProjectWrapper.setEarliestStartDate(simpleDateFormat.format(projects.get(i).getEarliestStartDate()));
                }

                if (projects.get(i).getLatestStartDate() != null) {
                    newProjectWrapper.setLatestStartDate(simpleDateFormat.format(projects.get(i).getLatestStartDate()));
                }

                newProjectWrapper.setIndustry(projects.get(i).getIndustry());

                String[] skillsArray = projects.get(i).getRequiredSkills().toArray(new String[0]);
                String[] milestoneArray = projects.get(i).getMilestones().toArray(new String[0]);
                
                newProjectWrapper.setRequiredSkills(skillsArray);
                newProjectWrapper.setProjectSpecialisation(projects.get(i).getProjectSpecialisation());
                newProjectWrapper.setIsComplete(projects.get(i).isIsComplete());
                newProjectWrapper.setMilestones(milestoneArray);
                projectWrappers.add(newProjectWrapper);
            }

            GenericEntity<List<ProjectWrapper>> genericEntity = new GenericEntity<List<ProjectWrapper>>(projectWrappers) {
            };

            return Response.status(Response.Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @Path("retrieveProjectById/{postingId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveProjectById(@PathParam("postingId") Long postingId) {
        System.out.println("here");
        try {
            Project project = projectSessionBeanLocal.retrieveProjectById(postingId);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            ProjectWrapper newProjectWrapper = new ProjectWrapper();
            newProjectWrapper.setPostingId(project.getPostingId());
            newProjectWrapper.setTitle(project.getTitle());
            newProjectWrapper.setDescription(project.getDescription());
            newProjectWrapper.setPay(project.getPay());

            if (project.getEarliestStartDate() != null) {
                newProjectWrapper.setEarliestStartDate(simpleDateFormat.format(project.getEarliestStartDate()));
            }

            if (project.getLatestStartDate() != null) {
                newProjectWrapper.setLatestStartDate(simpleDateFormat.format(project.getLatestStartDate()));
            }

            newProjectWrapper.setIndustry(project.getIndustry());

            String[] skillsArray = project.getRequiredSkills().toArray(new String[0]);
            String[] milestonesArray = project.getMilestones().toArray(new String[0]);
            
            newProjectWrapper.setRequiredSkills(skillsArray);
            newProjectWrapper.setProjectSpecialisation(project.getProjectSpecialisation());
            newProjectWrapper.setIsComplete(project.isIsComplete());
            newProjectWrapper.setMilestones(milestonesArray);

            return Response.status(Response.Status.OK).entity(newProjectWrapper).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    private ProjectSessionBeanLocal lookupProjectSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ProjectSessionBeanLocal) c.lookup("java:global/PerfectMatch/PerfectMatch-ejb/ProjectSessionBean!ejb.session.stateless.ProjectSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
