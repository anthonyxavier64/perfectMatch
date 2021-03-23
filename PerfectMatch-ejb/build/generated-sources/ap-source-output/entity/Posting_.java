package entity;

import entity.Application;
import entity.Offer;
import entity.Startup;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-23T00:02:27")
@StaticMetamodel(Posting.class)
public abstract class Posting_ { 

    public static volatile ListAttribute<Posting, Offer> offers;
    public static volatile SingularAttribute<Posting, Long> postingId;
    public static volatile SingularAttribute<Posting, Startup> startup;
    public static volatile ListAttribute<Posting, Application> applications;

}