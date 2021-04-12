/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import ejb.session.stateless.OfferSessionBeanLocal;
import ejb.session.stateless.PostingSessionBeanLocal;
import entity.Job;
import entity.Offer;
import entity.Posting;
import entity.Project;
import entity.StartUp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ws.datamodel.JobWrapper;
import ws.datamodel.OfferWrapper;
import ws.datamodel.PostingWrapper;
import ws.datamodel.ProjectWrapper;

/**
 * REST Web Service
 *
 * @author yappeizhen
 */
@Path("Offer")
public class OfferResource {

    PostingSessionBeanLocal postingSessionBeanLocal = lookupPostingSessionBeanLocal();

    OfferSessionBeanLocal offerSessionBeanLocal = lookupOfferSessionBeanLocal();
    

    @Context
    private UriInfo context;

    @Path("retrieveAllOffers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllOffers() {
        try {
            List<Offer> offers = offerSessionBeanLocal.retrieveAllOffers();
            List<OfferWrapper> results = new ArrayList<>();
            
            for (Offer o:offers) {
                results.add(OfferWrapper.convertOfferToOfferWrapper(o));
            }
            
            GenericEntity<List<OfferWrapper>> genericEntity = new GenericEntity<List<OfferWrapper>>(results) {
            };
            return Response.status(Response.Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    @Path("retrieveOffer")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveJobById(@PathParam("offerId") Long offerId) {
        try {
            Offer oriOffer = offerSessionBeanLocal.retrieveOfferByOfferId(offerId);
            OfferWrapper offer = OfferWrapper.convertOfferToOfferWrapper(oriOffer);
            
            return Response.status(Status.OK).entity(offer).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }
    
    @Path("getOfferPosting/{offerId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOfferPosting(@PathParam("offerId") Long offerId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Posting posting = offerSessionBeanLocal.getPostingByOfferId(offerId);
            PostingWrapper pwrap = new PostingWrapper();
            if (posting instanceof Project) { 
                pwrap = ProjectWrapper.convertProjectToProjectWrapper((Project) posting);
            } else {
                pwrap = JobWrapper.convertJobToJobWrapper((Job) posting);
            }
            return Response.status(Status.OK).entity(pwrap).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }
    
    @Path("getOfferStartUp")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOfferStartUp(@PathParam("offerId") Long offerId) {
        try {
            Posting posting = offerSessionBeanLocal.getPostingByOfferId(offerId);
            StartUp startup = postingSessionBeanLocal.retrieveStartupFromPostingId(posting.getPostingId());
            return Response.status(Status.OK).entity(startup).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }
    /**
     * Creates a new instance of OfferResource
     */
    public OfferResource() {
    }

    private OfferSessionBeanLocal lookupOfferSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (OfferSessionBeanLocal) c.lookup("java:global/PerfectMatch/PerfectMatch-ejb/OfferSessionBean!ejb.session.stateless.OfferSessionBeanLocal");
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

}