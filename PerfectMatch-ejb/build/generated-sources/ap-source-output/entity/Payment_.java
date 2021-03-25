package entity;

import entity.Project;
import entity.Startup;
import entity.Student;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-25T14:46:07")
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