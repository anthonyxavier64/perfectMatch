/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

/**
 *
 * @author Antho
 */
public class StudentWrapper {

    private long studentId;
    private String name;
    private String biography;
    private String email;
    private String password;
    private String educationalInstitute;
    private String courseOfStudy;
    private int yearOfStudy;
    private String projectedGraduationYear;
    private String[] relevantSkills;
    private String[] availabilityPeriod;

    public StudentWrapper() {
    }

    public StudentWrapper(long studentId, String name, String biography, String email, String password, String educationalInstitute, String courseOfStudy, int yearOfStudy, String projectedGraduationYear, String[] relevantSkills, String[] availabilityPeriod) {
        this.studentId = studentId;
        this.name = name;
        this.biography = biography;
        this.email = email;
        this.password = password;
        this.educationalInstitute = educationalInstitute;
        this.courseOfStudy = courseOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.projectedGraduationYear = projectedGraduationYear;
        this.relevantSkills = relevantSkills;
        this.availabilityPeriod = availabilityPeriod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getProjectedGraduationYear() {
        return projectedGraduationYear;
    }

    public void setProjectedGraduationYear(String projectedGraduationYear) {
        this.projectedGraduationYear = projectedGraduationYear;
    }

    public String[] getRelevantSkills() {
        return relevantSkills;
    }

    public void setRelevantSkills(String[] relevantSkills) {
        this.relevantSkills = relevantSkills;
    }

    public String[] getAvailabilityPeriod() {
        return availabilityPeriod;
    }

    public void setAvailabilityPeriod(String[] availabilityPeriod) {
        this.availabilityPeriod = availabilityPeriod;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

}