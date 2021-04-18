/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Posting;
import entity.ReviewOfStudent;
import entity.Student;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private FavouritesWrapper[] favorites;
    private ReviewOfStudentWrapper[] reviews;
    private String rating;

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
        this.favorites = new FavouritesWrapper[]{};
    }

    public static StudentWrapper convertStudentToStudentWrapper(Student student) {
        String[] availablePeriod = new String[2];

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        availablePeriod[0] = simpleDateFormat.format(student.getAvailabilityPeriod()[0]);
        availablePeriod[1] = simpleDateFormat.format(student.getAvailabilityPeriod()[1]);
        String[] skillsArray = student.getRelevantSkills().toArray(new String[0]);

        List<FavouritesWrapper> faves = new ArrayList<>();

        for (Posting p : student.getFavorites()) {
            FavouritesWrapper fave = new FavouritesWrapper();
            fave.setPost(PostingWrapper.convertPostingToPostingWrapper(p));
            faves.add(fave);
        }
        
        FavouritesWrapper[] faveWraps = new FavouritesWrapper[faves.size()];

        int index = 0;
        for (FavouritesWrapper fw : faves) {
            faveWraps[index] = fw;
            index++;
        }

        ReviewOfStudentWrapper[] revWraps = new ReviewOfStudentWrapper[student.getReviews().size()];

        index = 0;
        for (ReviewOfStudent rw : student.getReviews()) {
            revWraps[index] = ReviewOfStudentWrapper.convertReviewToWrapper(rw);
            index++;
        }

        StudentWrapper studentWrapper = new StudentWrapper(
                student.getStudentId(), student.getName(), student.getBiography(), student.getEmail(),
                student.getPassword(), student.getEducationalInstitute(), student.getCourseOfStudy(),
                student.getYearOfStudy(), simpleDateFormat.format(student.getProjectedGraduationYear()),
                skillsArray, availablePeriod);
        
        studentWrapper.setRating(student.getRating());

        studentWrapper.setReviews(revWraps);
        studentWrapper.setFavorites(faveWraps);

        return studentWrapper;
    }

    public static Student convertStudentWrapperToStudent(StudentWrapper student) throws ParseException {
        Date[] availableDates = new Date[2];
        availableDates[0] = new SimpleDateFormat("yyyy-MM-dd").parse(student.getAvailabilityPeriod()[0]);
        availableDates[1] = new SimpleDateFormat("yyyy-MM-dd").parse(student.getAvailabilityPeriod()[1]);
        Date projectedGraduationYear = new SimpleDateFormat("yyyy-MM-dd").parse(student.getProjectedGraduationYear());
        List<String> skillsList = Arrays.asList(student.getRelevantSkills());
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

    public FavouritesWrapper[] getFavorites() {
        return favorites;
    }

    public void setFavorites(FavouritesWrapper[] favorites) {
        this.favorites = favorites;
    }

    public ReviewOfStudentWrapper[] getReviews() {
        return reviews;
    }

    public void setReviews(ReviewOfStudentWrapper[] reviews) {
        this.reviews = reviews;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
