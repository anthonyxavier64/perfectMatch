/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import ejb.session.stateless.OfferSessionBeanLocal;
import entity.Job;
import entity.Offer;
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
import ws.datamodel.JobWrapper;
import ws.datamodel.OfferWrapper;

/**
 * REST Web Service
 *
 * @author yappeizhen
 */
@Path("Offer")
public class OfferResource {

    OfferSessionBeanLocal offerSessionBeanLocal = lookupOfferSessionBeanLocal();

    @Context
    private UriInfo context;
   

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

    
}
