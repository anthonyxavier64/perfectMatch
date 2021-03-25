package entity;

import entity.Payment;
import entity.Posting;
import enumeration.Industry;
import enumeration.StartupLocation;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-25T14:46:07")
@StaticMetamodel(Startup.class)
public class Startup_ { 

    public static volatile SingularAttribute<Startup, String> password;
    public static volatile SingularAttribute<Startup, String> companyName;
    public static volatile ListAttribute<Startup, Payment> payments;
    public static volatile SingularAttribute<Startup, String> description;
    public static volatile ListAttribute<Startup, Posting> postings;
    public static volatile SingularAttribute<Startup, Industry> industry;
    public static volatile SingularAttribute<Startup, Long> startupId;
    public static volatile SingularAttribute<Startup, String> startupRegistrationNum;
    public static volatile SingularAttribute<Startup, String> email;
    public static volatile SingularAttribute<Startup, StartupLocation> startupLocation;

}