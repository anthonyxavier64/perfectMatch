/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Antho
 */
@Entity
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotNull
    @Column(nullable = false)
    private String name;

    private String biography;

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false, length = 32)
    @Size(min = 6, max = 32)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String educationalInstitute;

    @NotNull
    @Column(nullable = false)
    private String courseOfStudy;

    @NotNull
    @Column(nullable = false)
    private Integer yearOfStudy;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date projectedGraduationYear;

    private List<String> relevantSkills;

    private Date[] availabilityPeriod;

    @OneToMany(mappedBy = "student")
    private List<Application> applications;

    @OneToMany(mappedBy = "student")
    private List<Payment> payments;

    @OneToMany(mappedBy = "student")
    private List<Offer> offers;

    @Column(nullable = false, length = 4)
    private String rating;

    @OneToMany(mappedBy = "studentBeingRated", fetch = FetchType.LAZY)
    private List<ReviewOfStudent> reviews;

    public Student() {
    }

    public Student(String name, String educationalInstitute, String biography, String email, String password, String courseOfStudy, Integer yearOfStudy,
            Date projectedGraduationYear, List<String> relevantSkills, Date[] availabiltiyPeriod) {
        this.name = name;
        this.educationalInstitute = educationalInstitute;
        this.biography = biography;
        this.email = email;
        this.password = password;
        this.courseOfStudy = courseOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.projectedGraduationYear = projectedGraduationYear;
        this.relevantSkills = relevantSkills;
        this.availabilityPeriod = availabiltiyPeriod;
        this.applications = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.offers = new ArrayList<>();
        this.rating = "0.00";
    }

    public Student(long studentId, String name, String educationalInstitute, String biography, String email, String password, String courseOfStudy, Integer yearOfStudy,
            Date projectedGraduationYear, List<String> relevantSkills, Date[] availabiltiyPeriod) {
        this.studentId = studentId;
        this.name = name;
        this.educationalInstitute = educationalInstitute;
        this.biography = biography;
        this.email = email;
        this.password = password;
        this.courseOfStudy = courseOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.projectedGraduationYear = projectedGraduationYear;
        this.relevantSkills = relevantSkills;
        this.availabilityPeriod = availabiltiyPeriod;
        this.applications = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.offers = new ArrayList<>();
        this.rating = "0.00";
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the studentId fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StudentEntity[ id=" + studentId + " ]";
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEducationalInstitute() {
        return educationalInstitute;
    }

    public void setEducationalInstitute(String educationalInstitute) {
        this.educationalInstitute = educationalInstitute;
    }

    public String getCourseOfStudy() {
        return courseOfStudy;
    }

    public void setCourseOfStudy(String courseOfStudy) {
        this.courseOfStudy = courseOfStudy;
    }

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Date getProjectedGraduationYear() {
        return projectedGraduationYear;
    }

    public void setProjectedGraduationYear(Date projectedGraduationYear) {
        this.projectedGraduationYear = projectedGraduationYear;
    }

    public List<String> getRelevantSkills() {
        return relevantSkills;
    }

    public void setRelevantSkills(List<String> relevantSkills) {
        this.relevantSkills = relevantSkills;
    }

    public Date[] getAvailabilityPeriod() {
        return availabilityPeriod;
    }

    public void setAvailabilityPeriod(Date[] availabilityPeriod) {
        this.availabilityPeriod = availabilityPeriod;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        BigDecimal sum = BigDecimal.ZERO;
        for (ReviewOfStudent r : reviews) {
            sum.add(BigDecimal.valueOf(r.getRating()));
        }
        BigDecimal ave = sum.divide(BigDecimal.valueOf(reviews.size())).setScale(2, RoundingMode.HALF_UP);
        Double aveRating = ave.doubleValue();
        rating = String.valueOf(aveRating);
        
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<ReviewOfStudent> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewOfStudent> reviews) {
        this.reviews = reviews;
    }

}
