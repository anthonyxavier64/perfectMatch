package entity;

import entity.Posting;
import entity.Student;
import enumeration.OfferStatus;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-23T00:02:27")
@StaticMetamodel(Offer.class)
public class Offer_ { 

    public static volatile SingularAttribute<Offer, Student> student;
    public static volatile SingularAttribute<Offer, Long> offerId;
    public static volatile SingularAttribute<Offer, String> offerMessage;
    public static volatile SingularAttribute<Offer, Posting> posting;
    public static volatile SingularAttribute<Offer, OfferStatus> offerStatus;

}