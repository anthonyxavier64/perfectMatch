/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import ejb.session.stateless.ReviewOfStartUpSessionBeanLocal;
import ejb.session.stateless.StartUpSessionBeanLocal;
import entity.ReviewOfStartUp;
import entity.StartUp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ws.datamodel.ReviewOfStartUpWrapper;
import ws.datamodel.StartUpWrapper;

/**
 * REST Web Service
 *
 * @author Antho
 */
@Path("StartUp")
public class StartUpResource {

    StartUpSessionBeanLocal startUpSessionBean = lookupStartUpSessionBeanLocal();

    ReviewOfStartUpSessionBeanLocal reviewOfStartUpSessionBean = lookupReviewOfStartUpSessionBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StartUpResource
     */
    public StartUpResource() {
    }

    @Path("getStartupById/{startupId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStartupById(@PathParam("startupId") Long id) {
        try {
            StartUp startup = startUpSessionBean.retrieveStartUpByStartUpId(id);

            StartUpWrapper startWrap = StartUpWrapper.convertStartUpToStartUpWrapper(startup);
            
            ReviewOfStartUpWrapper[] revs = new ReviewOfStartUpWrapper[startup.getReviews().size()];
            int index = 0;
            for(ReviewOfStartUp rw: startup.getReviews()) {
                ReviewOfStartUpWrapper newWrap = ReviewOfStartUpWrapper.convertReviewToWrapper(rw);
                revs[0] = newWrap;
                index++;
            }
            
            startWrap.setReviews(revs);

            return Response.status(Status.OK).entity(startWrap).build();

        } catch (Exception ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }

    }
    
    private ReviewOfStartUpSessionBeanLocal lookupReviewOfStartUpSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ReviewOfStartUpSessionBeanLocal) c.lookup("java:global/PerfectMatch/PerfectMatch-ejb/ReviewOfStartUpSessionBean!ejb.session.stateless.ReviewOfStartUpSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private StartUpSessionBeanLocal lookupStartUpSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (StartUpSessionBeanLocal) c.lookup("java:global/PerfectMatch/PerfectMatch-ejb/StartUpSessionBean!ejb.session.stateless.StartUpSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
