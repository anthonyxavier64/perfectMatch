package entity;

import entity.Posting;
import entity.Student;
import enumeration.OfferStatus;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.4.v20190115-rNA", date="2021-03-23T12:52:12")
@StaticMetamodel(Offer.class)
public class Offer_ { 

    public static volatile SingularAttribute<Offer, Student> student;
    public static volatile SingularAttribute<Offer, Long> offerId;
    public static volatile SingularAttribute<Offer, String> offerMessage;
    public static volatile SingularAttribute<Offer, Posting> posting;
    public static volatile SingularAttribute<Offer, OfferStatus> offerStatus;

}