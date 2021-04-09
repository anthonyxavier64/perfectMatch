/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import ejb.session.stateless.JobSessionBeanLocal;
import entity.Job;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ws.datamodel.JobWrapper;

/**
 * REST Web Service
 *
 * @author yappeizhen
 */
@Path("Job")
public class JobResource {

    JobSessionBeanLocal jobSessionBeanLocal = lookupJobSessionBeanLocal();
    
    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of JobResource
     */
    public JobResource() {
    }
    
    
    @Path("retrieveAllJobs")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllProjects() {
        try {
            List<Job> jobs = jobSessionBeanLocal.retrieveAllJobs();

            List<JobWrapper> jobWrappers = new ArrayList<>();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (int i = 0; i < jobs.size(); i++) {
                JobWrapper newJobWrapper = new JobWrapper();
                newJobWrapper.setPostingId(jobs.get(i).getPostingId());
                newJobWrapper.setJobTitle(jobs.get(i).getTitle());
                newJobWrapper.setJobDescription(jobs.get(i).getDescription());
                newJobWrapper.setMonthlySalary(jobs.get(i).getPay());

                if (jobs.get(i).getEarliestStartDate() != null) {
                    newJobWrapper.setEarliestStartDate(simpleDateFormat.format(jobs.get(i).getEarliestStartDate()));
                }

                if (jobs.get(i).getLatestStartDate() != null) {
                    newJobWrapper.setLatestStartDate(simpleDateFormat.format(jobs.get(i).getLatestStartDate()));
                }

                newJobWrapper.setIndustry(jobs.get(i).getIndustry());
                
                String[] skillsArray = jobs.get(i).getRequiredSkills().toArray(new String[0]);
                newJobWrapper.setRequiredSkills(skillsArray);
                jobWrappers.add(newJobWrapper);
            }

            GenericEntity<List<JobWrapper>> genericEntity = new GenericEntity<List<JobWrapper>>(jobWrappers) {
            };

            return Response.status(Response.Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @Path("retrieveJobById/{postingId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveJobById(@PathParam("postingId") Long postingId) {
        System.out.println("here");
        try {
            Job job = jobSessionBeanLocal.retrieveJobById(postingId);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            JobWrapper newJobWrapper = new JobWrapper();
            newJobWrapper.setPostingId(job.getPostingId());
            newJobWrapper.setJobTitle(job.getTitle());
            newJobWrapper.setJobDescription(job.getDescription());
            newJobWrapper.setMonthlySalary(job.getPay());

            if (job.getEarliestStartDate() != null) {
                newJobWrapper.setEarliestStartDate(simpleDateFormat.format(job.getEarliestStartDate()));
            }

            if (job.getLatestStartDate() != null) {
                newJobWrapper.setLatestStartDate(simpleDateFormat.format(job.getLatestStartDate()));
            }

            newJobWrapper.setIndustry(job.getIndustry());
            
            String[] skillsArray = job.getRequiredSkills().toArray(new String[0]);
            newJobWrapper.setRequiredSkills(skillsArray);
            
            return Response.status(Response.Status.OK).entity(newJobWrapper).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }
    
    private JobSessionBeanLocal lookupJobSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (JobSessionBeanLocal) c.lookup("java:global/PerfectMatch/PerfectMatch-ejb/JobSessionBean!ejb.session.stateless.JobSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
