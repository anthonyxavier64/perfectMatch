/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import ejb.session.stateless.ApplicationSessionBeanLocal;
import ejb.session.stateless.PostingSessionBeanLocal;
import ejb.session.stateless.ReviewOfStartUpSessionBeanLocal;
import ejb.session.stateless.StartUpSessionBeanLocal;
import ws.datamodel.StudentWrapper;
import ejb.session.stateless.StudentSessionBeanLocal;
import entity.Application;
import entity.Job;
import entity.Offer;
import entity.Payment;
import entity.Posting;
import entity.Project;
import entity.ReviewOfStartUp;
import entity.Student;
import enumeration.ApplicationStatus;
import enumeration.OfferStatus;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ws.datamodel.ApplicationWrapper;
import ws.datamodel.FavouritesWrapper;
import ws.datamodel.JobWrapper;
import ws.datamodel.OfferWrapper;
import ws.datamodel.PaymentWrapper;
import ws.datamodel.PostingWrapper;
import ws.datamodel.ProjectWrapper;
import ws.datamodel.ReviewOfStartUpWrapper;
import ws.datamodel.ReviewWrapper;
import ws.datamodel.StartUpWrapper;

/**
 * REST Web Service
 *
 * @author Antho
 */
@Path("Student")
public class StudentResource {

    StartUpSessionBeanLocal startUpSessionBeanLocal = lookupStartUpSessionBeanLocal();

    ReviewOfStartUpSessionBeanLocal reviewOfStartUpSessionBeanLocal = lookupReviewOfStartUpSessionBeanLocal();

    PostingSessionBeanLocal postingSessionBeanLocal = lookupPostingSessionBeanLocal();

    ApplicationSessionBeanLocal applicationSessionBean = lookupApplicationSessionBeanLocal();

    StudentSessionBeanLocal studentSessionBeanLocal = lookupStudentSessionBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StudentResource
     */
    public StudentResource() {
    }

    @Path("registerStudentAccount")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerStudentAccount(StudentWrapper student) {
        try {

            Student newStudent = StudentWrapper.convertStudentWrapperToStudent(student);

            newStudent = studentSessionBeanLocal.registerStudentAccount(newStudent);

            String[] availablePeriod = new String[2];

            StudentWrapper newStudentWrapper = StudentWrapper.convertStudentToStudentWrapper(newStudent);

            return Response.status(Status.OK).entity(newStudentWrapper).build();
        } catch (Exception ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @Path("studentLogin")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response studentLogin(@QueryParam("email") String email,
            @QueryParam("password") String password) {
        try {
            Student student = studentSessionBeanLocal.loginStudent(email, password);

            List<FavouritesWrapper> faves = new ArrayList<>();

            for (Posting p : student.getFavorites()) {
                FavouritesWrapper fave = new FavouritesWrapper();
                fave.setPost(PostingWrapper.convertPostingToPostingWrapper(p));
                faves.add(fave);
            }

            StudentWrapper studWrapper = StudentWrapper.convertStudentToStudentWrapper(student);
            FavouritesWrapper[] faveWraps = new FavouritesWrapper[faves.size()];

            int index = 0;
            for (FavouritesWrapper fw : faves) {
                faveWraps[index] = fw;
                index++;
            }
            studWrapper.setFavorites(faveWraps);

            return Response.status(Status.OK).entity(studWrapper).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    @Path("retrieveStudentById")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveStudentById(@PathParam("studentId") Long id) {
        try {
            Student student = studentSessionBeanLocal.retrieveStudentByStudentId(id);

            return Response.status(Status.OK).entity(student).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    @Path("getStudentOffers/{studentId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentOffers(@PathParam("studentId") Long studentId) {
        try {
            List<Offer> offers = studentSessionBeanLocal.getStudentOffers(studentId);

            List<OfferWrapper> offerWrappers = new ArrayList<>();
            for (Offer o : offers) {
                OfferWrapper offerWrapper = OfferWrapper.convertOfferToOfferWrapper(o);

                PostingWrapper postWrap;
                if (o.getPosting() instanceof Project) {
                    postWrap = ProjectWrapper.convertProjectToProjectWrapper((Project) o.getPosting());
                } else {
                    postWrap = JobWrapper.convertJobToJobWrapper((Job) o.getPosting());
                }

                offerWrapper.setPosting(postWrap);

                StudentWrapper stuWrap = StudentWrapper.convertStudentToStudentWrapper(o.getStudent());
                offerWrapper.setStudent(stuWrap);

                StartUpWrapper startWrap = StartUpWrapper.convertStartUpToStartUpWrapper(o.getPosting().getStartup());
                offerWrapper.getPosting().setStartup(startWrap);

                offerWrappers.add(offerWrapper);
            }

            GenericEntity<List<OfferWrapper>> genericEntity = new GenericEntity<List<OfferWrapper>>(offerWrappers) {
            };

            return Response.status(Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    @Path("getStudentApplications")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentApplications(@PathParam("studentId") Long id) {
        try {
            List<Application> applications = studentSessionBeanLocal.getStudentApplications(id);

            GenericEntity<List<Application>> genericEntity = new GenericEntity<List<Application>>(applications) {
            };

            return Response.status(Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    @Path("getStudentPayments/{studentId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentPayments(@PathParam("studentId") Long id) {
        try {
            List<Payment> payments = studentSessionBeanLocal.getStudentPayments(id);
            List<PaymentWrapper> results = new ArrayList<>();
            for (Payment p : payments) {
                PaymentWrapper pwrap = PaymentWrapper.convertPaymentToWrapper(p);
                pwrap.setProject(ProjectWrapper.convertProjectToProjectWrapper(p.getProject()));
                pwrap.setStartup(StartUpWrapper.convertStartUpToStartUpWrapper(p.getStartup()));
                results.add(pwrap);
            }
            GenericEntity<List<PaymentWrapper>> genericEntity = new GenericEntity<List<PaymentWrapper>>(results) {
            };
            return Response.status(Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    @Path("retrieveAllStudents")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllStudents() {
        try {
            List<Student> students = studentSessionBeanLocal.getAllStudents();

            GenericEntity<List<Student>> genericEntity = new GenericEntity<List<Student>>(students) {
            };

            return Response.status(Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    @Path("editStudentDetails")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editStudentDetails(StudentWrapper student) {
        try {
            Student newStudent = StudentWrapper.convertStudentWrapperToStudent(student);

            newStudent = studentSessionBeanLocal.editStudentDetails(newStudent);

            StudentWrapper newStudentWrapper = StudentWrapper.convertStudentToStudentWrapper(newStudent);

            return Response.status(Status.OK).entity(newStudentWrapper).build();
        } catch (Exception ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @Path("getStudentPostings/{studentId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentPostings(@PathParam("studentId") Long id) {
        try {
            List<PostingWrapper> postings = new ArrayList<>();
            List<Application> applications = studentSessionBeanLocal.getStudentApplications(id);
            for (Application app : applications) {
                if (app.getApplicationStatus() == ApplicationStatus.ACCEPTED) {
                    PostingWrapper pw = PostingWrapper.convertPostingToPostingWrapper(app.getPosting());
                    if (app.getPosting() instanceof Project) {
                        pw.setIsProject(true);
                    } else {
                        pw.setIsProject(false);
                    }

                    StartUpWrapper sw = StartUpWrapper.convertStartUpToStartUpWrapper(app.getPosting().getStartup());
                    pw.setStartup(sw);
                    postings.add(pw);
                }
            }
            List<Offer> offers = studentSessionBeanLocal.getStudentOffers(id);
            for (Offer o : offers) {
                if (o.getOfferStatus() == OfferStatus.ACCEPTED) {
                    PostingWrapper pw = PostingWrapper.convertPostingToPostingWrapper(o.getPosting());
                    if (o.getPosting() instanceof Project) {
                        pw.setIsProject(true);
                    } else {
                        pw.setIsProject(false);
                    }

                    StartUpWrapper sw = StartUpWrapper.convertStartUpToStartUpWrapper(o.getPosting().getStartup());
                    pw.setStartup(sw);
                    postings.add(pw);
                }
            }

            GenericEntity<List<PostingWrapper>> genericEntity = new GenericEntity<List<PostingWrapper>>(postings) {
            };

            return Response.status(Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    @Path("createNewReview")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewReview(ReviewWrapper review) {
        try {
            ReviewOfStartUp newReview = new ReviewOfStartUp();
            newReview.setRating(review.getRating());
            newReview.setReview(review.getReview());
            newReview = reviewOfStartUpSessionBeanLocal.addStartupReview(review.getStartUpBeingRatedId(), review.getStudentId(), newReview);

            ReviewWrapper result = new ReviewWrapper();
            result.setReviewId(newReview.getReviewOfStartUpId());
            result.setRating(newReview.getRating());
            result.setReview(newReview.getReview());
            result.setStartUpBeingRatedId(newReview.getStartUpBeingRated().getStartupId());
            result.setStudentId(newReview.getStudent().getStudentId());

            return Response.status(Status.OK).entity(result).build();
        } catch (Exception ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @Path("getReviewsByStudent/{studentId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReviewsByStudent(@PathParam("studentId") Long id) {
        try {
            List<ReviewOfStartUpWrapper> reviews = new ArrayList<>();
            List<ReviewOfStartUp> rs = studentSessionBeanLocal.getReviewsByStudent(id);
            for (ReviewOfStartUp r : rs) {
                ReviewOfStartUpWrapper rev = ReviewOfStartUpWrapper.convertReviewToWrapper(r);
                rev.setStartUpBeingRated(StartUpWrapper.convertStartUpToStartUpWrapper(r.getStartUpBeingRated()));
                reviews.add(rev);
            }
            GenericEntity<List<ReviewOfStartUpWrapper>> genericEntity = new GenericEntity<List<ReviewOfStartUpWrapper>>(reviews) {
            };
            return Response.status(Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    @Path("addFavourite")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFavourite(@QueryParam("studId") Long studentId,
            @QueryParam("postId") Long postingId) {
        try {
            Posting posting = postingSessionBeanLocal.retrievePostingByPostingId(postingId);
            studentSessionBeanLocal.addFavourite(posting, studentId);

            Student student = studentSessionBeanLocal.retrieveStudentByStudentId(studentId);

            List<FavouritesWrapper> faves = new ArrayList<>();

            for (Posting p : student.getFavorites()) {
                FavouritesWrapper fave = new FavouritesWrapper();
                fave.setPost(PostingWrapper.convertPostingToPostingWrapper(p));
                faves.add(fave);
            }

            StudentWrapper studWrapper = StudentWrapper.convertStudentToStudentWrapper(student);
            FavouritesWrapper[] faveWraps = new FavouritesWrapper[faves.size()];

            int index = 0;
            for (FavouritesWrapper fw : faves) {
                faveWraps[index] = fw;
                index++;
            }
            studWrapper.setFavorites(faveWraps);

            return Response.status(Status.OK).entity(studWrapper).build();
        } catch (Exception ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @Path("removeFavourite")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeFavourite(@QueryParam("studId") Long studentId,
            @QueryParam("postId") Long postingId) {
        try {
            Posting posting = postingSessionBeanLocal.retrievePostingByPostingId(postingId);
            studentSessionBeanLocal.removeFavourite(posting, studentId);

            Student student = studentSessionBeanLocal.retrieveStudentByStudentId(studentId);

            List<FavouritesWrapper> faves = new ArrayList<>();

            for (Posting p : student.getFavorites()) {
                FavouritesWrapper fave = new FavouritesWrapper();
                fave.setPost(PostingWrapper.convertPostingToPostingWrapper(p));
                faves.add(fave);
            }

            StudentWrapper studWrapper = StudentWrapper.convertStudentToStudentWrapper(student);
            FavouritesWrapper[] faveWraps = new FavouritesWrapper[faves.size()];

            int index = 0;
            for (FavouritesWrapper fw : faves) {
                faveWraps[index] = fw;
                index++;
            }
            studWrapper.setFavorites(faveWraps);

            return Response.status(Status.OK).entity(studWrapper).build();

        } catch (Exception ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @Path("getApplicationsByStudentId/{studentId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplicationsByStudentId(@PathParam("studentId") Long id
    ) {

        try {
            List<Application> applications = studentSessionBeanLocal.getStudentApplications(id);

            List<ApplicationWrapper> applicationWrappers = new ArrayList<>();

            for (Application a : applications) {
                ApplicationWrapper applicationWrapper = ApplicationWrapper.createApplicationWrapper(a);
                PostingWrapper postWrap = PostingWrapper.convertPostingToPostingWrapper(a.getPosting());

                if (a.getPosting() instanceof Project) {
                    postWrap.setIsProject(true);
                } else {
                    postWrap.setIsProject(false);
                }

                applicationWrapper.setPosting(postWrap);
                applicationWrappers.add(applicationWrapper);
            }

            GenericEntity<List<ApplicationWrapper>> genericEntity = new GenericEntity<List<ApplicationWrapper>>(applicationWrappers) {
            };

            return Response.status(Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    @Path("getFavoritesByStudentId/{studentId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavoritesByStudentId(@PathParam("studentId") Long id
    ) {

        try {
            Student student = studentSessionBeanLocal.retrieveStudentByStudentId(id);

            List<FavouritesWrapper> faves = new ArrayList<>();

            for (Posting p : student.getFavorites()) {
                FavouritesWrapper fave = new FavouritesWrapper();
                fave.setPost(PostingWrapper.convertPostingToPostingWrapper(p));
                faves.add(fave);
            }

            StudentWrapper studWrapper = StudentWrapper.convertStudentToStudentWrapper(student);
            FavouritesWrapper[] faveWraps = new FavouritesWrapper[faves.size()];

            int index = 0;
            for (FavouritesWrapper fw : faves) {
                faveWraps[index] = fw;
                index++;
            }
            studWrapper.setFavorites(faveWraps);

            return Response.status(Status.OK).entity(studWrapper).build();
        } catch (Exception ex) {
            return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).build();
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
