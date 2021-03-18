/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Student;
import javax.ejb.Local;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author Antho
 */
@Local
public interface StudentSessionBeanLocal {

    Student registerStudentAccount(Student student);

    Student loginStudent(String email, String password) throws NonUniqueResultException, NoResultException;

}
