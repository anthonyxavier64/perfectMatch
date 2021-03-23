package entity;

import entity.Application;
import entity.Offer;
import entity.Startup;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.4.v20190115-rNA", date="2021-03-23T12:52:12")
@StaticMetamodel(Posting.class)
public abstract class Posting_ { 

    public static volatile ListAttribute<Posting, Offer> offers;
    public static volatile SingularAttribute<Posting, Long> postingId;
    public static volatile SingularAttribute<Posting, Startup> startup;
    public static volatile ListAttribute<Posting, Application> applications;

}