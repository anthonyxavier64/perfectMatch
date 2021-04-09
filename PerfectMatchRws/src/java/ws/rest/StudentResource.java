/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import ws.datamodel.StudentWrapper;
import ejb.session.stateless.StudentSessionBeanLocal;
import entity.Application;
import entity.Offer;
import entity.Payment;
import entity.Student;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

/**
 * REST Web Service
 *
 * @author Antho
 */
@Path("Student")
public class StudentResource {

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
            Date[] availableDates = new Date[2];
            availableDates[0] = new SimpleDateFormat("yyyy-MM-dd").parse(student.getAvailabilityPeriod()[0]);
            availableDates[1] = new SimpleDateFormat("yyyy-MM-dd").parse(student.getAvailabilityPeriod()[1]);
            Date projectedGraduationYear = new SimpleDateFormat("yyyy-MM-dd").parse(student.getProjectedGraduationYear());
            List<String> skillList = Arrays.asList(student.getRelevantSkills());

            Student newStudent = new Student(student.getName(), student.getEducationalInstitute(),
                    student.getBiography(), student.getEmail(), student.getPassword(),
                    student.getCourseOfStudy(), student.getYearOfStudy(), projectedGraduationYear,
                    skillList, availableDates);

            newStudent = studentSessionBeanLocal.registerStudentAccount(newStudent);

            String[] availablePeriod = new String[2];

            availablePeriod[0] = newStudent.getAvailabilityPeriod()[0].toString();
            availablePeriod[1] = newStudent.getAvailabilityPeriod()[1].toString();
            String[] skillsArray = newStudent.getRelevantSkills().toArray(new String[0]);

            StudentWrapper newStudentWrapper = new StudentWrapper(
                    newStudent.getStudentId(), newStudent.getName(), newStudent.getBiography(), newStudent.getEmail(),
                    newStudent.getPassword(), newStudent.getEducationalInstitute(), newStudent.getCourseOfStudy(),
                    newStudent.getYearOfStudy(), newStudent.getProjectedGraduationYear().toString(),
                    skillsArray, availablePeriod);

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

            String[] availablePeriod = new String[2];

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            availablePeriod[0] = simpleDateFormat.format(student.getAvailabilityPeriod()[0]);
            availablePeriod[1] = simpleDateFormat.format(student.getAvailabilityPeriod()[1]);
            String[] skillsArray = student.getRelevantSkills().toArray(new String[0]);

            StudentWrapper studentWrapper = new StudentWrapper(
                    student.getStudentId(), student.getName(), student.getBiography(), student.getEmail(),
                    student.getPassword(), student.getEducationalInstitute(), student.getCourseOfStudy(),
                    student.getYearOfStudy(), simpleDateFormat.format(student.getProjectedGraduationYear()),
                    skillsArray, availablePeriod);

            return Response.status(Status.OK).entity(studentWrapper).build();
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

    @Path("getStudentOffers")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentOffers(@PathParam("studentId") Long id) {
        try {
            List<Offer> offers = studentSessionBeanLocal.getStudentOffers(id);

            GenericEntity<List<Offer>> genericEntity = new GenericEntity<List<Offer>>(offers) {
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

    @Path("getStudentPayments")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentPayments(@PathParam("studentId") Long id) {
        try {
            List<Payment> payments = studentSessionBeanLocal.getStudentPayments(id);

            GenericEntity<List<Payment>> genericEntity = new GenericEntity<List<Payment>>(payments) {
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
            Date[] availableDates = new Date[2];
            availableDates[0] = new SimpleDateFormat("yyyy-MM-dd").parse(student.getAvailabilityPeriod()[0]);
            availableDates[1] = new SimpleDateFormat("yyyy-MM-dd").parse(student.getAvailabilityPeriod()[1]);
            Date projectedGraduationYear = new SimpleDateFormat("yyyy-MM-dd").parse(student.getProjectedGraduationYear());
            List<String> skillsList = Arrays.asList(student.getRelevantSkills());

            Student newStudent = new Student(student.getStudentId(), student.getName(), student.getEducationalInstitute(),
                    student.getBiography(), student.getEmail(), student.getPassword(),
                    student.getCourseOfStudy(), student.getYearOfStudy(), projectedGraduationYear,
                    skillsList, availableDates);

            newStudent = studentSessionBeanLocal.editStudentDetails(newStudent);

            String[] availablePeriod = new String[2];

            availablePeriod[0] = new SimpleDateFormat("yyyy-MM-dd").format(newStudent.getAvailabilityPeriod()[0]);
            availablePeriod[1] = new SimpleDateFormat("yyyy-MM-dd").format(newStudent.getAvailabilityPeriod()[1]);
            String[] skillsArray = newStudent.getRelevantSkills().toArray(new String[0]);
            StudentWrapper newStudentWrapper = new StudentWrapper(
                    newStudent.getStudentId(), newStudent.getName(), newStudent.getBiography(), newStudent.getEmail(),
                    newStudent.getPassword(), newStudent.getEducationalInstitute(), newStudent.getCourseOfStudy(),
                    newStudent.getYearOfStudy(), new SimpleDateFormat("yyyy-MM-dd").format(newStudent.getProjectedGraduationYear()),
                    skillsArray, availablePeriod);

            return Response.status(Status.OK).entity(newStudentWrapper).build();
        } catch (Exception ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
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
