package entity;

import entity.Project;
import entity.Startup;
import entity.Student;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.4.v20190115-rNA", date="2021-03-23T12:52:12")
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SingularAttribute<Payment, Date> dateOfTransaction;
    public static volatile SingularAttribute<Payment, Student> student;
    public static volatile SingularAttribute<Payment, Long> paymentId;
    public static volatile SingularAttribute<Payment, Startup> startup;
    public static volatile SingularAttribute<Payment, String> description;
    public static volatile SingularAttribute<Payment, Project> project;
    public static volatile SingularAttribute<Payment, Double> paymentAmount;

}