/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import ejb.session.stateless.ApplicationSessionBeanLocal;
import ejb.session.stateless.PostingSessionBeanLocal;
import ejb.session.stateless.StudentSessionBeanLocal;
import entity.Application;
import entity.Project;
import enumeration.ApplicationStatus;
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
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import util.exception.RepeatedApplicationException;
import ws.datamodel.ApplicationWrapper;
import ws.datamodel.PostingWrapper;
import ws.datamodel.StartUpWrapper;
import ws.datamodel.StudentWrapper;

/**
 * REST Web Service
 *
 * @author yappeizhen
 */
@Path("Application")
public class ApplicationResource {

    StudentSessionBeanLocal studentSessionBean = lookupStudentSessionBeanLocal();

    PostingSessionBeanLocal postingSessionBean = lookupPostingSessionBeanLocal();

    ApplicationSessionBeanLocal applicationSessionBean = lookupApplicationSessionBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApplicationResource
     */
    public ApplicationResource() {
    }

    @Path("createNewApplication")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewApplication(ApplicationWrapper app) {
        try {
            Application newApp = new Application();

            for (ApplicationStatus status : ApplicationStatus.values()) {

                if (status == app.getApplicationStatus()) {
                    newApp.setApplicationStatus(status);
                }
            }
            
//            newApp.setApplicationStatus(app.getApplicationStatus());
            newApp.setOfferSent(app.getOfferSent());

            Application createdApp = applicationSessionBean.createNewApplication(newApp, app.getStudentId(), app.getPostingId());
            app.setApplicationId(createdApp.getApplicationId());
            app.setStudent(StudentWrapper.convertStudentToStudentWrapper(createdApp.getStudent()));
            app.setPosting(PostingWrapper.convertPostingToPostingWrapper(createdApp.getPosting()));

            return Response.status(Status.OK).entity(app).build();
        } catch (RepeatedApplicationException ex) {
            System.out.println(ex.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @Path("retrieveApplicationById/{applicationId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveApplicationById(@PathParam("applicationId") Long id) {
        try {
            Application application = applicationSessionBean.retrieveApplicationByApplicationId(id);
            ApplicationWrapper app = ApplicationWrapper.createApplicationWrapper(application);

            PostingWrapper postWrap = PostingWrapper.convertPostingToPostingWrapper(application.getPosting());
            if (application.getPosting() instanceof Project) {
                postWrap.setIsProject(true);
            } else {
                postWrap.setIsProject(false);
            }

            StartUpWrapper startWrap = StartUpWrapper.convertStartUpToStartUpWrapper(application.getPosting().getStartup());
            postWrap.setStartup(startWrap);

            app.setPosting(postWrap);
            app.setStudent(StudentWrapper.convertStudentToStudentWrapper(application.getStudent()));

            return Response.status(Status.OK).entity(app).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    private ApplicationSessionBeanLocal lookupApplicationSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ApplicationSessionBeanLocal) c.lookup("java:global/PerfectMatch/PerfectMatch-ejb/ApplicationSessionBean!ejb.session.stateless.ApplicationSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
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

    private StudentSessionBeanLocal lookupStudentSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (StudentSessionBeanLocal) c.lookup("java:global/PerfectMatch/PerfectMatch-ejb/StudentSessionBean!ejb.session.stateless.StudentSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
