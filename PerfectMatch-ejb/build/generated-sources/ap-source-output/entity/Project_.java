package entity;

import enumeration.Industry;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.4.v20190115-rNA", date="2021-03-23T12:52:12")
@StaticMetamodel(Project.class)
public class Project_ extends Posting_ {

    public static volatile SingularAttribute<Project, String> projectDescription;
    public static volatile SingularAttribute<Project, Double> compensation;
    public static volatile SingularAttribute<Project, Date> earliestStartDate;
    public static volatile SingularAttribute<Project, Date> latestStartDate;
    public static volatile SingularAttribute<Project, Industry> industry;
    public static volatile SingularAttribute<Project, String[]> requiredSkills;
    public static volatile SingularAttribute<Project, String> projectSpecialisation;
    public static volatile SingularAttribute<Project, String> projectTitle;
    public static volatile SingularAttribute<Project, Boolean> isComplete;

}