/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import ejb.session.stateless.PostingSessionBeanLocal;
import entity.Job;
import entity.Posting;
import entity.Project;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ws.datamodel.JobWrapper;
import ws.datamodel.PostingWrapper;
import ws.datamodel.ProjectWrapper;
import ws.datamodel.StartUpWrapper;

/**
 * REST Web Service
 *
 * @author yappeizhen
 */
@Path("Posting")
public class PostingResource {

    PostingSessionBeanLocal postingSessionBeanLocal = lookupPostingSessionBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PostingResource
     */
    public PostingResource() {
    }

    
    @Path("retrievePostingById/{postingId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveJobById(@PathParam("postingId") Long postingId) {
        try {
            Posting posting = postingSessionBeanLocal.retrievePostingByPostingId(postingId);
            PostingWrapper result;
            if (posting instanceof Project) {
                ProjectWrapper newProjectWrapper = ProjectWrapper.convertProjectToProjectWrapper((Project) posting);
                result = newProjectWrapper;
            } else {
                PostingWrapper newJobWrapper = JobWrapper.convertJobToJobWrapper((Job) posting);
                result = newJobWrapper;
            }
            result.setStartup(StartUpWrapper.convertStartUpToStartUpWrapper(posting.getStartup()));

            return Response.status(Response.Status.OK).entity(result).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }
    
    
    private PostingSessionBeanLocal lookupPostingSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PostingSessionBeanLocal) c.lookup("java:global/PerfectMatch/PerfectMatch-ejb/PostingSessionBean!ejb.session.stateless.PostingSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    
}
