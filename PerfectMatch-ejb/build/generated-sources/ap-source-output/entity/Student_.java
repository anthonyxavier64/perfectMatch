package entity;

import entity.Application;
import entity.Offer;
import entity.Payment;
import java.util.Date;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.4.v20190115-rNA", date="2021-03-23T12:52:12")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile ListAttribute<Student, Offer> offers;
    public static volatile ListAttribute<Student, Payment> payments;
    public static volatile SingularAttribute<Student, Date> projectedGraduationYear;
    public static volatile SingularAttribute<Student, String[]> relevantSkills;
    public static volatile SingularAttribute<Student, String> biography;
    public static volatile SingularAttribute<Student, Long> studentId;
    public static volatile SingularAttribute<Student, String> password;
    public static volatile SingularAttribute<Student, String> courseOfStudy;
    public static volatile SingularAttribute<Student, String> educationalInstitute;
    public static volatile SingularAttribute<Student, Integer> yearOfStudy;
    public static volatile SingularAttribute<Student, Date[]> availabiltiyPeriod;
    public static volatile SingularAttribute<Student, String> email;
    public static volatile ListAttribute<Student, Application> applications;

}