package entity;

import entity.Posting;
import entity.Student;
import enumeration.ApplicationStatus;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-25T14:46:07")
@StaticMetamodel(Application.class)
public class Application_ { 

    public static volatile SingularAttribute<Application, Boolean> offerSent;
    public static volatile SingularAttribute<Application, ApplicationStatus> applicationStatus;
    public static volatile SingularAttribute<Application, Student> student;
    public static volatile SingularAttribute<Application, Long> applicationId;
    public static volatile SingularAttribute<Application, Posting> posting;

}