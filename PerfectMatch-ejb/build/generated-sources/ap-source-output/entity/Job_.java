package entity;

import enumeration.Industry;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-25T14:46:07")
@StaticMetamodel(Job.class)
public class Job_ extends Posting_ {

    public static volatile SingularAttribute<Job, Double> monthlySalary;
    public static volatile SingularAttribute<Job, String> jobTitle;
    public static volatile SingularAttribute<Job, Date> earlietStartDate;
    public static volatile SingularAttribute<Job, String> jobDescription;
    public static volatile SingularAttribute<Job, Date> latestStartDate;
    public static volatile SingularAttribute<Job, Industry> industry;
    public static volatile SingularAttribute<Job, String[]> requiredSkills;

}