/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Posting;
import entity.Student;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    private PostingWrapper[] favorites;

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
        this.favorites = new PostingWrapper[0];
    }

    public static StudentWrapper convertStudentToStudentWrapper(Student student) {
        String[] availablePeriod = new String[2];

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        availablePeriod[0] = simpleDateFormat.format(student.getAvailabilityPeriod()[0]);
        availablePeriod[1] = simpleDateFormat.format(student.getAvailabilityPeriod()[1]);
        String[] skillsArray = student.getRelevantSkills().toArray(new String[0]);

        StudentWrapper studentWrapper = new StudentWrapper(
                student.getStudentId(), student.getName(), student.getBiography(), student.getEmail(),
                student.getPassword(), student.getEducationalInstitute(), student.getCourseOfStudy(),
                student.getYearOfStudy(), simpleDateFormat.format(student.getProjectedGraduationYear()),
                skillsArray, availablePeriod);

        return studentWrapper;
    }

    public static Student convertStudentWrapperToStudent(StudentWrapper student) throws ParseException {
        Date[] availableDates = new Date[2];
        availableDates[0] = new SimpleDateFormat("yyyy-MM-dd").parse(student.getAvailabilityPeriod()[0]);
        availableDates[1] = new SimpleDateFormat("yyyy-MM-dd").parse(student.getAvailabilityPeriod()[1]);
        Date projectedGraduationYear = new SimpleDateFormat("yyyy-MM-dd").parse(student.getProjectedGraduationYear());
        List<String> skillsList = Arrays.asList(student.getRelevantSkills());
        List<PostingWrapper> postingList = Arrays.asList(student.getFavorites());
        for (PostingWrapper pw : postingList) {
            
        }
        Student newStudent = new Student(student.getStudentId(), student.getName(), student.getEducationalInstitute(),
                student.getBiography(), student.getEmail(), student.getPassword(),
                student.getCourseOfStudy(), student.getYearOfStudy(), projectedGraduationYear,
                skillsList, availableDates);

        return newStudent;
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

    public PostingWrapper[] getFavorites() {
        return favorites;
    }

    public void setFavorites(PostingWrapper[] favorites) {
        this.favorites = favorites;
    }

}
