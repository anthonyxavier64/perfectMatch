/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author user
 */
public class CreateNewStudentException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewStudentException</code> without
     * detail message.
     */
    public CreateNewStudentException() {
    }

    /**
     * Constructs an instance of <code>CreateNewStudentException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewStudentException(String msg) {
        super(msg);
    }
}
